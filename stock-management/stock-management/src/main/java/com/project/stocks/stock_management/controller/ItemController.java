package com.project.stocks.stock_management.controller;

import com.project.stocks.stock_management.entity.ItemEntity;
import com.project.stocks.stock_management.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ItemEntity> createItem(@Valid @RequestBody ItemEntity item){
        return ResponseEntity.ok(service.createItem(item));
    }

    @GetMapping("/get/all")
    public List<ItemEntity> getAll(){
        return service.getAllItems();
    }

    @GetMapping("/get/{id}")
    public ItemEntity getOne(@PathVariable Long id){
        return service.getItem(id);
    }

    @PutMapping("/{id}/increase")
    public ItemEntity increaseStock(@PathVariable Long id, @RequestParam int amount){
        return service.increaseStock(id,amount);
    }

    @PutMapping("/{id}/decrease")
    public ItemEntity decreaseStock(@PathVariable Long id, @RequestParam int amount){
        return service.decreaseStock(id,amount);
    }


}
