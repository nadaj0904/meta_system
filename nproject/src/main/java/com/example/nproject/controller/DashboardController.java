package com.example.nproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/std-dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard() {
        return "std_dashboard";
    }
}
