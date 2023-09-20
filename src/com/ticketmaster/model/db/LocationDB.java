package com.ticketmaster.model.db;

import com.ticketmaster.model.Location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationDB {

    static Location las1 = new Location("LAS1");
    static Location las2 = new Location("LAS2");
    static Location las3 = new Location("LAS3");


    public static List<Location> locationList() {
        return List.of(las1, las2, las3);
    }

    public static List<String> locationNameList(){
        return locationList().stream().map(Location::getName).collect(Collectors.toList());
    }

}
