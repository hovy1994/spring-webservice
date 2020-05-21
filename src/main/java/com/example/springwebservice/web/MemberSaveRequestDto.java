package com.example.springwebservice.web;

import com.example.springwebservice.domain.inquiry.Inquiry;
import com.example.springwebservice.domain.member.Member;

import javax.persistence.Column;

public class MemberSaveRequestDto {

    private Integer USER_IDX;
    private Integer STAMP;
    private String USER_ID;
    private String USER_NAME;
    private String USER_PHONE;
    private String USER_EMAIL;
    private String USER_PW;
    private String USER_GENDER;
    private String USER_NICKNAME;

    public Member toEntity(){
        return Member.builder()
                .USER_IDX(USER_IDX)
                .STAMP(STAMP)
                .USER_ID(USER_ID)
                .USER_NAME(USER_NAME)
                .USER_PHONE(USER_PHONE)
                .USER_EMAIL(USER_EMAIL)
                .USER_PW(USER_PW)
                .USER_GENDER(USER_GENDER)
                .USER_NICKNAME(USER_NICKNAME)
                .build();
    }
}
