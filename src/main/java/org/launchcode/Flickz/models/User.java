package org.launchcode.Flickz.models;




import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by melissa on 5/21/17.
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String passwordHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<Review> reviews = new ArrayList<>();

    public User(){}

    public User(String username, String password) {

        super();

        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username");
        }

        this.username = username;
        this.passwordHash = hashPassword(password);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordhash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, passwordHash);
    }

    public static boolean isValidUsername(String username) {
        Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,15}");
        Matcher matcher = validUsernamePattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
        Matcher matcher = validUsernamePattern.matcher(password);
        return matcher.matches();
    }



    protected void addReview(Review review){
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
