package com.testing.webapp2.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class SimpleController {

    @GetMapping("/hello")
    public String getHello(HttpServletRequest request, Model model) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        model.addAttribute("title", "HelloPage");
        model.addAttribute("helloMessage", "Hello, " + name + " " + surname);
        System.out.println("Hello, " + name + " " + surname);

        return "simple/hello";
    }

    @GetMapping("/goodbye")
    public String getGoodbye(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "surname", required = false) String surname,
                             Model model) {

        model.addAttribute("title", "GoodbyePage");
        model.addAttribute("goodbyeMessage", "Goodbye, " + name + " " + surname);
        System.out.println("Goodbye, " + name + " " + surname);

        return "simple/goodbye";
    }

    @GetMapping("/calculator")
    public String getCalc(@RequestParam(value = "a", required = false) double num1,
                          @RequestParam(value = "b", required = false) double num2,
                          @RequestParam(value = "action", required = false) String act,
                          Model model) {
        double res;
        switch (act) {
            case "plus":
                res = num1 + num2;
                System.out.println("yeah " + res);
                break;
            case "minus":
                res = num1 - num2;
                System.out.println("yeah " + res);
                break;
            case "multiply":
                res = num1 * num2;
                System.out.println("yeah " + res);
                break;
            case "division":
                res = num1 / num2;
                System.out.println("yeah " + res);
                break;
            default: res = 0.;

        }
        model.addAttribute("num1", num1+"");
        model.addAttribute("num2", num2+"");
        model.addAttribute("action", act+"");
        model.addAttribute("result", res + "");
        return "simple/calculator";
    }

    @GetMapping("/exit")
    public String getExit() {
        return "simple/exit";
    }
}
