package com.example.springwebservice.domain.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ITEM_IDX;

    private Integer CATEGORY_IDX;

    private Integer ITEM_NUM;

    private Integer RENT_PRICE;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ITEM_NAME;

    @Builder
    public Item(Integer ITEM_IDX, Integer CATEGORY_IDX,Integer ITEM_NUM,Integer RENT_PRICE,String ITEM_NAME) {
        this.ITEM_IDX=ITEM_IDX;
        this.CATEGORY_IDX = CATEGORY_IDX;
        this.ITEM_NUM = ITEM_NUM;
        this.RENT_PRICE=RENT_PRICE;
        this.ITEM_NAME=ITEM_NAME;
    }
}