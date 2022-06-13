package ru.minikhanov.valuetogif.service;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.minikhanov.valuetogif.model.CurrencyInfo;
import ru.minikhanov.valuetogif.model.GiphyInfo;
import ru.minikhanov.valuetogif.utils.ApiClientCurrencyExchangeService;
import ru.minikhanov.valuetogif.utils.ApiClientGiphyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        CurrencyInfo yesterdayCurrencyInfo;
        CurrencyInfo latestCurrencyInfo;
        GiphyInfo giphyInfo;
        try {
            yesterdayCurrencyInfo = apiClientCurrencyExchangeService.getCurrencyExchangeOnDate(yesterday, currencyApiKey, currency, baseCurrency);
            latestCurrencyInfo = apiClientCurrencyExchangeService.getLatestCurrencyExchange(currencyApiKey, currency, baseCurrency);
            if (yesterdayCurrencyInfo.getRates().isEmpty() || latestCurrencyInfo.getRates().isEmpty()) {
                throw new RuntimeException("Currency info is empty. Please check currency name.");
            }
            double yesterdayRate = latestCurrencyInfo.getRates().get(currency);
            double latestRate = yesterdayCurrencyInfo.getRates().get(currency);

            if (yesterdayRate < latestRate) {
                giphyInfo = apiClientGiphyService.getGif(giphyApiKey, "rich");
            } else {
                giphyInfo = apiClientGiphyService.getGif(giphyApiKey, "broke");
            }
        } catch (FeignException fex) {
            throw new RuntimeException("External service is broken. Status " + fex.status());
        }
        return giphyInfo.getUrl();
    }
}
