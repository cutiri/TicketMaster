package com.ticketmaster.model;

import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextBackgroundColor;
import com.ticketmaster.view.utils.ConsoleTextColor;

import java.time.LocalDateTime;
import java.util.*;

import static com.ticketmaster.model.Status.*;

public abstract class Ticket {
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
    private int totalTimeSpentInMinutes;
    private final Collection<Comment> comments = new LinkedList<>();

    public Ticket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User createdBy) {
        setId(ID_COUNTER++);
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

    public static Map<String, Integer> getHeaders(){
        Map<String, Integer> headers = new LinkedHashMap<>();
        headers.put("Title", 20);
        headers.put("User", 12);
        headers.put("Date", 10);
        headers.put("Priority", 10);
        return headers;
    }

    public List<ConsoleText> getRowData(){
        List<ConsoleText> rawData = new ArrayList<>();
        rawData.add(new ConsoleText(this.title));
        rawData.add(new ConsoleText(this.createdBy.getLogin()));
        rawData.add(new ConsoleText(this.createdAt.toString()));

        ConsoleText sev = new ConsoleText(this.priority.getPriority());
        if(this.priority == Priority.HIGH) {
            sev.setConsoleTextBackgroundColor(ConsoleTextBackgroundColor.RED);
            sev.setConsoleTextColor(ConsoleTextColor.BLACK);
        }
        if(this.priority == Priority.MEDIUM) {
            sev.setConsoleTextBackgroundColor(ConsoleTextBackgroundColor.YELLOW);
            sev.setConsoleTextColor(ConsoleTextColor.BLACK);
        }
        if(this.priority == Priority.LOW) {
            sev.setConsoleTextBackgroundColor(ConsoleTextBackgroundColor.GREEN);
            sev.setConsoleTextColor(ConsoleTextColor.BLACK);
        }
        rawData.add(sev);
        return rawData;
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

    public void setTotalTimeSpentInMinutes(int totalTimeSpentInMinutes) {
        this.totalTimeSpentInMinutes = totalTimeSpentInMinutes;
    }

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
    public abstract void close() throws InvalidActionException;

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