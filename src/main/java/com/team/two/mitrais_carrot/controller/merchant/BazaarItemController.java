package com.team.two.mitrais_carrot.controller.merchant;

import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemResponseDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bazaar")
public class BazaarItemController {
    @Autowired
    BazaarItemService bazaarItemService;

    public BazaarItemController(BazaarItemService bazaarItemService) {
        this.bazaarItemService = bazaarItemService;
    }

    @PostMapping("{id}/item")
    public ResponseEntity<?> addBazaarItem(@PathVariable("id") int id, @RequestBody BazaarItemDto request) {
        return bazaarItemService.addNewItem(request, id);
    }

    @GetMapping("item")
    public List<BazaarItemResponseDto> getAllBazaarItems() {
        return bazaarItemService.getAllDto();
    }

    @GetMapping("item/{itemId}")
    public BazaarItemResponseDto getItem(@PathVariable int itemId) {
        return bazaarItemService.getDtoById(itemId);
    }

    @GetMapping("{bazaarId}/{itemId}")
    public BazaarItemResponseDto getBazaarItem(@PathVariable int bazaarId, @PathVariable int itemId) {
        return bazaarItemService.getDtoByIdInBazaar(itemId, bazaarId);
    }

    @GetMapping("{bazaarId}/items")
    public List<BazaarItemResponseDto> getAllItemsInBazaar(@PathVariable int bazaarId) {
        return bazaarItemService.getAllDtoInBazaar(bazaarId);
    }

    @GetMapping("/getImage/{itemId}")
    public ResponseEntity<byte[]> getItemBazaarImage(@PathVariable int itemId) throws Exception {
        BazaarItemEntity item = bazaarItemService.getById(itemId);
        if (item != null) {
            if (item.getImage() != null) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"Item id " + item.getId() + " Image \"")
                        .contentType(MediaType.valueOf(item.getImageType()))
                        .body(item.getImage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/item/{itemId}")
    public ResponseEntity<?> updateDetail(@PathVariable int itemId, @RequestBody BazaarItemDto request) {
        return bazaarItemService.updateItem(itemId, request);
    }

    @PutMapping("/uploadImage/{itemId}")
    public ResponseEntity<?> uploadImage(@PathVariable int itemId, @RequestParam("file") MultipartFile file) {
        try {
            bazaarItemService.saveImage(itemId, file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @DeleteMapping("{bazaarId}/{itemId}")
    public ResponseEntity<?> deleteBazaarItem(@PathVariable int bazaarId, @PathVariable int itemId) {
        return bazaarItemService.deleteById(itemId, bazaarId);
    }
}
