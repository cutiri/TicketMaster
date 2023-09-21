package com.ticketmaster.controller;


import com.ticketmaster.controller.controllers.MainController;
import com.ticketmaster.controller.db.AppDatabase;
import com.ticketmaster.controller.io.AppIO;

/*
 * Application's entry point class
 */
public class TicketMasterApp {

    /*
     * Entry point method main, the applications starts here
     */
    public static void main(String[] args) {
        try {
            //This will try to start the application normally, reading the data from a file
            normalAppStart();
            //Initializing the Main Controller and running it
            MainController mainController = new MainController();
            mainController.run(null);
        }catch (Exception e){
            //A problem was encountered during the file reading, we will try to generate the data and create a new file
            //At this point the app will crash but it should run the next time
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
     * Calling AppIO to load the application's data from a file
     */
    private static void normalAppStart(){
        AppDatabase appDatabase = AppIO.readS();
        appDatabase.initializeAppDatabase();
    }
}
