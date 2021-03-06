package com.levelup.web.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String login;

    @Column(nullable = false, length = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStates status;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(nullable = false)
    private boolean isAdmin;

    @OneToMany(mappedBy = "author")
    private List<Question> listOfQuestions;

    @OneToMany(mappedBy = "author")
    private List<Comment> listOfComments;

    @OneToMany(mappedBy = "author")
    private List<Thumb> listOfThumbs;

    @OneToMany(mappedBy = "author")
    private List<Answer> listOfAnswers;

    public User() {
    }

    public User(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public List<Thumb> getListOfLikes() {
        return listOfThumbs;
    }

    public void setListOfLikes(List<Thumb> listOfThumbs) {
        this.listOfThumbs = listOfThumbs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public List<Comment> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<Comment> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public List<Answer> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<Answer> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserStates getStatus() {
        return status;
    }

    public void setStatus(UserStates status) {
        this.status = status;
    }
}
