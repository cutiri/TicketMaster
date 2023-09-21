package com.ticketmaster.controller.db;

import com.ticketmaster.controller.io.AppIO;
import com.ticketmaster.model.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationDB {
    private static List<Location> list;

    public static void setList(List<Location> list){
        if(LocationDB.list == null)
            LocationDB.list = list;
    }

    public static List<Location> getList(){
        setList(new ArrayList<>());
        return list;
    }

    public static List<String> locationNameList(){
        return list.stream().map(Location::getName).collect(Collectors.toList());
    }

    public static void add(Location newLocation) {
        setList(new ArrayList<>());
        list.add(newLocation);
        AppIO.saveS();
    }
}
