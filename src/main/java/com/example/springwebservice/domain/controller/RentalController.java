package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.item.RentalRequestInfo;
import com.example.springwebservice.domain.posts.Posts;
import com.example.springwebservice.domain.posts.PostsRepository;
import com.example.springwebservice.service.RentalService;
import com.example.springwebservice.web.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RentalController {
    private RentalService rentalService;
    private PostsRepository postsRepository;

//    @PostMapping(value="/recommendCabinet")
//    public Integer recommendCabinet(@RequestBody RentalRequestInfo dto){
//
//        return rentalService.findCabinet(dto);
//    }

//    @RequestMapping(value="/recommendCabinet",method={ RequestMethod.GET, RequestMethod.POST })
    @PostMapping(path = "/recommendCabinet")
    @GetMapping(path = "/recommendCabinet")
    public Integer recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Category Idx : " + info.getCategory_idx());
        System.out.println("Start time : " + info.getStart());

        return info.getUser_idx();
    }

    @RequestMapping(value="/posts",method={ RequestMethod.GET, RequestMethod.POST })
    public List<Posts> savePosts(@RequestBody PostsSaveRequestDto dto){
        System.out.println("posts : " + dto.getAuthor());
        postsRepository.save(dto.toEntity());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        return postsList;
    }
    @GetMapping(path = "/getItemInfo")
    public Integer recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Category Idx : " + info.getCategory_idx());
        System.out.println("Start time : " + info.getStart());

        return info.getUser_idx();
    }

}
