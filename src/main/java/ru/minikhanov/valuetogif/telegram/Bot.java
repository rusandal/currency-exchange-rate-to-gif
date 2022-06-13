package ru.minikhanov.valuetogif.telegram;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.minikhanov.valuetogif.service.MyService;
import ru.minikhanov.valuetogif.utils.ApiClientCurrencyExchangeService;
import ru.minikhanov.valuetogif.utils.ApiClientGiphyService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${myapp.telegram.bot.name}")
    private String BOT_NAME;
    @Value("${myapp.telegram.bot.token}")
    private String BOT_TOKEN;
    private List<String> listSupportCurrency = List.of("EUR", "AUD", "RUB");
    private MyService myService;

    public Bot(MyService myService) {
        this.myService = myService;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message requestMess = update.getMessage();
            String chatId = requestMess.getChatId().toString();
            String response = parseMessage(requestMess.getText());
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(chatId);
            try {
                if (requestMess.getText().equals("/start")) {
                    responseMessage.setText(response);
                    execute(responseMessage);
                } else if (response.equals("Bad request")) {
                    responseMessage.setText("Нет такой.");
                    execute(responseMessage);
                } else {
                    SendAnimation responseAnimation = new SendAnimation();
                    responseAnimation.setChatId(chatId);
                    responseAnimation.setAnimation(new InputFile(response));
                    execute(responseAnimation);
                }
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String parseMessage(String textMsg) {
        String response;
        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if (textMsg.equals("/start"))
            response = "Приветствую.\nУкажите любую из валют и мы сравним ее отношение к USD вчера и сегодня.\nЕсли ваша валюта сегодня подорожала, то выдадим картинки из тематики #Rich ... если подешевела, то картинку из тематики #Broke.\n"+listSupportCurrency.toString();
        else if (listSupportCurrency.contains(textMsg))
            response = myService.getGif(textMsg);
        else
            response = "Bad request";

        return response;
    }


}
