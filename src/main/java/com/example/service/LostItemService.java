package com.example.lostandfound.service;

import com.example.lostandfound.model.LostItem;
import com.example.lostandfound.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository repository;

    // 保存失物信息，并隐私处理手机号
    public LostItem saveLostItem(LostItem item) {
        // 处理手机号隐私
        item.setPhone(PhonePrivacyUtil.hidePhone(item.getPhone()));

        // 将失物信息保存到数据库
        return repository.save(item);
    }

    // 获取失物信息时，可以选择是否展示隐藏的手机号
    public LostItem getLostItemById(Long id) {
        LostItem item = repository.findById(id).orElse(null);

        if (item != null) {
            // 返回时将手机号恢复为隐私处理过的版本
            item.setPhone(PhonePrivacyUtil.hidePhone(item.getPhone()));
        }

        return item;
    }

    // 保存失物信息
    public LostItem saveLostItem(LostItem item) {
        return repository.save(item);
    }

    // 保存失物信息
    public LostItem saveLostItem(LostItem item) {
        return repository.save(item);
    }

    // 查找所有失物信息
    public List<LostItem> getAllLostItems() {
        return repository.findAll();
    }

    // 根据ID查找失物信息
    public Optional<LostItem> getLostItemById(Long id) {
        return repository.findById(id);
    }
}
