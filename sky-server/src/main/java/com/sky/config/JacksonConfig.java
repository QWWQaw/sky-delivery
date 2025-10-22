//package com.sky.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sky.json.JacksonObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//@Slf4j
//public class JacksonConfig {
//
//    @Bean
//    @Primary
//    public ObjectMapper jacksonObjectMapper() {
//        log.info("Initializing custom JacksonObjectMapper");
//        return new JacksonObjectMapper();
//    }
//}
