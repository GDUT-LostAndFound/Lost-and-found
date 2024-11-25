package com.example.lostandfound.controller;

import com.example.lostandfound.model.LostItem;
import com.example.lostandfound.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lost-items")
public class LostItemController {

    @Autowired
    private LostItemService service;

    // 发布失物信息的端点
    @PostMapping("/add")
    public ResponseEntity<LostItem> addLostItem(@RequestBody LostItem lostItem) {
        LostItem savedItem = service.saveLostItem(lostItem);
        return ResponseEntity.ok(savedItem);
    }

    // 获取失物信息并返回手机号隐私处理后的版本
    @GetMapping("/{id}")
    public ResponseEntity<LostItem> getLostItemById(@PathVariable Long id) {
        LostItem item = service.getLostItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    // 发布找到的失物信息的端点
    @PostMapping("/found")
    public ResponseEntity<LostItem> reportFoundItem(@RequestBody LostItem foundItem) {
        // 将找到的失物信息保存到数据库中
        LostItem savedItem = service.saveLostItem(foundItem);
        return ResponseEntity.ok(savedItem);
    }

    // 发布失物信息的端点
    @PostMapping("/add")
    public ResponseEntity<LostItem> addLostItem(@RequestBody LostItem lostItem) {
        LostItem savedItem = service.saveLostItem(lostItem);
        return ResponseEntity.ok(savedItem);
    }

    // 获取所有失物信息的端点
    @GetMapping("/all")
    public ResponseEntity<List<LostItem>> getAllLostItems() {
        List<LostItem> items = service.getAllLostItems();
        return ResponseEntity.ok(items);
    }

    // 根据ID获取失物信息的端点
    @GetMapping("/{id}")
    public ResponseEntity<LostItem> getLostItemById(@PathVariable Long id) {
        Optional<LostItem> item = service.getLostItemById(id);
        return item.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

