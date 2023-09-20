package com.ticketmaster.model.db;

import com.ticketmaster.model.User;

import java.util.List;

class UserDB {
    static User jack = new User("jack", "jack_password", TeamDB.las1Team);
    static User jason = new User("jason", "jason_password", TeamDB.las1Team);
    static User peter = new User("peter", "peter_password", TeamDB.las2Team);
    static User william = new User("william", "william_password", TeamDB.las2Team);
    static User michael = new User("michael", "michael_password", TeamDB.las3Team);
    static User sam = new User("samuel", "samuel_password", TeamDB.las3Team);
    static User dummy = new User("a", "a", TeamDB.las3Team);

    public static List<User> userList() {
        return List.of(jack, jason, peter, william, michael, sam, dummy);
    }
}
