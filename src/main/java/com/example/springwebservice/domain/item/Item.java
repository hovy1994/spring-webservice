package com.example.springwebservice.domain.item;

import com.example.springwebservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ITEM_IDX;

    private Integer CATEGORY_IDX;

    private Integer RENT_PRICE;

    private Integer CABINET_IDX;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ITEM_NAME;

    private Integer STATE;

    @Builder
    public Item(Integer ITEM_IDX, Integer STATE, Integer CATEGORY_IDX,Integer RENT_PRICE,String ITEM_NAME,Integer CABINET_IDX) {
        this.ITEM_IDX=ITEM_IDX;
        this.CATEGORY_IDX = CATEGORY_IDX;
        this.CABINET_IDX=CABINET_IDX;
        this.RENT_PRICE=RENT_PRICE;
        this.ITEM_NAME=ITEM_NAME;
        this.STATE=STATE;
    }
}
