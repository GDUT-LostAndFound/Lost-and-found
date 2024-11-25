package com.example.lostandfound.repository;

import com.example.lostandfound.model.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    // 按物品描述模糊搜索
    List<LostItem> findByDescriptionContainingIgnoreCase(String description);

    // 按找到的地址模糊搜索
    List<LostItem> findByFoundLocationContainingIgnoreCase(String foundLocation);

}
