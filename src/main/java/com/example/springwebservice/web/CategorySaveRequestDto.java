package com.example.springwebservice.web;

import com.example.springwebservice.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private Integer CATEGORY_IDX;
    private String CATEGORY_NAME;

    public Category toEntity(){
        return Category.builder()
                .CATEGORY_IDX(CATEGORY_IDX)
                .CATEGORY_NAME(CATEGORY_NAME)
                .build();
    }
}
