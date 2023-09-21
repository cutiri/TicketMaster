package com.ticketmaster.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TroubleTicket extends Ticket implements Serializable {

    public TroubleTicket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User createdBy) {
        super(title, description, priority, location, createdAt, createdBy);
    }

    @Override
    public void updateStatus(Status status) throws InvalidActionException {
        setStatus(status);
    }


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
