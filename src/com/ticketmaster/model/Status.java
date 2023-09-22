package com.ticketmaster.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Status implements Serializable {
    OPEN ("Open"),
    IN_PROGRESS ("In Progress"),
    PENDING ("Pending"),
    PENDING_APPROVAL ("Pending Approval"),
    RESOLVED ("Resolved");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    // get string of status
    public String getStatus() {
        return status;
    }

    // get a list of all status string value
    public static List<String> getStatusStringList() {
        return Arrays.stream(Status.values()).map(Status::getStatus).collect(Collectors.toList());
    }

    public static Status getStatusFromString(String status){
        for (Status item : Status.values()){
            if(item.getStatus().toLowerCase().equals(status.toLowerCase())){
                return item;
            }
        }
        return Status.OPEN;
    }


    @Override
    public String toString(){
        return this.getStatus();
    }
}