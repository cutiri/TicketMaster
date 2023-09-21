package com.ticketmaster.controller;


import com.ticketmaster.controller.controllers.MainController;
import com.ticketmaster.controller.db.AppDatabase;
import com.ticketmaster.controller.io.AppIO;

public class TicketMasterApp {

    public static void main(String[] args) {
        try {
            normalAppStart();

            MainController mainController = new MainController();
            mainController.run(null);
        }catch (Exception e){
            saveWithFakeData();
            e.printStackTrace();
        }
    }

    /*
     * This method will load predefined data into the system and save it to a file
     * Use when the main DB file gets corrupted
     */
    private static void saveWithFakeData(){
        AppDatabase appDatabase = new AppDatabase();
        appDatabase.initializeAppDatabaseWithFakeData();
        AppIO.saveS();
    }

    /*
     * Will load the data from a file
     */
    private static void normalAppStart(){
        AppDatabase appDatabase = AppIO.readS();
        appDatabase.initializeAppDatabase();
    }
}
