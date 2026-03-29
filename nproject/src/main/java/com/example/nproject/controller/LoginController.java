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
        return "login2";
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

    /**
     * 세션 만료 시간 연장 API
     * 클라이언트 측에서 호출하여 서버의 세션 유효 시간을 연장합니다.
     * @param session 현재 연결된 HTTP 세션 객체
     * @return 연장 성공 메시지 ("OK")
     */
    @PostMapping("/api/session/extend")
    @ResponseBody
    public String extendSession(jakarta.servlet.http.HttpSession session) {
        // 서버 측에서 이 API가 호출되는 것만으로도 Servlet 컨테이너에 의해 세션 유효 시간이 연장됨
        // 추가로 안전성 확보를 원하면 session.setMaxInactiveInterval(3600); 호출 가능
        return "OK";
    }
}
