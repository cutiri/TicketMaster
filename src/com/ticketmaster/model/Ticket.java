package com.ticketmaster.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import static com.ticketmaster.model.Status.*;

public abstract class Ticket implements Serializable {
    private int id = -1;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Status status;
    private Priority priority;
    private Location location;
    private Team teamAssigned;
    private User userAssigned;
    private User createdBy;
    private int totalTimeSpentInMinutes;
    private final Collection<Comment> comments = new LinkedList<>();

    public Ticket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User createdBy) {
        setTitle(title);
        setDescription(description);
        setCreatedAt(createdAt);
        setStatus(OPEN);
        setPriority(priority);
        setLocation(location);
        setTeamAssigned(location.getSupportTeam());
        setUserAssigned(teamAssigned.getFirstUser());
        setCreatedBy(createdBy);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // whenever update location, assign the ticket to the appropriate team and user.
    public void updateLocation(Location location) {
        setLocation(location);
        setTeamAssigned(getLocation().getSupportTeam());
        setUserAssigned(getTeamAssigned().getFirstUser());
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

    public int getTotalTimeSpentInMinutes() {
        return totalTimeSpentInMinutes;
    }

    // get a list of all comments in reverse order so new comments appear first
    public Collection<Comment> getComments() {
        List<Comment> reversedComments = new ArrayList<>(comments);
        Collections.reverse(reversedComments);
        return reversedComments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        totalTimeSpentInMinutes += comment.getTimeSpentInMinutes();
    }

    // abstract method
    public abstract void updateStatus(Status status) throws InvalidActionException;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", status=" + getStatus() +
                ", priority=" + getPriority() +
                ", location=" + getLocation().getName() +
                ", teamAssigned=" + getTeamAssigned().getName() +
                ", userAssigned=" + getUserAssigned().getLogin() +
                ", createdBy=" + getCreatedBy().getLogin() +
                ", totalTimeSpentInMinutes=" + getTotalTimeSpentInMinutes() +
                ", comments=" + getComments() +
                '}';
    }


}