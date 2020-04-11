package com.akahori.auth.controller;

import com.akahori.auth.model.AccountModel;
import com.akahori.auth.service.UserDetailsServiceImpl;
import com.akahori.auth.utils.MessageResourceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @RequestMapping({"/", "/login"})
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            MessageResourceHelper.addErrorMessage(messageSource, model, "error.authentication_fail");
        }
        return "login";
    }


    @RequestMapping(value="account")
    String accountForm() {
        return "accountForm";
    }

    @RequestMapping(value = "account", method = RequestMethod.POST)
    String create(@Validated AccountModel form, BindingResult bindingResult) {
        userDetailsService.create(form);
        return "redirect:/account/complete";
    }

    @RequestMapping(value = "account/complete", method = RequestMethod.GET)
    String createFinish() {
        return "redirect:/login";
    }

}

