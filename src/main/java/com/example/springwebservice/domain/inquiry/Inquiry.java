package com.example.springwebservice.domain.inquiry;

import com.example.springwebservice.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Inquiry extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Integer user_idx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;

    private String content;

    @Builder
    public Inquiry(Integer user_idx, String title, String content) {
        this.user_idx=user_idx;
        this.title = title;
        this.content = content;
    }

}

