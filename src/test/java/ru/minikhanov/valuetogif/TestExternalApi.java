package ru.minikhanov.valuetogif;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.minikhanov.valuetogif.model.CurrencyInfo;
import ru.minikhanov.valuetogif.model.GiphyInfo;
import ru.minikhanov.valuetogif.service.MyService;
import ru.minikhanov.valuetogif.utils.ApiClientCurrencyExchangeService;
import ru.minikhanov.valuetogif.utils.ApiClientGiphyService;

@AutoConfigureMockMvc
@SpringBootTest
public class TestExternalApi {
    @Value("${myapp.currency.key}")
    private String currencyApiKey;
    @Value("${myapp.giphy.key}")
    private String giphyApiKey;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MyService myService;

    @Autowired
    private ApiClientGiphyService apiClientGiphyService;
    @Autowired
    private ApiClientCurrencyExchangeService apiClientCurrencyExchangeService;

    @Test
    public void giphyApiTest(){
        GiphyInfo rich = apiClientGiphyService.getGif(giphyApiKey, "rich");
        Assertions.assertFalse(rich.getUrl().isEmpty());
    }

    @Test
    public void currencyExchangeApiTest(){
        CurrencyInfo latestCurrencyExchange = apiClientCurrencyExchangeService.getLatestCurrencyExchange(currencyApiKey, "EUR", "USD");
        Assertions.assertTrue(latestCurrencyExchange.getRates().containsKey("EUR"));
        Assertions.assertEquals(latestCurrencyExchange.getBase(), "USD");
    }

    @Test
    public void currecyExchangeApiBadCurrencyName(){
        String currency = "EURO";
        Assertions.assertThrows(RuntimeException.class, ()->{
            myService.getGif(currency);});
    }
}
