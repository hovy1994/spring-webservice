package com.example.springwebservice.web;

import com.example.springwebservice.domain.inquiry.Inquiry;
import com.example.springwebservice.domain.inquiry.InquiryRepository;
import com.example.springwebservice.domain.posts.Posts;
import com.example.springwebservice.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class WebRestController {

    private PostsRepository postsRepository;
    private InquiryRepository inquiryRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld"; // helloworld 문자열을 json 형태로 반환
    }

    @RequestMapping(value="/request", method={ RequestMethod.GET, RequestMethod.POST })
    public String returnhello(){
        return "hello";
    }


    @RequestMapping(value="/inquiry",method={ RequestMethod.GET, RequestMethod.POST })
    public Inquiry saveInquiry(@RequestBody InquirySaveRequestDto dto){

        inquiryRepository.save(dto.toEntity());
        List<Inquiry> inquiryList = inquiryRepository.findAll();

        Inquiry inquiry = inquiryList.get(0);
        return inquiry;
    }


    //@PostMapping(path = "/getKakaoAuth")
    @RequestMapping(value="/getKakaoAuth",method={ RequestMethod.GET, RequestMethod.POST })
    public String echoKakao(@RequestBody HashMap<String,String> map) {
        //System.out.println("id : " + map[ID]);
        map.put("USER_NAME","name");
        map.put("USER_PHONE","000-0000-0000");
        map.put("USER_PW","1234");
        map.put("USER_NICKNAME","nick");
        return map.get("id");
    }


    @RequestMapping(value="/posts",method={ RequestMethod.GET, RequestMethod.POST })
    public Posts savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        return posts;
    }

    @RequestMapping("/board/setposts")
    public void getBoard(){
        PostsSaveRequestDto postsSaveRequestDto = new PostsSaveRequestDto();
        postsSaveRequestDto.setAuthor("moomin1");
        postsSaveRequestDto.setTitle("title1");
        postsSaveRequestDto.setContent("content1");
        postsRepository.save(postsSaveRequestDto.toEntity());
        //return postsSaveRequestDto;
    }

    @GetMapping("/board/getposts")
    public Posts getBoard2(){
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        return posts;
        //assertThat(posts.getContent(), is("테스트 본문"));
        //postsRepository.save(postsSaveRequestDto.toEntity());
        //return postsSaveRequestDto;
    }
}
