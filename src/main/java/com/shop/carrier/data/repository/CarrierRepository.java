package com.shop.carrier.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.carrier.data.dto.ResponseDto;
import com.shop.carrier.data.entity.Carrier; // 변경된 엔티티 클래스명에 맞게 임포트 수정

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Long> { // 인터페이스명도 변경
    @Query("SELECT c.brand, c.productName, c.image FROM Carrier c WHERE c.brand = :brand") // 엔티티명과 필드명도 변경
    List<Carrier> queryByBrand(String brand);

    @Query("SELECT c FROM Carrier c WHERE c.productId = :productId") // productId로 조회하는 쿼리 추가
    Carrier findByProductId(Long productId);
}
