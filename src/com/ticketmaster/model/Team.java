package com.ticketmaster.model;

import java.util.ArrayList;
import java.util.List;

class Team {
    private String name;
    private Location locatedAt;
    private List<User> members = new ArrayList<>();

    public Team(String name, Location locatedAt) {
        setName(name);
        setLocatedAt(locatedAt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(Location locatedAt) {
        this.locatedAt = locatedAt;
    }

    public List<User> getMembers() {
        return members;
    }

    // add member

    public User getRandomUser() {
        return members.size() > 0 ? members.get(0) : null;
    }
}
