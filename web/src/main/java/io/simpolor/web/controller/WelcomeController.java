package io.simpolor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping({"/", "/index", "/welcome", "/main"})
    @ResponseBody
    public ModelAndView welcome(ModelAndView mav) {

        mav.setViewName("main");
        return mav;
    }
}
