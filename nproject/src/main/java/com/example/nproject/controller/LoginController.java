package com.example.nproject.controller;

import com.example.nproject.dto.LoginDto;
import com.example.nproject.dto.UserDto;
import com.example.nproject.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginDto loginDto, jakarta.servlet.http.HttpSession session) {
        UserDto user = loginMapper.login(loginDto.getUserId(), loginDto.getPassword());

        if (user != null) {
            session.setAttribute("LOGIN_USER", user);
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @GetMapping("/logout")
    public String logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
