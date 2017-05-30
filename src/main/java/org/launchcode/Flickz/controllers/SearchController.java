package org.launchcode.Flickz.controllers;

import org.launchcode.Flickz.models.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melissa on 5/30/17.
 */

@Controller
public class SearchController extends AbstractController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String DisplaySearchForm(Model model){
        model.addAttribute("title", "Find Reviews");

        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String ProcessSearchForm(HttpServletRequest request, Model model){
        List<Review> reviews = reviewDao.findAll();
        List<Review> results = new ArrayList<>();
        String query = request.getParameter("film");
        for (int i = 0; i < reviews.size(); i++){
            if (reviews.get(i).getFilm().equals(query)){
                results.add(reviews.get(i));
            }
        }
            model.addAttribute("title","Search Results");
            model.addAttribute("reviews", results);
        return "results";
    }
}
