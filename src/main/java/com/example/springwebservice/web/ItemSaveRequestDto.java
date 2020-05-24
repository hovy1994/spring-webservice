package com.example.springwebservice.web;

import com.example.springwebservice.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveRequestDto {
<<<<<<< HEAD
    private Integer ITEM_IDX;
    private Integer CATEGORY_IDX;
    private Integer ITEM_NUM;
    private Integer RENT_PRICE;
    private String ITEM_NAME;

    public Item toEntity(){
        return Item.builder()
                .ITEM_IDX(ITEM_IDX)
                .CATEGORY_IDX(CATEGORY_IDX)
                .RENT_PRICE(RENT_PRICE)
                .ITEM_NAME(ITEM_NAME)
=======

    private Integer item_idx;
    private Integer category_idx;
    private Integer item_num;
    private Integer rent_price;
    private String item_name;

    public Item toEntity(){
        return Item.builder()
                .item_idx(item_idx)
                .category_idx(category_idx)
                .item_num(item_num)
		.rent_price(rent_price)
		.item_name(item_name)
>>>>>>> 3f2ba606c46c3f5d53cf84ec2dd2c5ed2c913952
                .build();
    }
}
