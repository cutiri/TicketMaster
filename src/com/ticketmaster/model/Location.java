package com.ticketmaster.model;

class Location {
    private String name;
    private Team supportTeam;

    public Location(String name, Team supportTeam) {
        setName(name);
        setSupportTeam(supportTeam);
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
}