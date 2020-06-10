package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.domain.posts.Posts;
import com.example.springwebservice.domain.posts.PostsRepository;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.service.RentalService;
import com.example.springwebservice.service.mapper.ItemMapper;
import com.example.springwebservice.service.mapper.MemberMapper;
import com.example.springwebservice.web.PostsSaveRequestDto;
import com.example.springwebservice.web.RentSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = false)
@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RentalController {
    private RentalService rentalService;
    private PostsRepository postsRepository;
    private RentRepository rentRepository;
    @Autowired
    private MemberMapper memberMapper;

    @PostMapping(path = "/recommendCabinet")
    @GetMapping(path = "/recommendCabinet")
    public ArrayList<Cabinet> recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Item Idx : " + info.getItem_idx());
        System.out.println("Start time : " + info.getStart());
        System.out.println("End time : " + info.getEnd());

        return rentalService.findCabinet(info);
    }


    @PostMapping(path = "/apply")
    @GetMapping(path = "/apply")
    public RentSaveRequestDto apply(@RequestBody RentalRequestInfo info){
        System.out.println("User Id : " + info.getUser_id());
        System.out.println("Start Cabinet Idx : " + info.getStart_cabinet_idx());

        //Item applyItem=rentalService.findAvailableItem(info);

        RentSaveRequestDto dto=new RentSaveRequestDto();

        dto.setSTART_CABINET_IDX(info.getStart_cabinet_idx());
        dto.setEND_CABINET_IDX(info.getEnd_cabinet_idx());
        //dto.setITEM_IDX(applyItem.getITEM_IDX());
        dto.setUSER_ID(info.getUser_id());
        dto.setSTART_TIME(info.getStart());
        dto.setEND_TIME(info.getEnd());
        dto.setITEM_IDX(info.getItem_idx());

        rentRepository.save(dto.toEntity());

        // stamp 추가 -> member 정보 update
        if(info.getRecommend()==1)
            memberMapper.addStamp(info.getUser_id(),2);
        else
            memberMapper.addStamp(info.getUser_id(),1);

        return dto;
    }


    @RequestMapping(value="/posts",method={ RequestMethod.GET, RequestMethod.POST })
    public List<Posts> savePosts(@RequestBody PostsSaveRequestDto dto){
        System.out.println("posts : " + dto.getAuthor());
        postsRepository.save(dto.toEntity());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        return postsList;
    }

}
