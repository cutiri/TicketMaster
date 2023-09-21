package com.ticketmaster.model;

import java.io.Serializable;
import java.util.*;
import java.util.TreeSet;

public class Team implements Serializable {
    private String name;
    private Location locatedAt;
    private final Set<User> members = new HashSet<>();

    public Team(){}

    public Team(String name, Location locatedAt) {
        setName(name);
        setLocatedAt(locatedAt);
        locatedAt.setSupportTeam(this);
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

    public Set<User> getMembers() {
        return members;
    }

    public void addMember(User user) throws InvalidActionException{
        if (members.contains(user)) {
            throw new InvalidActionException(String.format("User with login %s already exist.", user.getLogin()));
        } else {
            members.add(user);
        }
    }

    public User getFirstUser() {
        Iterator<User> iterator = members.iterator();
        return iterator.next();
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + getName() + '\'' +
                ", locatedAt=" + getLocatedAt() .getName()+
                '}';
    }
}
