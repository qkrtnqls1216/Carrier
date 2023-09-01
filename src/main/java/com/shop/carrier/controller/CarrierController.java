package com.shop.carrier.controller;

import java.util.List;
import java.util.stream.Collectors;

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
        // isMainPage 변수를 true로 설정하여 전달
        model.addAttribute("isMainPage", true);
        return "articles/main";
    }

    @GetMapping("/articles/{brand}")
    public String brand(Model model, @PathVariable String brand) {
        List<ResponseDto> carrierList = carrierservice.getCarriersByBrand(brand);
        model.addAttribute("carrierList", carrierList);
        // isMainPage 변수를 false로 설정하여 전달
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

    @GetMapping("articles/commend1")
    public String Commend1() {
        return "articles/commend1";
    }

    @GetMapping("articles/commend2")
    public String Commend12() {
        return "articles/commend2";
    }

    @GetMapping("articles/commend3")
    public String Commend3() {
        return "articles/commend3";
    }

    @GetMapping("articles/commend4")
    public String Commend4() {
        return "articles/commend4";
    }
}
