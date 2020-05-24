package com.example.springwebservice.domain.inquiry;

import com.example.springwebservice.domain.member.Member;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquiryRepositoryTest {

    @Autowired
    InquiryRepository inquiryRepository;

    @After
    public void cleanup() {
        inquiryRepository.deleteAll();
    }

    @Test
    public void Inquiry_register () {
        //given
        LocalDateTime now = LocalDateTime.now();

        inquiryRepository.save(Inquiry.builder()
                .IDX(3)
                .USER_IDX(3)
                .TITLE("title")
                .CONTENT("content")
                .build()
        );

        //when
        List<Inquiry> inquiryList = inquiryRepository.findAll();

        //then
        Inquiry inquiry = inquiryList.get(0);
        assertTrue(inquiry.getREQUEST_DATE().isAfter(now));
        assertThat(inquiry.getTITLE(), is("title"));
    }
}
