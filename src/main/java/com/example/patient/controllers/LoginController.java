package com.example.patient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class LoginController {
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("titre","Connectez-vous ");
        return "login/login";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "/login";
    }
    @GetMapping("/")
    public String dashboard(Model model){
        model.addAttribute("titre","Votre Dashboard");
        return "dashboard/dashboard";
    }


}
