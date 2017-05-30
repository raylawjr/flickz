package org.launchcode.Flickz.controllers;


import org.launchcode.Flickz.models.Review;
import org.launchcode.Flickz.models.User;
import org.launchcode.Flickz.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by melissa on 5/22/17.
 */

@Controller
@RequestMapping("")
public class UserController extends AbstractController {

    @RequestMapping(value = "")
    public String index(HttpServletRequest request, Model model){

        User author = getUserFromSession(request.getSession());

        if (author == null){
            model.addAttribute("title", "Welcome to Flickz, guest");
        }
        else {
            model.addAttribute("title", "Welcome to Flickz, "+author.getUsername());
        }

        List<Review> reviews = reviewDao.findAll();
        model.addAttribute("reviews", reviews);

        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String DisplayRegistrationForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Create Flickz Account");
        return "registration";
    }

    @RequestMapping(value = "/register",  method = RequestMethod.POST)
    public String ProcessRegistrationForm(HttpServletRequest request, Model model, @ModelAttribute @Valid User user, Errors errors, String password_conf){

        model.addAttribute(user);
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User existingUser = userDao.findByUsername(username);
        // if user already exists
        if (existingUser != null) {
            model.addAttribute("username_error", "A user with that username already exists! Please choose a different username.");
            model.addAttribute("title", "Create Flickz Account");
            return "registration";
        }

        // if passwords don't match
        boolean passwordsMatch = true;
        if (user.getPassword() == null || password_conf == null
                || !user.getPassword().equals(password_conf)) {
            passwordsMatch = false;
            user.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (!errors.hasErrors() && passwordsMatch) {
            userDao.save(user);
            return "redirect:";
        }
        model.addAttribute("title", "Create Flickz Account");
        return "registration";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String DisplayLoginForm(Model model){
        model.addAttribute("title", "Log-in to Flickz");
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String ProcessLoginForm(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.findByUsername(username);

        if (user == null) {
            model.addAttribute("username_error", "There is no user with username: <b>"+username+"</b>");
            return "login";
        }

        if (!user.getPassword().equals(password)) {
            model.addAttribute("password_error", "Invalid password");
            return "login";
        }

        setUserInSession(request.getSession(), user);
        return "redirect:";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:";
    }
}
