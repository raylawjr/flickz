package org.launchcode.Flickz.controllers;

import org.launchcode.Flickz.models.User;
import org.launchcode.Flickz.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String DisplayRegistrationForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Create Account");
        return "registration";
    }

    @RequestMapping(value = "/register",  method = RequestMethod.POST)
    public String ProcessRegistrationForm(Model model, @ModelAttribute @Valid User user, Errors errors, String password_conf){

        model.addAttribute(user);
        boolean passwordsMatch = true;
        if (user.getPassword() == null || password_conf == null
                || !user.getPassword().equals(password_conf)) {
            passwordsMatch = false;
            user.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (!errors.hasErrors() && passwordsMatch) {
            userDao.save(user);
            return "index";
        }

        return "registration";
    }
}
