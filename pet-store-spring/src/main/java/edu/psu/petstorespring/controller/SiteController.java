package edu.psu.petstorespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @GetMapping("/about")
    public String about(){return "about";}

    @GetMapping("/contact")
    public String contact(){return "contact";}

}
