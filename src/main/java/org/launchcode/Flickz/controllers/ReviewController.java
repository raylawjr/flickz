package org.launchcode.Flickz.controllers;

import org.launchcode.Flickz.models.Review;
import org.launchcode.Flickz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by melissa on 5/29/17.
 */

@Controller
public class ReviewController extends AbstractController {

    @RequestMapping(value="/newreview", method= RequestMethod.GET)
    public String DisplayNewReviewForm(HttpServletRequest request, Model model) {
        model.addAttribute(new Review());
        User author = getUserFromSession(request.getSession());

        if (author == null){
            model.addAttribute("title", "Welcome to Flickz, guest");
        }
        else {
            model.addAttribute("title", "Welcome to Flickz, "+author.getUsername());
        }

        return "newreview";

    }

    @RequestMapping(value="/newreview", method= RequestMethod.POST)
    public String ProcessNewReviewForm(HttpServletRequest request, Model model, @ModelAttribute @Valid Review review, Errors errors){

        User author = getUserFromSession(request.getSession());
        String film = request.getParameter("film");
        String title = request.getParameter("title");
        String body = request.getParameter("body");

        if (author == null){
            model.addAttribute("author_error", "You must be logged in to post a new review!");
            return "redirect:login";
        }

        Review newreview = new Review(film, title, body, author);
        reviewDao.save(newreview);
        return "redirect:";

    }

    @RequestMapping(value = "/review/{username}/{id}", method = RequestMethod.GET)
    public String DisplaySingleReview(@PathVariable String username, @PathVariable long id, Model model) {
        Review review = reviewDao.findById(id);
        model.addAttribute(review);
        return "review";
    }

    @RequestMapping(value = "/review/{username}", method = RequestMethod.GET)
    public String DisplayAllReviewsByUser(@PathVariable String username, Model model) {
        User user = userDao.findByUsername(username);
        List<Review> reviews = user.getReviews();
        model.addAttribute("title", "All reviews by "+user.getUsername());
        model.addAttribute("reviews", reviews);
        return "allreviews";
    }
}
