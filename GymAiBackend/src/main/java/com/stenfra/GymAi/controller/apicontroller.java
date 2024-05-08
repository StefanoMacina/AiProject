package com.stenfra.GymAi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class apicontroller {

    @GetMapping("/demo")
    public String some(){
        return "helddo";
    }
}
