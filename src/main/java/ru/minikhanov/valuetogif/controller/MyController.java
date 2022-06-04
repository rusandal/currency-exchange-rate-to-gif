package ru.minikhanov.valuetogif.controller;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.minikhanov.valuetogif.service.MyService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;

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

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndViewErr = new ModelAndView("error");
        modelAndViewErr.addObject("url", req.getRequestURL());
        modelAndViewErr.addObject("message", ex.getMessage());
        return modelAndViewErr;
    }
}
