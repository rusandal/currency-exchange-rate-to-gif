package ru.minikhanov.valuetogif.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.minikhanov.valuetogif.model.CurrencyInfo;
import ru.minikhanov.valuetogif.model.GiphyInfo;

@FeignClient(value = "${myapp.feign.giphy.config.name}", url = "${myapp.feign.giphy.config.url}")
public interface ApiClientGiphyService {
    @GetMapping("/random")
    GiphyInfo getGif(@RequestParam(value = "api_key") String apiKey, @RequestParam(value = "tag") String category);
}
