package com.example.springwebservice.web;

import com.example.springwebservice.domain.inquiry.Inquiry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InquirySaveRequestDto {

    private Integer IDX;
    private Integer USER_IDX;
    private String TITLE;
    private String CONTENT;
    private LocalDateTime REQUEST_DATE;

    public Inquiry toEntity(){
        return Inquiry.builder()
                .IDX(IDX)
                .USER_IDX(USER_IDX)
                .TITLE(TITLE)
                .CONTENT(CONTENT)
                .REQUEST_DATE(REQUEST_DATE)
                .build();
    }
}
