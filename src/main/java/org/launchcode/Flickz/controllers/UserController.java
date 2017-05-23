package org.launchcode.Flickz.controllers;

import org.launchcode.Flickz.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by melissa on 5/22/17.
 */

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("Title", "Welcome to Flickz");

        return "index";
    }
}
