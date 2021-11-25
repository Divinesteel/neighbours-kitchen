package com.application.neighbourskitchen.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    @ResponseBody
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session;
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "logout";
    }
}
