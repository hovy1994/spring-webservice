package com.example.springwebservice.domain.inquiry;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InquriyRepositoryTest {

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
                .user_idx(1)
                .title("title")
                .content("content")
                .build()
        );

        //when
        List<Inquiry> inquiryList = inquiryRepository.findAll();

        //then
        Inquiry inquiry = inquiryList.get(0);
        assertTrue(inquiry.getRequest_date().isAfter(now));
        assertThat(inquiry.getTitle(), is("title"));
    }

}
