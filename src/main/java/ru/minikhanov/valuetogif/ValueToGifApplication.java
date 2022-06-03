package ru.minikhanov.valuetogif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ValueToGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValueToGifApplication.class, args);
    }

}
