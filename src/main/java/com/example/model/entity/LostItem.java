package com.example.lostandfound.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成主键
    private Long id;

    private String image; // 物品图片的URL或路径
    private String phone; // 联系电话
    private String name; // 发布者姓名
    private String description; // 物品描述
    private String foundLocation; // 物品找到的地址

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
    }
}
