package com.ticketmaster.model;

import java.time.LocalDateTime;
import static com.ticketmaster.model.Status.RESOLVED;

public class TroubleTicket extends Ticket{

    public TroubleTicket(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User createdBy) {
        super(title, description, priority, location, createdAt, createdBy);
    }

    @Override
    public void updateStatus(Status status) throws InvalidActionException {

        setStatus(status);
//        if (status == RESOLVED && getComments().size() < 1) {
//            System.out.println("throwing exception from here");
//            throw new InvalidActionException("Please add a comment to close the ticket.");
//        } else {
//            setStatus(status);
//        }
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
