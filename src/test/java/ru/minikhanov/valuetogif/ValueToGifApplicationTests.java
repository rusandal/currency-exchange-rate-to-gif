package ru.minikhanov.valuetogif;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;
import ru.minikhanov.valuetogif.model.CurrencyInfo;
import ru.minikhanov.valuetogif.model.GiphyInfo;
import ru.minikhanov.valuetogif.utils.ApiClientCurrencyExchangeService;
import ru.minikhanov.valuetogif.utils.ApiClientGiphyService;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
class ValueToGifApplicationTests {
    @Value("${myapp.basecurrency}")
    private String baseCurrency;
    private static CurrencyInfo currencyInfoOnDate = new CurrencyInfo();
    private static CurrencyInfo currencyInfoLatet = new CurrencyInfo();
    private static GiphyInfo giphyInfo = new GiphyInfo();
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ApiClientGiphyService apiClientGiphyService;
    @MockBean
    private ApiClientCurrencyExchangeService apiClientCurrencyExchangeService;

    @BeforeAll
    public static void setData() {
        Map<String, Double> ratesOnDate = new HashMap<>();
        Map<String, Double> ratesLatest = new HashMap<>();
        ratesOnDate.put("EUR", 0.93);
        ratesLatest.put("EUR", 0.95);
        currencyInfoOnDate.setBase("USD");
        currencyInfoOnDate.setRates(ratesOnDate);
        currencyInfoLatet.setBase("USD");
        currencyInfoLatet.setRates(ratesLatest);

        giphyInfo.setId("123");
        giphyInfo.setUrl("https://media2.giphy.com/media/zWD9ER5oJLzgkPjV5v/200_d.gif?cid=d6df192568413d85d22be296e848000512c1b68580b2a998&rid=200_d.gif&ct=g");
    }

    @Test
    void contextLoads() {
    }


    @Test
    public void endpointGetTest() throws Exception {
        String currency = baseCurrency;
        Mockito.when(apiClientCurrencyExchangeService.getCurrencyExchangeOnDate(anyString(), anyString(), anyString(), anyString())).thenReturn(currencyInfoOnDate);
        Mockito.when(apiClientCurrencyExchangeService.getLatestCurrencyExchange(anyString(), anyString(), anyString())).thenReturn(currencyInfoLatet);
        Mockito.when(apiClientGiphyService.getGif(anyString(), anyString())).thenReturn(giphyInfo);
        MvcResult mvcResult = mvc.perform(get("/{currency}", currency))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        Assertions.assertTrue(model.containsKey("image"));
        String state = (String) model.get("image");
        Assertions.assertFalse(state.isEmpty());
    }

    @Test
    public void handlerExceptionTest() throws Exception {
        String currency = "EURO";
        Mockito.when(apiClientCurrencyExchangeService.getCurrencyExchangeOnDate(anyString(), anyString(), anyString(), anyString())).thenReturn(currencyInfoOnDate);
        Mockito.when(apiClientCurrencyExchangeService.getLatestCurrencyExchange(anyString(), anyString(), anyString())).thenReturn(currencyInfoLatet);
        Mockito.when(apiClientGiphyService.getGif(anyString(), anyString())).thenReturn(giphyInfo);
        MvcResult mvcResult = mvc.perform(get("/{currency}", currency))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        Assertions.assertTrue(model.containsKey("url"));
        Assertions.assertTrue(model.containsKey("message"));

    }
}
