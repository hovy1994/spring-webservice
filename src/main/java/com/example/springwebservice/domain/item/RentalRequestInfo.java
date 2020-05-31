package com.example.springwebservice.domain.item;

import com.example.springwebservice.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalRequestInfo {
    private Integer category_idx;
    private Integer user_idx;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    private LocalDateTime end;
}
