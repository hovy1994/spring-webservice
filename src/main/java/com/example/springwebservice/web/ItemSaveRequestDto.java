package com.example.springwebservice.web;

import com.example.springwebservice.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveRequestDto {
    private Integer ITEM_IDX;
    private Integer CATEGORY_IDX;
    private Integer RENT_PRICE;
    private Integer START_CABINET_IDX;
    private Integer END_CABINET_IDX;
    private String ITEM_NAME;

    public Item toEntity(){
        return Item.builder()
                .ITEM_IDX(ITEM_IDX)
                .CATEGORY_IDX(CATEGORY_IDX)
                .START_CABINET_IDX(START_CABINET_IDX)
                .END_CABINET_IDX(END_CABINET_IDX)
                .RENT_PRICE(RENT_PRICE)
                .ITEM_NAME(ITEM_NAME)
                .build();
    }
}
