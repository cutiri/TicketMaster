package com.ticketmaster.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String login;
    private String password;
    private Team team;
    private String fullName;
    boolean isActive;

    public User(){}

    public User(String login, String password, Team team) throws InvalidActionException {
        setLogin(login);
        setPassword(password);
        setTeam(team);
        setActive(true);
        // add user to the team on creation
        team.addMember(this);
    }

    public User(String login, String password, String name, Team team) throws InvalidActionException {
        this(login, password, team);
        setFullName(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin());
    }

    // equals method to be used when adding to the team
    @Override
    public boolean equals(Object other) {
        boolean result = false;

        if (this == other) {
            result = true;
        }

        if (other != null && (this.getClass() == other.getClass())) {
            result = Objects.equals(this.getLogin(), ((User) other).getLogin());
        }

        return result;
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

    public String getPassword() {
        return password;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + getLogin() + '\'' +
                ", team=" + getTeam().getName() +
                ", isActive=" + isActive() +
                '}';
    }
}
