package com.ticketmaster.model.db;

import com.ticketmaster.model.User;

import java.util.List;

public class UserDB {
    static User jack = new User("jack", "password", "Jack Sparrow", TeamDB.las1Team);
    static User jason = new User("jason", "password","Jason Momoa", TeamDB.las1Team);
    static User peter = new User("peter", "password","Peter Jackson", TeamDB.las2Team);
    static User william = new User("william", "password","William Defoe", TeamDB.las2Team);
    static User michael = new User("michael", "password","Michael Scofield", TeamDB.las3Team);
    static User sam = new User("samuel", "password","Samuel Jackson", TeamDB.las3Team);
    static User dummy = new User("a", "a","Bob Sponge", TeamDB.las3Team);

    public static List<User> userList() {
        return List.of(jack, jason, peter, william, michael, sam, dummy);
    }
}
