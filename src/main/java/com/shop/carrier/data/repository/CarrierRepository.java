package com.shop.carrier.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.carrier.data.entity.Carrier; // 변경된 엔티티 클래스명에 맞게 임포트 수정

public interface CarrierRepository extends JpaRepository<Carrier, Long> { // 인터페이스명도 변경
    @Query("SELECT c FROM Carrier c WHERE c.brand like %:brand%") // 엔티티명과 필드명도 변경
    List<Carrier> queryByBrand(String brand);

    @Query("SELECT c FROM Carrier c WHERE c.productId = :productId") // productId로 조회하는 쿼리 추가
    Carrier findByProductId(Long productId);

    // 케릭터 냉장고 커리문
    @Query("SELECT c FROM Carrier c WHERE c.price <= 1000000 and c.capacity <= 600 and (c.brand like :brand1 or c.brand like :brand2 or c.brand like :brand3) ORDER BY c.price DESC, c.capacity ASC")
    List<Carrier> selectAllCharacter(@Param("brand1") String brand1, @Param("brand2") String brand2,
            @Param("brand3") String brand3);

    // 가성비 냉장고powerConsumptionGrade
    @Query("SELECT c FROM Carrier c WHERE c.price <= 1000000 and c.capacity <= 1000 and (:brand IS NULL OR c.brand like %:brand%) and powerConsumptionGrade <= 5 and doorCount <= 4 and numberofusers <= 4 ORDER BY price DESC, capacity ASC")
    List<Carrier> selectAllgasungbi(@Param("brand") String brand);

    // 신혼부부 냉장고
    @Query("SELECT c FROM Carrier c WHERE c.price <= 800000 AND c.capacity <= 600 AND (:brand IS NULL OR c.brand like %:brand%) ORDER BY c.price DESC, c.capacity ASC")
    List<Carrier> selectAllNwl(@Param("brand") String brand);

    // 1인가구
    @Query("SELECT c FROM Carrier c WHERE c.price <= 600000 AND c.capacity <= 300 AND (:brand IS NULL OR c.brand like %:brand%) AND c.powerConsumptionGrade <= 5 AND c.doorCount <= 4 AND c.numberOfUsers <= 4 ORDER BY c.price DESC, c.capacity ASC, c.numberOfUsers ASC, c.doorCount ASC, c.powerConsumptionGrade DESC")
    List<Carrier> selectAllsingle(@Param("brand") String brand);

}
