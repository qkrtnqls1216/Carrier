// package com.shop.carrier.service;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.support.ConversionServiceFactoryBean;
// import org.springframework.core.convert.converter.Converter;

// import java.util.HashSet;
// import java.util.Set;

// @Configuration
// public class ConversionService {

//     @Bean
//     public ConversionServiceFactoryBean conversionService() {
//         ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
//         factoryBean.setConverters(getConverters());
//         return factoryBean;
//     }

//     private Set<Converter<?, ?>> getConverters() {
//         Set<Converter<?, ?>> converters = new HashSet<>();
//         // Add your converters here
//         return converters;
//     }
// }
