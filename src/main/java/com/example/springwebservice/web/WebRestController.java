package com.example.springwebservice.web;

import com.example.springwebservice.domain.inquiry.Inquiry;
import com.example.springwebservice.domain.inquiry.InquiryRepository;
import com.example.springwebservice.domain.member.LoginAuthInfo;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.cabinet.CabinetRepository;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.item.ItemRepository;
import com.example.springwebservice.domain.category.CategoryRepository;
import com.example.springwebservice.domain.category.Category;
import com.example.springwebservice.domain.member.KakaoLoginTokenResponse;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.domain.posts.Posts;
import com.example.springwebservice.domain.posts.PostsRepository;
import com.example.springwebservice.service.KakaoLoginService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RestController // @ResponseBody를 모든 메소드에 적용
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class WebRestController {

    private PostsRepository postsRepository;
    private InquiryRepository inquiryRepository;
    private MemberRepository memberRepository;
    KakaoLoginService kakaoLoginService;


    public WebRestController(KakaoLoginService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld"; // helloworld 문자열을 json 형태로 반환
    }

    @RequestMapping(value="/request", method={ RequestMethod.GET, RequestMethod.POST })
    public String returnhello(){
        return "hello";
    }


    @RequestMapping(value="/inquiry",method={ RequestMethod.GET, RequestMethod.POST })
    public Inquiry saveInquiry(@RequestBody InquirySaveRequestDto dto) {
        inquiryRepository.save(dto.toEntity());
        List<Inquiry> inquiryList = inquiryRepository.findAll();

        Inquiry inquiry = inquiryList.get(0);
        return inquiry;
    }

    private KakaoLoginService service;



//	@PostMapping(path = "/getKakaoAuth")
//	public String echoKakao(@RequestBody KakaoLoginTokenResponse res) {
//		System.out.println("id : " + res.getId());
//		return "id : " + res.getId();
//	}

    @PostMapping(path = "/keyRequest")
    public KakaoLoginTokenResponse transferPublicKey() {
        KakaoLoginTokenResponse res = new KakaoLoginTokenResponse();
        res.setPubKey(service.getPublicKey());
        return res;
    }

//	@PostMapping(path = "/encode")
//	public String encodePlainData(@RequestBody KakaoLoginTokenResponse res) {
//		return service.encode(res.getId());
//	}

    @PostMapping(path = "/decode")
    public String decodeEncryptedData(@RequestBody KakaoLoginTokenResponse res) {
        return service.decode(res.getEncryptedUserId());
    }

    @PostMapping(path = "/joinAndLoginRequest")
    public KakaoLoginTokenResponse joinAndLogin(@RequestBody LoginAuthInfo info) {
        KakaoLoginTokenResponse res = new KakaoLoginTokenResponse();
        res.setLoggedIn(false);
        if(service.join(info)) {
            if (service.login(info)) {
                res.setLoggedIn(true);
            }
        }
        return res;
    }
//    @PostMapping(path = "/getKakaoAuth")
//    public String echoKakao(@RequestBody String res) {
//        MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
//
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(res);
//
//        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//        //JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//        String id = element.getAsJsonObject().get("id").getAsString();
//        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//        //String email = kakao_account.getAsJsonObject().get("email").getAsString();
//
//        memberSaveRequestDto.setUSER_ID(String.valueOf(id));
//        memberSaveRequestDto.setUSER_NICKNAME(nickname);
//        memberSaveRequestDto.setUSER_NAME("name");
//        memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
//        memberSaveRequestDto.setUSER_PW("1232");
//
//        memberRepository.save(memberSaveRequestDto.toEntity());
//        //System.out.println("id : " + res.getId());
//
//        return "nickname : " + nickname;
//    }

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
