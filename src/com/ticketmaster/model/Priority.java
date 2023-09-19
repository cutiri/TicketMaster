package com.ticketmaster.model;

public enum Priority {
    LOW ("Low"),
    MEDIUM ("Medium"),
    HIGH ("High");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public static String getPriorities() {
        return "HIGH, MEDIUM, LOW";
    }

}