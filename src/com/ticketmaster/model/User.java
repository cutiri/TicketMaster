package com.ticketmaster.model;

class User {
    private String login;
    private String password;
    private Team team;
    boolean isActive;

    public User(String login, String password, Team team) {
        setLogin(login);
        setPassword(password);
        setTeam(team);
        setActive(true);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
