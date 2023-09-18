package com.ticketmaster.model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(getLogin());
    }

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

    @Override
    public String toString() {
        return "User{" +
                "login='" + getLogin() + '\'' +
                ", team=" + getTeam().getName() +
                ", isActive=" + isActive() +
                '}';
    }
}
