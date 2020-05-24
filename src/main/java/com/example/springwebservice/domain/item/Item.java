<<<<<<< HEAD
package com.example.springwebservice.domain.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
package com.example.springwebservice.domain.Item;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

>>>>>>> 3f2ba606c46c3f5d53cf84ec2dd2c5ed2c913952

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
<<<<<<< HEAD
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
public class Item{
    @Id
>>>>>>> 3f2ba606c46c3f5d53cf84ec2dd2c5ed2c913952
    private Integer ITEM_IDX;

    private Integer CATEGORY_IDX;

    private Integer ITEM_NUM;

    private Integer RENT_PRICE;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ITEM_NAME;

    @Builder
<<<<<<< HEAD
    public Item(Integer ITEM_IDX, Integer CATEGORY_IDX,Integer ITEM_NUM,Integer RENT_PRICE,String ITEM_NAME) {
        this.ITEM_IDX=ITEM_IDX;
        this.CATEGORY_IDX = CATEGORY_IDX;
        this.ITEM_NUM = ITEM_NUM;
        this.RENT_PRICE=RENT_PRICE;
        this.ITEM_NAME=ITEM_NAME;
    }
=======
    public Item(Integer ITEM_IDX, Integer CATEGORY_IDX,Integer ITEM_NUM, Integer RENT_PRICE, String ITEM_NAME) {
        this.ITEM_IDX=ITEM_IDX;
        this.CATEGORY_IDX = CATEGORY_IDX;
        this.ITEM_NUM=ITEM_NUM;
        this.RENT_PRICE = RENT_PRICE;
        this.ITEM_NAME=ITEM_NAME;
    }

>>>>>>> 3f2ba606c46c3f5d53cf84ec2dd2c5ed2c913952
}
