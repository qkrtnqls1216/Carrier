package com.shop.carrier.service.ipml;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.shop.carrier.data.dto.ResponseDto;
import com.shop.carrier.data.entity.Carrier;
import com.shop.carrier.data.repository.CarrierRepository;
import com.shop.carrier.service.CarrierService;

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
        ResponseDto dto = new ResponseDto();
        dto.setBrand(carrier.getBrand());
        dto.setImage(carrier.getImage());
        dto.setProductId(carrier.getProductId());
        dto.setProductName(carrier.getProductName());
        dto.setCapacity(carrier.getCapacity());
        dto.setPrice(carrier.getPrice());
        dto.setSize(carrier.getSize());
        return dto;
    }

    @Override
    public Carrier getCarrierById(Long productId) {
        return carrierRepository.findByProductId(productId);
    }

    // 캐릭터 냉장고 추천
    @Override
    public List<Carrier> selectfindCharacter(String brand1, String brand2, String brand3) {
        return carrierRepository.selectAllCharacter(brand1, brand2,
                brand3);
    }

    @Override
    public List<ResponseDto> getCarrierCharacter(String brand1, String brand2, String brand3) {
        List<Carrier> carriers = carrierRepository.selectAllCharacter(brand1, brand2, brand3);

        return carriers.stream()
                .map(this::Character)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto Character(Carrier carrier) {
        ResponseDto dto = new ResponseDto();
        dto.setBrand(carrier.getBrand());
        dto.setImage(carrier.getImage());
        dto.setProductId(carrier.getProductId());
        dto.setProductName(carrier.getProductName());
        dto.setCapacity(carrier.getCapacity());
        dto.setPrice(carrier.getPrice());
        dto.setSize(carrier.getSize());
        return dto;
    }

    @Override
    public Carrier getCarrierCharacterId(Long productId) {
        return carrierRepository.findByProductId(productId);
    }

    // 신혼 부부 추천 냉장고
    @Override
    public List<Carrier> selectfindNwl(String brand) {
        if ("all".equalsIgnoreCase(brand)) {
            brand = null;
        }
        return carrierRepository.selectAllNwl(brand);
    }

    @Override
    public List<ResponseDto> getCarriersNwl(String brand) {
        List<Carrier> carriers = carrierRepository.selectAllNwl(brand);

        return carriers.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto Nwl(Carrier carrier) {
        ResponseDto dto = new ResponseDto();
        dto.setBrand(carrier.getBrand());
        dto.setImage(carrier.getImage());
        dto.setProductId(carrier.getProductId());
        dto.setProductName(carrier.getProductName());
        dto.setCapacity(carrier.getCapacity());
        dto.setPrice(carrier.getPrice());
        dto.setSize(carrier.getSize());
        return dto;
    }

    @Override
    public Carrier getCarrierNwl(Long productId) {
        return carrierRepository.findByProductId(productId);
    }

    // 1인가구 냉장고 추천
    @Override
    public List<Carrier> selectfindsingle(String brand) {
        if ("all".equalsIgnoreCase(brand)) {
            brand = null;
        }
        return carrierRepository.selectAllsingle(brand);
    }

    @Override
    public List<ResponseDto> getCarriersingle(String brand) {
        List<Carrier> carriers = carrierRepository.selectAllsingle(brand);

        return carriers.stream()
                .map(this::single)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto single(Carrier carrier) {
        ResponseDto dto = new ResponseDto();
        dto.setBrand(carrier.getBrand());
        dto.setImage(carrier.getImage());
        dto.setProductId(carrier.getProductId());
        dto.setProductName(carrier.getProductName());
        dto.setCapacity(carrier.getCapacity());
        dto.setPrice(carrier.getPrice());
        dto.setSize(carrier.getSize());
        return dto;
    }

    @Override
    public Carrier getCarriersingle(Long productId) {
        return carrierRepository.findByProductId(productId);
    }

    // 가성비 냉장고 추천
    @Override
    public List<Carrier> selectfindgasungbi(String brand) {
        if ("all".equalsIgnoreCase(brand)) {
            brand = null;
        }
        return carrierRepository.selectAllgasungbi(brand);
    }

    @Override
    public List<ResponseDto> getCarriergasungbi(String brand) {
        List<Carrier> carriers = carrierRepository.selectAllgasungbi(brand);

        return carriers.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto gasungbi(Carrier carrier) {
        ResponseDto dto = new ResponseDto();
        dto.setBrand(carrier.getBrand());
        dto.setImage(carrier.getImage());
        dto.setProductId(carrier.getProductId());
        dto.setProductName(carrier.getProductName());
        dto.setCapacity(carrier.getCapacity());
        dto.setPrice(carrier.getPrice());
        dto.setSize(carrier.getSize());
        return dto;
    }

    @Override
    public Carrier getCarriergasungbiId(Long productId) {
        return carrierRepository.findByProductId(productId);
    }
}