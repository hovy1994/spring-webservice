package com.example.springwebservice.web;

import com.example.springwebservice.domain.rent.Rent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class RentSaveRequestDto {

    private Integer IDX;
    private Integer ITEM_IDX;
    private Integer CABINET_IDX;
    private Integer USER_IDX;

    private LocalDateTime START_TIME;
    private LocalDateTime END_TIME;
    
    private byte[] IMAGE;

    public Rent toEntity(){
        return Rent.builder()
                .IDX(IDX)
                .ITEM_IDX(ITEM_IDX)
                .CABINET_IDX(CABINET_IDX)
                .USER_IDX(USER_IDX)
                .START_TIME(START_TIME)
                .END_TIME(END_TIME)
                .IMAGE(IMAGE)
                .build();
    }
}
