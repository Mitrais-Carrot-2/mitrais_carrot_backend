package com.team.two.mitrais_carrot.service.exchange;

import com.team.two.mitrais_carrot.dto.MessageDto;

import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.exchange.EExchangeStatus;
import com.team.two.mitrais_carrot.entity.exchange.ExchangeEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.ExchangeRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.entity.basket.EBasket;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import com.team.two.mitrais_carrot.service.notification.NotificationService;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
import com.team.two.mitrais_carrot.service.user.UserService;

import com.team.two.mitrais_carrot.dto.exchange.ExchangeDataDto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private BazaarItemRepository bazaarItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BazaarItemService bazaarItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private TransferService transferService;

    @Autowired
    NotificationService notificationService;

    @Getter
    public enum ExchangeStatus{
        RequestSuccess(200, "[SUCCESS] Request purchasing item"),
        NotEnoughCarrots(400, "[FAILED] There are not enough carrots to buy this item"),
        ItemNotFound(404, "[FAILED] Item is not found / unavailable"),
        ItemSoldOut(402, "[FAILED] Item is already sold out");

        int errorCode;
        String message;
        private ExchangeStatus(int errorCode, String message){
            this.errorCode = errorCode;
            this.message = message;
        }
    }

    private ExchangeStatus response;

    public ExchangeService(ExchangeRepository exchangeRepository) {this.exchangeRepository = exchangeRepository;}

    public ExchangeEntity add(UserEntity buyer, BazaarItemEntity item){
        ExchangeEntity exchange = createEntity(buyer, item);
        return exchangeRepository.save(exchange);
    }

    public ExchangeEntity add(ExchangeEntity exchange){
        return exchangeRepository.save(exchange);
    }

    public ExchangeEntity getById(long id){
        return exchangeRepository.findById(id).orElse(null);
    }

    public ExchangeEntity createEntity(UserEntity buyer, BazaarItemEntity item){
        ExchangeEntity exchange = new ExchangeEntity();
        exchange.setActive(true);
        exchange.setUserId(buyer);
        exchange.setPrice(item.getPrice());
        exchange.setBazaarItemId(item);
        exchange.setExchangeDate(LocalDateTime.now());
        exchange.setStatus(EExchangeStatus.REQUESTED);

        return exchange;
    }

    public boolean isCarrotEnough(UserEntity buyer, BazaarItemEntity item){
        BasketEntity basket = basketService.getActiveBasket(buyer, true);
        return (basket.getCarrotAmount() >= item.getPrice());
    }

    public void requestExchange(ExchangeEntity exchange){
        UserEntity buyer = exchange.getUserId();
        BazaarItemEntity item = exchange.getBazaarItemId();
        bazaarItemService.updateQuantity(exchange.getBazaarItemId().getId(), -1);
        basketService.updateCarrot(buyer, -(item.getPrice()), EBasket.BAZAAR);
        saveExchangeToTransferHistory(exchange, true);
    }

    public void denyRequest(ExchangeEntity exchange){
        UserEntity buyer = userService.getById(exchange.getUserId().getId());
        BazaarItemEntity item = bazaarItemService.getById(exchange.getBazaarItemId().getId());

        bazaarItemService.updateQuantity(exchange.getBazaarItemId().getId(), 1);
        basketService.updateCarrot(buyer, item.getPrice(), EBasket.BAZAAR);
        saveExchangeToTransferHistory(exchange, false);
    }

    public ExchangeStatus buyBazaarItem(long buyerId, int itemId){
        UserEntity buyer = userService.getById(buyerId);
        BazaarItemEntity item = bazaarItemService.getById(itemId);
        ExchangeEntity exchange = createEntity(buyer, item);

        if (buyer != null){
            if (item == null) {
                response = ExchangeStatus.ItemNotFound;
                return response;
            }
            else {
                if (item.getQuantity() > 0){
                    if (isCarrotEnough(buyer, item)){
                        add(exchange);
                        requestExchange(exchange);
                        response = ExchangeStatus.RequestSuccess;
                        return response;
                    }
                    else {
                        response = ExchangeStatus.NotEnoughCarrots;
                        return response;
                    }
                }
                else {
                    response = ExchangeStatus.ItemSoldOut;
                    return response;
                }
            }
        }
        return response;
    }

    public ResponseEntity<?> setExchangeStatus(ExchangeEntity exchange, EExchangeStatus newStatus){
        String itemName = bazaarItemService.getById(exchange.getBazaarItemId().getId()).getName();
        long userId = exchange.getUserId().getId();
        if (newStatus == EExchangeStatus.DENIED){
            denyRequest(exchange);
            notificationService.createNotification(new NotificationDto(userId, "[FAIL] Your " + itemName + " request has been denied!" ));
        } else if (newStatus == EExchangeStatus.APPROVED){
            notificationService.createNotification(new NotificationDto(userId, "[SUCCESS] Your " + itemName + " request has been approved!" ));
        }
        exchange.setStatus(newStatus);
        add(exchange);
        return ResponseEntity.ok(new MessageDto(exchange,"Exchange Status Updated!", true));
    }

    public TransferEntity saveExchangeToTransferHistory(ExchangeEntity exchange, boolean success){
        TransferEntity transfer = new TransferEntity();
        long bazaarId = 999L;
        long userId = exchange.getUserId().getId();
        long carrot = exchange.getPrice();
        LocalDateTime date = exchange.getExchangeDate();
        String itemName = bazaarItemService.getById(exchange.getBazaarItemId().getId()).getName();

        if (success){
            transfer.setSenderId(userId);
            transfer.setReceiverId(bazaarId);
            transfer.setNote("[REQUEST SUCCESS] Buy Item " + itemName);
            carrot = -carrot;

        }
        else {
            transfer.setSenderId(bazaarId);
            transfer.setReceiverId(userId);
            transfer.setNote("[REQUEST DENIED] Buy Item " + itemName);
//            date = LocalDateTime.now();

        }
        transfer.setShareAt(date);
        transfer.setType(ETransferType.TYPE_BAZAAR);
        transfer.setCarrotAmount(carrot);

        return transferService.add(transfer);
    }

    public List<ExchangeDataDto> getAllExchange(){
        List<ExchangeEntity> rawExchange = exchangeRepository.findAll();

        List<ExchangeDataDto> modifiedExchange = rawExchange.stream()
                .map((ExchangeEntity exchange) -> new ExchangeDataDto(
                        (int) exchange.getId(),
                        exchange.isActive(),
                        exchange.getPrice(),
                        exchange.getExchangeDate(),
                        exchange.getStatus(),
                        exchange.getUserId().getFirstName() + " " + exchange.getUserId().getLastName(),
                        exchange.getBazaarItemId().getName()))
                .collect(Collectors.toList());
        return modifiedExchange;
    }

    public List<ExchangeEntity> getAll(){
        return (List<ExchangeEntity>) exchangeRepository.findAll();
    }
}