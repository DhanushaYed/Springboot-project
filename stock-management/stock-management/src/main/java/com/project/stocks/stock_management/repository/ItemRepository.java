package com.project.stocks.stock_management.repository;

import com.project.stocks.stock_management.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
