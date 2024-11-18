package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 查询信息相应结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectResult<T> {
    ArrayList<T> data;
}
