package org.launchcode.Flickz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by melissa on 5/29/17.
 */

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String film;

    @NotNull
    private String title;

    @NotNull
    private String body;

    private String image_url;

    @ManyToOne
    private User author;

    private Date created = new Date();

    private Date modified = new Date();

    public Review(String film, String title, String body, String image_url, User author) {

        super();
        this.film = film;
        this.title = title;
        this.body = body;
        this.image_url = image_url;
        this.author = author;
        this.created = new Date();
        this.updated();

        author.addReview(this);
    }

    public Review() {}

    public Long getId() {
        return id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    private void updated() { this.modified = new Date();}
}
