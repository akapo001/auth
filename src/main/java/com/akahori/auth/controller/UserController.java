package com.akahori.auth.controller;

import com.akahori.auth.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @RequestMapping
    public String index(Authentication authentication, Model model) {
        // 認証情報を取得
        UserModel userModel = (UserModel)authentication.getPrincipal();
        model.addAttribute("name", userModel.getName());
        return "user";
    }
}
