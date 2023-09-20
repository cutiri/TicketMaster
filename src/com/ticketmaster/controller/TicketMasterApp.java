package com.ticketmaster.controller;


import com.ticketmaster.model.db.Database;

public class TicketMasterApp {

    public static void main(String[] args) {
        try {
            Database.loadData();
            MainController mainController = new MainController();
            mainController.run(null);
        }catch (Exception e){

        }
    }
}
