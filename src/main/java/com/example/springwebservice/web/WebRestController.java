package com.example.springwebservice.web;

import com.example.springwebservice.domain.inquiry.Inquiry;
import com.example.springwebservice.domain.inquiry.InquiryRepository;
import com.example.springwebservice.domain.member.KakaoLoginTokenResponse;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.domain.posts.Posts;
import com.example.springwebservice.domain.posts.PostsRepository;
import com.example.springwebservice.service.KakaoLoginService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    private MemberRepository memberRepository;
    KakaoLoginService kakaoLoginService;

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
//    @RequestMapping(value="/getKakaoAuth",method={ RequestMethod.GET, RequestMethod.POST })
//    public String echoKakao(@RequestBody HashMap<String,String> map) {
//        //System.out.println("id : " + map[ID]);
//        MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
//
//        memberSaveRequestDto.setUSER_ID(map.get("id"));
//        memberSaveRequestDto.setUSER_NICKNAME(map.get("nickname"));
//        memberSaveRequestDto.setUSER_NAME("name");
//        memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
//        memberSaveRequestDto.setUSER_PW("1232");
//
//        memberRepository.save(memberSaveRequestDto.toEntity());
//        return map.get("id");
//    }
//    @PostMapping(path = "/getKakaoAuth")
//    public String echoKakao(@RequestBody KakaoLoginTokenResponse res) {
//        System.out.println("id : " + res.getId());
//        MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
//
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(res.getProperties());
//
//        String nickname = element.getAsJsonObject().get("nickname").getAsString();
//
//        memberSaveRequestDto.setUSER_ID(String.valueOf(res.getId()));
//        memberSaveRequestDto.setUSER_NICKNAME(nickname);
//        memberSaveRequestDto.setUSER_NAME("name");
//        memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
//        memberSaveRequestDto.setUSER_PW("1232");
//        return "id : " + res.getId();
//    }

    @PostMapping(path = "/getKakaoAuth")
    public String echoKakao(@RequestBody String res) {
        MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(res);

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        //JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

        String id = element.getAsJsonObject().get("id").getAsString();
        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        //String email = kakao_account.getAsJsonObject().get("email").getAsString();

        memberSaveRequestDto.setUSER_ID(String.valueOf(id));
        memberSaveRequestDto.setUSER_NICKNAME(nickname);
        memberSaveRequestDto.setUSER_NAME("name");
        memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
        memberSaveRequestDto.setUSER_PW("1232");

        memberRepository.save(memberSaveRequestDto.toEntity());
        //System.out.println("id : " + res.getId());

        return "nickname : " + nickname;
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
