package ru.minikhanov.valuetogif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.minikhanov.valuetogif.telegram.Bot;

@SpringBootApplication
@EnableFeignClients
public class ValueToGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValueToGifApplication.class, args);
    }
}
