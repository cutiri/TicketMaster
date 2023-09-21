package com.ticketmaster.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Class that represents a Ticket's comment
 * A single ticket could have none, one or more comments
 * A comments consists of some text, a Date and time entry that marks when the comment was made
 * The user who made the comment, and the time spent on the ticket by that user
 */
public class Comment implements Serializable {
    private String text;
    private LocalDateTime commentedAt;
    private String userLogin;
    private int timeSpentInMinutes;

    public Comment(String text, String userLogin, int timeSpentInMinutes, LocalDateTime commentedAt) {
        setText(text);
        setUserLogin(userLogin);
        setTimeSpentInMinutes(timeSpentInMinutes);
        setCommentedAt(commentedAt);

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getTimeSpentInMinutes() {
        return timeSpentInMinutes;
    }

    private void setTimeSpentInMinutes(int timeSpentInMinutes) {
        this.timeSpentInMinutes = timeSpentInMinutes;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    @Override
    public String toString() {
        return String.format("\n -> " +
                "Commented At: %s\n" +
                "    user: %s\n" +
                "    Time Spent: %d Minutes\n" +
                "    %s\n", LocalDateTime.parse(getCommentedAt().toString()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), getUserLogin(), getTimeSpentInMinutes(), getText());
    }
}
