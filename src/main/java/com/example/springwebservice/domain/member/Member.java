package com.example.springwebservice.domain.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member{
    @Id
    private Integer USER_IDX;

    private Integer STAMP;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String USER_ID;

    private String USER_NAME;

    private String USER_PHONE;

    private String USER_EMAIL;

    private String USER_PW;

    private String USER_GENDER;

    private String USER_NICKNAME;

    @Builder
    public Member(Integer USER_IDX, Integer STAMP,String USER_ID, String USER_NAME, String USER_EMAIL,
                   String USER_PW, String USER_PHONE, String USER_GENDER, String USER_NICKNAME) {
        this.USER_IDX=USER_IDX;
        this.STAMP = STAMP;
        this.USER_ID=USER_ID;
        this.USER_NAME = USER_NAME;
        this.USER_PHONE=USER_PHONE;
        this.USER_PW=USER_PW;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_GENDER = USER_GENDER;
        this.USER_NICKNAME = USER_NICKNAME;
    }

}