package com.ticketmaster.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Status {
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

    public static String getAllStatus() {
        return "OPEN, IN PROGRESS, PENDING, PENDING APPROVAL, RESOLVED";
    }

    public static List<String> getStatusStringList() {
        return Arrays.stream(Status.values()).map(Enum::name).collect(Collectors.toList());
    }
}