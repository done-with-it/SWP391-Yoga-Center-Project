package com.fptyoga.yogacenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class adminController {

    @GetMapping("/admin")
    public String admin() {
        return "Admin/admin";
    }

}
