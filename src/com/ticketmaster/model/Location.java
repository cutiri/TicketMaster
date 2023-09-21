package com.ticketmaster.model;

import java.io.Serializable;

public class Location  implements Serializable {
    private String name;
    private Team supportTeam;

    public Location(){}

    public Location(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getSupportTeam() {
        return supportTeam;
    }

    public void setSupportTeam(Team supportTeam) {
        this.supportTeam = supportTeam;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + getName() + '\'' +
                ", supportTeam=" + getSupportTeam().getName() +
                '}';
    }
}