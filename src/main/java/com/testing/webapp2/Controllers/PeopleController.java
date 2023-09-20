package com.testing.webapp2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @GetMapping()
    public String getIndex() {
        //получение всех людей из DAO
        return null;
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") long id, Model model){
        //получение конкртного человека из DAO
        return null;
    }


}
