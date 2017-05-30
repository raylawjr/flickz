package org.launchcode.Flickz.controllers;

import org.launchcode.Flickz.models.User;
import org.launchcode.Flickz.models.data.ReviewDao;
import org.launchcode.Flickz.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * Created by melissa on 5/26/17.
 */

public class AbstractController {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected ReviewDao reviewDao;

    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session){
        Long userId = (Long) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findById(userId);
    }

    protected void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

}
