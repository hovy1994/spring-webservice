package com.example.springwebservice.domain.member;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginAuthInfo {
    private String encryptedUserId;
    private String pubKey;
}