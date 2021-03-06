package com.team.two.mitrais_carrot.controller.transfer;

import com.team.two.mitrais_carrot.dto.transfer.TransferHistoryDto;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    @Autowired
    TransferService transferService;

    @GetMapping("")
    public List<TransferEntity> getAllTransfers(){
        return transferService.getAll();
    }

    @GetMapping("{userId}")
    public List<TransferHistoryDto> getAllTransfersById(@PathVariable long userId){
        return transferService.getAllById(userId);
    }

}
