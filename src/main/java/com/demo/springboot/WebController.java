package com.demo.springboot;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class WebController {

    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal) {
        LOGGER.info(String.format("User is authenticated: %s", isAuthenticated()));
        return "securedPage";
    }

    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        LOGGER.info(String.format("User is authenticated: %s", isAuthenticated()));
        return "index";
    }
}
