package com.ticketmaster.model;

enum Priority {
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
}