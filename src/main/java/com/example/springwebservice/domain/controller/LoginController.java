package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.member.KakaoLoginTokenResponse;
import com.example.springwebservice.domain.member.LoginAuthInfo;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.service.KakaoLoginService;
import com.example.springwebservice.web.MemberSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {

    private KakaoLoginService kakaoLoginService;
    private MemberRepository memberRepository;

    @PostMapping(path = "/keyRequest")
    public KakaoLoginTokenResponse transferPublicKey() {
        KakaoLoginTokenResponse res = new KakaoLoginTokenResponse();

        res.setPubKey(kakaoLoginService.getPublicKey());

        return res;
    }

//	@PostMapping(path = "/encode")
//	public String encodePlainData(@RequestBody KakaoLoginTokenResponse res) {
//		return service.encode(res.getId());
//	}

    @PostMapping(path = "/decode")
    public String decodeEncryptedData(@RequestBody KakaoLoginTokenResponse res) {

        return kakaoLoginService.decode(res.getEncryptedUserId());
    }

    @PostMapping(path = "/joinAndLoginRequest")
    public KakaoLoginTokenResponse joinAndLogin(@RequestBody LoginAuthInfo info) {
        KakaoLoginTokenResponse res = new KakaoLoginTokenResponse();
        res.setLoggedIn(false);
        if(kakaoLoginService.join(info)) {
            if(!kakaoLoginService.isMember(info.getEncryptedUserId())){
                MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
                memberSaveRequestDto.setUSER_ID(kakaoLoginService.decode(info.getEncryptedUserId()));
                memberSaveRequestDto.setUSER_NICKNAME(info.getNickname());
//                memberSaveRequestDto.setUSER_NAME("name");
//                memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
//                memberSaveRequestDto.setUSER_PW("1232");

                memberRepository.save(memberSaveRequestDto.toEntity());
            }
            if (kakaoLoginService.login(info)) {
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
}
