package com.example.springwebservice.web;

import com.example.springwebservice.domain.BaseTimeEntity;
import com.example.springwebservice.domain.rent.Rent;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class RentSaveRequestDto extends BaseTimeEntity {

    private Integer IDX;
    private Integer ITEM_IDX;
    private Integer CABINET_IDX;
    private Integer USER_IDX;

    private byte[] IMAGE;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime START_TIME;
    private LocalDateTime END_TIME;

    public Rent toEntity(){
        return Rent.builder()
                .IDX(IDX)
                .ITEM_IDX(ITEM_IDX)
                .CABINET_IDX(CABINET_IDX)
                .USER_IDX(USER_IDX)
                .IMAGE(IMAGE)
                .START_TIME(START_TIME)
                .END_TIME(END_TIME)
                .build();
    }
}
