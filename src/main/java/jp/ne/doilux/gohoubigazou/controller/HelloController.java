package jp.ne.doilux.gohoubigazou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("name", "World");
        return "index";
    }
}
