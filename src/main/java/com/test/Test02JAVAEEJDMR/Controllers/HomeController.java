package com.test.Test02JAVAEEJDMR.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.Test02JAVAEEJDMR.Models.ProductoJDMR;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String create(ProductoJDMR productoJDMR) {
        return "home/index";
    }
}
