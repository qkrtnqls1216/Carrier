// package com.shop.carrier.data.entity;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;

// @Entity
// @AllArgsConstructor
// @NoArgsConstructor
// @Getter
// public class Product {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;
// private String brand;
// private String productName;
// private String image;
// // 다른 필드 추가

// // 게터와 세터 메서드

// public Long getId() {
// return id;
// }

// public void setId(Long id) {
// this.id = id;
// }

// public String getBrand() {
// return brand;
// }

// public void setBrand(String brand) {
// this.brand = brand;
// }

// public String getProductName() {
// return productName;
// }

// public void setProductName(String productName) {
// this.productName = productName;
// }

// // 다른 게터와 세터 메서드들

// @Override
// public String toString() {
// return "Product [id=" + id + ", brand=" + brand + ", productName=" +
// productName + "]";
// }

// public String getImage() {
// return image;
// }

// public void setImage(String image) {
// this.image = image;
// }
// }
