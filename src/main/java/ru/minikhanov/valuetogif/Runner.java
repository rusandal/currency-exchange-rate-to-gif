package ru.minikhanov.valuetogif;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.minikhanov.valuetogif.service.MyService;
import ru.minikhanov.valuetogif.telegram.Bot;

@Component
public class Runner implements CommandLineRunner {
    //private MyService myService;
    private Bot bot;

    public Runner(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
