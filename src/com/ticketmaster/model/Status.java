package com.ticketmaster.model;

enum Status {
    OPEN ("Open"),
    IN_PROGRESS ("In Progress"),
    PENDING ("Pending"),
    PENDING_APPROVAL ("Pending Approval"),
    RESOLVED ("Resolved");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}