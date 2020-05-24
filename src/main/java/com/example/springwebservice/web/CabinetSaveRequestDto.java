package com.example.springwebservice.web;


import com.example.springwebservice.domain.cabinet.Cabinet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CabinetSaveRequestDto {
    private Integer CABINET_IDX;
    private String CABINET_NAME;
    private Double LATITUDE;
    private Double LONGITUDE;

    public Cabinet toEntity(){
        return Cabinet.builder()
                .CABINET_IDX(CABINET_IDX)
                .CABINET_NAME(CABINET_NAME)
                .LATITUDE(LATITUDE)
                .LONGITUDE(LONGITUDE)
                .build();
    }
}
