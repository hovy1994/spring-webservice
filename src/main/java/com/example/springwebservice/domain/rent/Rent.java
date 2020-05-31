package com.example.springwebservice.domain.rent;

import com.example.springwebservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Rent extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDX;
    
    private Integer ITEM_IDX;

    private Integer CABINET_IDX;

    private Integer USER_IDX;
    
    private byte[] IMAGE;

    @Builder
    public Rent(Integer IDX, Integer ITEM_IDX, Integer CABINET_IDX, Integer USER_IDX,
               byte[] IMAGE) {
        this.IDX=IDX;
        this.ITEM_IDX = ITEM_IDX;
        this.CABINET_IDX = CABINET_IDX;
        this.USER_IDX = USER_IDX;
        this.IMAGE = IMAGE;
    }
}
