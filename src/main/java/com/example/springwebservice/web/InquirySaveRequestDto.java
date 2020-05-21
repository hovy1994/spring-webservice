package com.example.springwebservice.web;

import com.example.springwebservice.domain.inquiry.Inquiry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InquirySaveRequestDto {

    private Integer user_id;
    private String title;
    private String content;

    public Inquiry toEntity(){
        return Inquiry.builder()
                .user_id(user_id)
                .title(title)
                .content(content)
                .build();
    }
}
