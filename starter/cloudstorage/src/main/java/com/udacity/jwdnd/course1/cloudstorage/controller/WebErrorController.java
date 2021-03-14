package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class WebErrorController implements ErrorController {

    @GetMapping
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("updateFail", "Status code: " + status.toString() + " OOPS! Please make sure your input is correct.");
        return "result";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
