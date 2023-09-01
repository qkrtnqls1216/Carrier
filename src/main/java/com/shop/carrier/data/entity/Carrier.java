package com.shop.carrier.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.shop.carrier.data.entity.Carrier.CarrierId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "carrier")
@IdClass(Carrier.CarrierId.class)
public class Carrier implements Serializable {

    @Id
    @Column(name = "product_id") // productId 필드 추가
    private Long productId;

    @Column(name = "brand")
    private String brand;

    @Id
    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private int price;

    @Column(name = "capacity_L")
    private double capacity;

    @Column(name = "size_cm")
    private String size;

    @Column(name = "powerconsumptiongrade")
    private String powerConsumptionGrade;

    @Column(name = "door_count")
    private int doorCount;

    @Column(name = "image")
    private String image;

    @Column(name = "numberofusers")
    private int numberOfUsers;

    public Carrier() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPowerConsumptionGrade() {
        return powerConsumptionGrade;
    }

    public void setPowerConsumptionGrade(String powerConsumptionGrade) {
        this.powerConsumptionGrade = powerConsumptionGrade;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public static class CarrierId implements Serializable {
        private Long productId; // productId 필드 추가
        private String productName;
    }
}
