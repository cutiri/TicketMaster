package com.ticketmaster.model;

import java.time.LocalDateTime;

class Comment {
    private String text;
    private LocalDateTime commentedAt;
    private String userLogin;

    public Comment(String text, String userLogin, LocalDateTime commentedAt) {
        setText(text);
        setCommentedAt(commentedAt);
        setUser(userLogin);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    public String getUser() {
        return userLogin;
    }

    public void setUser(String userLogin) {
        this.userLogin = userLogin;
    }
}
