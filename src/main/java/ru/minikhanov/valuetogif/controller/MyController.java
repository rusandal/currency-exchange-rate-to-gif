package ru.minikhanov.valuetogif.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.minikhanov.valuetogif.service.MyService;

@RestController
public class MyController {
    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/{currency}")
    public ModelAndView getResult(@PathVariable("currency") String currency){
        ModelAndView modelAndView = new ModelAndView("result");
        String gifUrl = myService.getGif(currency);
        modelAndView.addObject("image", gifUrl);
        return modelAndView;
    }
}
