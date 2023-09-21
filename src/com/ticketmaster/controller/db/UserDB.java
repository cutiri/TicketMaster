package com.ticketmaster.controller.db;

import com.ticketmaster.controller.io.AppIO;
import com.ticketmaster.model.Location;
import com.ticketmaster.model.Team;
import com.ticketmaster.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDB {
    private static List<User> list;

    public static void setList(List<User> list){
        if(UserDB.list == null)
            UserDB.list = list;
    }

    public static List<User> getList(){
        setList(new ArrayList<>());
        return list;
    }

    public static User authenticate(String login, String password){
        User user = list.stream().
                filter(
                        u -> u.getLogin().equals(login)).
                findFirst().orElse(null);

        if(user != null){
            return password.equals(user.getPassword()) ? user : null;
        }
        return user;
    }

    public static void add(User newUser) {
        setList(new ArrayList<>());
        list.add(newUser);
        AppIO.saveS();
    }
}