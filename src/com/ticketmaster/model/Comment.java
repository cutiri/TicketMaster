package com.ticketmaster.model;

import java.time.LocalDateTime;

class Comment {
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
        return "Comment{" +
                "text='" + text + '\'' +
                ", commentedAt=" + getCommentedAt() +
                ", userLogin='" + getUserLogin() + '\'' +
                ", timeSpentInMinutes=" + getTimeSpentInMinutes() +
                '}';
    }
}
