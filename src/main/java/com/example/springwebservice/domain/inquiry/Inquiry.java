package com.example.springwebservice.domain.inquiry;

import com.example.springwebservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.persistence.*;


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

