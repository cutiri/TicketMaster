package com.ticketmaster.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import static com.ticketmaster.model.Status.*;

abstract class Ticket {
    private static int ID_COUNTER = 1000; // move to DB
    private int id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Status status;
    private Priority priority;
    private Location location;
    private Team teamAssigned;
    private User userAssigned;
    private User createdBy;
    private final Collection<Comment> comments = new LinkedList<>();

    public Ticket(String title, String description, Priority priority, Location location, LocalDateTime createdAt) {
        setId(ID_COUNTER++);
        setTitle(title);
        setDescription(description);
        setCreatedAt(createdAt);
        setStatus(OPEN);
        setPriority(priority);
        setLocation(location);
        setTeamAssigned(location.getSupportTeam());
        setUserAssigned(teamAssigned.getRandomUser());
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Team getTeamAssigned() {
        return teamAssigned;
    }

    public void setTeamAssigned(Team teamAssigned) {
        this.teamAssigned = teamAssigned;
    }

    public User getUserAssigned() {
        return userAssigned;
    }

    public void setUserAssigned(User userAssigned) {
        this.userAssigned = userAssigned;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // abstract method
    public abstract void close();
}