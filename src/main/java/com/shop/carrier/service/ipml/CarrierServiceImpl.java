package com.shop.carrier.service.ipml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.shop.carrier.data.dto.ResponseDto;
import com.shop.carrier.data.entity.Carrier;
import com.shop.carrier.data.repository.CarrierRepository;
import com.shop.carrier.service.CarrierService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrierServiceImpl implements CarrierService {
    private final CarrierRepository carrierRepository;
    private final ConversionService conversionService;

    @Autowired
    public CarrierServiceImpl(CarrierRepository carrierRepository, ConversionService conversionService) {
        this.carrierRepository = carrierRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<Carrier> getAllCarriers() {
        return carrierRepository.findAll();
    }

    @Override
    public List<ResponseDto> getCarriersByBrand(String brand) {
        List<Carrier> carriers = carrierRepository.queryByBrand(brand);

        return carriers.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto convert(Carrier carrier) {
        return conversionService.convert(carrier, ResponseDto.class);
    }
}
