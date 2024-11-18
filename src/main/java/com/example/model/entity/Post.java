package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String image;
    private  String name;
    private String phone;
    private String description;
    private String location;
    /**
     * 0.寻找失主贴 1.寻找失物贴
     */
    private Integer type;
    private Integer userId;

}
