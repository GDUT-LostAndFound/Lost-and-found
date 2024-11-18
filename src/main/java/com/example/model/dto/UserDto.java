package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.regex.qual.Regex;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
   private String avatar;
   private String username;

   private String phone;


   private String password;
}
