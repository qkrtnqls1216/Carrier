package com.shop.carrier.service;

import java.util.List;

import com.shop.carrier.data.dto.ResponseDto;
import com.shop.carrier.data.entity.Carrier;

public interface CarrierService {

    List<Carrier> getAllCarriers();

    List<ResponseDto> getCarriersByBrand(String brand);

    ResponseDto convert(Carrier carrier); // convert 메서드 시그니처 변경

    Carrier getCarrierById(Long productId); // 추가된 부분

    // 케릭터 냉장고
    List<Carrier> selectfindCharacter(String brand1, String brand2, String brand3);

    List<ResponseDto> getCarrierCharacter(String brand1, String brand2, String brand3);

    ResponseDto Character(Carrier carrier);

    Carrier getCarrierCharacterId(Long productId);

    // 가성비 냉장고
    List<Carrier> selectfindgasungbi(String brand);

    List<ResponseDto> getCarriergasungbi(String brand);

    ResponseDto gasungbi(Carrier carrier);

    Carrier getCarriergasungbiId(Long productId);

    // 신혼부부 추천 냉장고
    List<Carrier> selectfindNwl(String brand);

    List<ResponseDto> getCarriersNwl(String brand);

    ResponseDto Nwl(Carrier carrier);

    Carrier getCarrierNwl(Long productId);

    // 1인가구 추천 냉장고
    List<Carrier> selectfindsingle(String brand);

    List<ResponseDto> getCarriersingle(String brand);

    ResponseDto single(Carrier carrier);

    Carrier getCarriersingle(Long productId);
}