package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.RentalRequestInfo;
import com.example.springwebservice.domain.posts.Posts;
import com.example.springwebservice.domain.posts.PostsRepository;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.service.RentalService;
import com.example.springwebservice.service.mapper.ItemMapper;
import com.example.springwebservice.web.PostsSaveRequestDto;
import com.example.springwebservice.web.RentSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RentalController {
    private RentalService rentalService;
    private PostsRepository postsRepository;
    private RentRepository rentRepository;

//    @PostMapping(value="/recommendCabinet")
//    public Integer recommendCabinet(@RequestBody RentalRequestInfo dto){
//
//        return rentalService.findCabinet(dto);
//    }

//    @RequestMapping(value="/recommendCabinet",method={ RequestMethod.GET, RequestMethod.POST })
    @PostMapping(path = "/recommendCabinet")
    @GetMapping(path = "/recommendCabinet")
    public ArrayList<Cabinet> recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Category Idx : " + info.getCategory_idx());
        System.out.println("Start time : " + info.getStart());
        System.out.println("End time : " + info.getEnd());

        return rentalService.findCabinet(info);
    }


    @PostMapping(path = "/apply")
    @GetMapping(path = "/apply")
    public RentSaveRequestDto apply(@RequestBody RentalRequestInfo info){
        System.out.println("User Idx : " + info.getUser_idx());
        System.out.println("Cabinet Idx : " + info.getCabinet_idx());

        Item applyItem=rentalService.findAvailableItem(info);

        RentSaveRequestDto dto=new RentSaveRequestDto();
        dto.setCABINET_IDX(info.getCabinet_idx());
        dto.setITEM_IDX(applyItem.getITEM_IDX());
        dto.setUSER_IDX(info.getUser_idx());
        dto.setSTART_TIME(info.getStart());
        dto.setEND_TIME(info.getEnd());
        rentRepository.save(dto.toEntity());

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
