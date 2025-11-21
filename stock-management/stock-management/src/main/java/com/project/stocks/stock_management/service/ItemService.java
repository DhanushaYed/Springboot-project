package com.project.stocks.stock_management.service;

import com.project.stocks.stock_management.exception.InsufficientStockException;
import com.project.stocks.stock_management.entity.ItemEntity;
import com.project.stocks.stock_management.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {
    private static final Logger log = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository repo;

    public ItemService(ItemRepository repo){
        this.repo=repo;
    }

    public ItemEntity createItem(ItemEntity item){
        log.info("Creating new item: {}",item.getName());
        return repo.save(item);
    }

    public List<ItemEntity> getAllItems(){
        return repo.findAll();
    }

    public ItemEntity getItem(Long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("Item not found"));
    }

    public ItemEntity increaseStock(Long id, int amount){
        ItemEntity item = getItem(id);
        item.setQuantity(item.getQuantity()+amount);
        log.info("Increased stock of {} by {}", item.getName(), amount);
        return repo.save(item);
    }

    public ItemEntity decreaseStock(Long id, int amount){
        ItemEntity item = getItem(id);
        if(item.getQuantity()<amount){
            log.warn("Insufficient stock for item {}. Requested: {}, Available: {}", item.getName(), amount, item.getQuantity());
            throw new InsufficientStockException("Stock is not enough");
        }
        item.setQuantity(item.getQuantity() - amount);
        log.info("Decreased stock of {} by {}", item.getName(), amount);
        return repo.save(item);
    }

}
