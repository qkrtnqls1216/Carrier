package com.shop.carrier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.carrier.data.dto.ResponseDto;
import com.shop.carrier.data.entity.Carrier;
import com.shop.carrier.service.CarrierService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CarrierController {
    @Autowired
    private CarrierService carrierservice;

    @GetMapping("/articles/main")
    public String main(Model model) {
        List<Carrier> carrierList = carrierservice.getAllCarriers();
        model.addAttribute("carrierList", carrierList);
        // isMainPage 변수를 true로 설정하여 전달 - 작용안되는 부분
        model.addAttribute("isMainPage", true);
        return "articles/main";
    }

    @GetMapping("/articles/{brand}")
    public String brand(Model model, @PathVariable String brand) {
        List<ResponseDto> carrierList = carrierservice.getCarriersByBrand(brand);
        model.addAttribute("carrierList", carrierList);
        // isMainPage 변수를 false로 설정하여 전달 - 적용안안되는 부분
        model.addAttribute("isMainPage", false);
        return "articles/main";
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseDto>> search(@RequestParam String query) {
        List<ResponseDto> searchResults = carrierservice.getCarriersByBrand(query);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/articles/product-details/{productId}")
    public String getProductDetails(Model model, @PathVariable Long productId) {
        Carrier carrier = carrierservice.getCarrierById(productId); // 해당 productId에 해당하는 제품 정보 조회
        model.addAttribute("carrier", carrier);
        return "articles/productdetails"; // 상세 정보를 보여줄 페이지로 이동
    }

    // 캐릭터 냉장고 추천 조회 - 함수명변경
    @GetMapping("articles/commend1")
    public String Commend1(Model model) {
        List<Carrier> getCarrierCharacter = carrierservice.selectfindCharacter("KAKAO", "Ccomo", "SMEG");
        model.addAttribute("getCarrierCharacter", getCarrierCharacter);
        return "articles/commend1";
    }

    // 가성비 냉장고
    @GetMapping("articles/commend2")
    public String Commend12(Model model) {
        List<Carrier> getCarriergasungbi = carrierservice.selectfindgasungbi("all");
        model.addAttribute("getCarriergasungbi", getCarriergasungbi);
        return "articles/commend2";
    }

    // 신혼부부추천 냉장고
    @GetMapping("articles/commend3")
    public String Commend3(Model model) {
        List<Carrier> getCarrierNwl = carrierservice.selectfindNwl("all");
        model.addAttribute("getCarrierNwl", getCarrierNwl);
        return "articles/commend3";
    }

    // 1인가구 추천
    @GetMapping("articles/commend4")
    public String Commend4(Model model) {
        List<Carrier> selectfindsingle = carrierservice.selectfindsingle("all");
        model.addAttribute("selectfindsingle", selectfindsingle);
        return "articles/commend4";
    }
}
