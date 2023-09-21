package com.ticketmaster.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Priority  implements Serializable {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

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

    public static List<String> getPriorityStringList() {
        return Arrays.stream(Priority.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}