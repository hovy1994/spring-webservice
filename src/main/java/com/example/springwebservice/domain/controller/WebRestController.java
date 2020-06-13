package com.example.springwebservice.domain.controller;


import com.example.springwebservice.domain.inquiry.Inquiry;
import com.example.springwebservice.domain.inquiry.InquiryRepository;
import com.example.springwebservice.domain.cabinet.CabinetRepository;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.ItemRepository;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.web.InquirySaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class WebRestController {

    private InquiryRepository inquiryRepository;
    private MemberRepository memberRepository;
    private CabinetRepository cabinetRepository;
    private ItemRepository itemRepository;



    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld"; // helloworld 문자열을 json 형태로 반환
    }

    @RequestMapping(value="/request", method={ RequestMethod.GET, RequestMethod.POST })
    public String returnhello(){
        return "hello";
    }



    @Cacheable(value="memberCache")  // cache name을 value로
    @GetMapping(path = "/itemList")
    public List CategoryDisplay(){
        System.out.print("Item list connect success");
        List<Item> itemList =itemRepository.findAll();

        return itemList;
    }
    @RequestMapping(value="/inquiry",method={ RequestMethod.GET, RequestMethod.POST })
    public Inquiry saveInquiry(@RequestBody InquirySaveRequestDto dto) {
        inquiryRepository.save(dto.toEntity());
        List<Inquiry> inquiryList = inquiryRepository.findAll();

        Inquiry inquiry = inquiryList.get(0);
        return inquiry;
    }


}
