package ru.minikhanov.valuetogif.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.minikhanov.valuetogif.model.CurrencyInfo;

@FeignClient(value = "${myapp.feign.currencyexchange.config.name}", url = "${myapp.feign.currencyexchange.config.url}")
public interface ApiClientCurrencyExchangeService {
    @GetMapping("/latest.json")
    CurrencyInfo getLatestCurrencyExchange(@RequestParam(value = "app_id") String appId, @RequestParam(value = "symbols") String currency, @RequestParam(value = "base") String baseCurrency);
    @GetMapping("/historical/{date}.json")
    CurrencyInfo getCurrencyExchangeOnDate(@PathVariable String date, @RequestParam(value = "app_id") String appId, @RequestParam(value = "symbols") String currency, @RequestParam(value = "base") String baseCurrency);
}