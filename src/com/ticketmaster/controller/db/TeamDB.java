package com.ticketmaster.controller.db;

import com.ticketmaster.controller.io.AppIO;
import com.ticketmaster.model.Team;
import java.util.ArrayList;
import java.util.List;

public class TeamDB {

    private static List<Team> list;

    public static void setList(List<Team> list){
        if(TeamDB.list == null)
            TeamDB.list = list;
    }

    public static List<Team> getList(){
        setList(new ArrayList<>());
        return list;
    }

    public static void add(Team newTeam) {
        setList(new ArrayList<>());
        list.add(newTeam);
        AppIO.saveS();
    }
}
