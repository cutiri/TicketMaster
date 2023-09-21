package com.ticketmaster.model;

import java.time.LocalDateTime;
import static com.ticketmaster.model.Status.RESOLVED;

public class Request extends Ticket{
    private User approver;
    private boolean isApproved;

    public Request(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User approver, User createdBy) {
        super(title, description, priority, location, createdAt, createdBy);
        setApprover(approver);
        setApproved(false);
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public void updateStatus(Status status) throws InvalidActionException {
        if (status != RESOLVED) {
            setStatus(status);
        } else {
            if (isApproved()) {
                setStatus(status);
            } else {
                throw new InvalidActionException("Pending approval, request can not be closed without approval.");
            }
        }
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
                ", approver=" + getApprover().getLogin() +
                ", approved=" + isApproved() +
                '}';
    }
}
