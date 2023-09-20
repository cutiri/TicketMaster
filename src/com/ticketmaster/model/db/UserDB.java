package com.ticketmaster.model.db;

import com.ticketmaster.model.User;

import java.util.List;

public class UserDB {
    static User jack = new User("jack", "password", TeamDB.las1Team);
    static User jason = new User("jason", "password", TeamDB.las1Team);
    static User peter = new User("peter", "password", TeamDB.las2Team);
    static User william = new User("william", "password", TeamDB.las2Team);
    static User michael = new User("michael", "password", TeamDB.las3Team);
    static User sam = new User("samuel", "password", TeamDB.las3Team);
    static User dummy = new User("a", "a", TeamDB.las3Team);

    public static List<User> userList() {
        return List.of(jack, jason, peter, william, michael, sam, dummy);
    }
}
