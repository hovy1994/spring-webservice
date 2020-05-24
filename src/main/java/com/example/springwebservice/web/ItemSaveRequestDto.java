package com.example.springwebservice.web;

import com.example.springwebservice.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveRequestDto {

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
                .build();
    }
}
