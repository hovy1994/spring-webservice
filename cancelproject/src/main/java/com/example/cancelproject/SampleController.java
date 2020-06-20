package com.example.cancelproject;

import com.example.cancelproject.kakaoCancel;
import com.example.cancelproject.KakaoPayCancelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class SampleController {

    @Setter(onMethod_ = @Autowired)
    private kakaoCancel kakaocancel;

    @GetMapping("/Cancel")
    public void CancelGet(){
    }

    @PostMapping(value = "/Cancel")
    public String CancelPost() {
        log.info("kakaoCancel post............................................");

        return "redirect:" + kakaocancel.kakaoCancelGO();
    }
}