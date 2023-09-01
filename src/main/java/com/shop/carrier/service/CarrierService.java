package com.shop.carrier.service;

import java.util.List;

import com.shop.carrier.data.dto.ResponseDto;
import com.shop.carrier.data.entity.Carrier;

public interface CarrierService {

    List<Carrier> getAllCarriers();

    List<ResponseDto> getCarriersByBrand(String brand);

    ResponseDto convert(Carrier carrier); // convert 메서드 시그니처 변경

    Carrier getCarrierById(Long productId); // 추가된 부분
}