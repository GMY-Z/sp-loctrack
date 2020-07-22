package io.github.gmy.sp.loctrack.controller;
import io.github.gmy.sp.loctrack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam(value = "phone",required = false) String phone,
                        @RequestParam(value = "password",required = false) String password,
                        HttpServletRequest request){

        if(loginService.login(phone,password)){
            request.getSession().setAttribute("phone", phone);
            return "redirect:/map";
        }

        return "/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("phone");
        return "redirect:/";
    }
}
