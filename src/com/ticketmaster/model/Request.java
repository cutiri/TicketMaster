package com.ticketmaster.model;

import java.time.LocalDateTime;

class Request extends Ticket{
    private User approver;
    private boolean isApproved;

    public Request(String title, String description, Priority priority, Location location, LocalDateTime createdAt, User approver) {
        super(title, description, priority, location, createdAt);
        this.approver = approver;
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
    public void close() {
        System.out.println("Closing Request");
        System.out.println("Order has been placed!");
    }
}
