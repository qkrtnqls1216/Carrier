package com.shop.carrier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// 컨트롤러에서는 Carrier 경로로 요청이 들어왔을 때 mustache페이지를 보여줌
@Controller
public class Home_Controller {

    @GetMapping("/")
    public String index(Model model) {
        // 모델에 데이터를 추가하거나 다른 처리가 필요하다면 여기에 작성
        
        return "articles/main"; // main.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/main")
    public String myPage() {
        return "articles/main"; // main.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/SAMSUNG")
    public String SAMSUNG() {
        return "articles/SAMSUNG"; // SAMSUNG.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/LG")
    public String LG() {
        return "articles/LG"; // LG.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/KAKAO")
    public String KAKAO() {
        return "articles/KAKAO"; // KAKAO.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/SMEG")
    public String SMEG() {
        return "articles/SMEG"; // SMEG.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/CCOMO")
    public String CCOMO() {
        return "articles/CCOMO"; // CCOMO.mustache 파일에서 {{> header}} 추가
    }

    @GetMapping("articles/MIDDLE")
    public String MIDDLE() {
        return "articles/MIDDLE"; // MIDDLE.mustache 파일에서 {{> header}} 추가
    }    

}
