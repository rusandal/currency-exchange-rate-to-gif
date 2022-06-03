package ru.minikhanov.valuetogif.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.minikhanov.valuetogif.model.CurrencyInfo;
import ru.minikhanov.valuetogif.model.GiphyInfo;
import ru.minikhanov.valuetogif.utils.ApiClientCurrencyExchangeService;
import ru.minikhanov.valuetogif.utils.ApiClientGiphyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MyService {
    @Value("${myapp.currency.key}")
    private String currencyApiKey;
    @Value("${myapp.giphy.key}")
    private String giphyApiKey;
    @Value("${myapp.basecurrency}")
    private String baseCurrency;

    private final ApiClientCurrencyExchangeService apiClientCurrencyExchangeService;
    private final ApiClientGiphyService apiClientGiphyService;

    public MyService(ApiClientCurrencyExchangeService apiClientCurrencyExchangeService, ApiClientGiphyService apiClientGiphyService) {
        this.apiClientCurrencyExchangeService = apiClientCurrencyExchangeService;
        this.apiClientGiphyService = apiClientGiphyService;
    }

    public String getGif(String currency) {
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE);
        CurrencyInfo yesterdayCurrencyInfo = apiClientCurrencyExchangeService.getCurrencyExchangeOnDate(yesterday, currencyApiKey, currency, baseCurrency);
        CurrencyInfo latestCurrencyInfo = apiClientCurrencyExchangeService.getLatestCurrencyExchange(currencyApiKey, currency, baseCurrency);
        double yesterdayRate = latestCurrencyInfo.getRates().get(currency);
        double latestRate = yesterdayCurrencyInfo.getRates().get(currency);
        GiphyInfo giphyInfo;
        if(yesterdayRate<latestRate){
            giphyInfo = apiClientGiphyService.getGif(giphyApiKey, "rich");
        }
        else {
            giphyInfo = apiClientGiphyService.getGif(giphyApiKey, "broke");
        }
        return giphyInfo.getUrl();

    }
}
