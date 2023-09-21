package com.ticketmaster.controller.io;

import com.ticketmaster.controller.db.AppDatabase;

import java.io.*;

/*
 * This class will handle reading and writing the application's data from and to the hard drive
 */
public final class AppIO {
    //default data file path
    public static final String DATA_FILE_PATH = "data/data.dat";

    //Fully static class
    private AppIO(){}

    //Writing method
    public static void saveS(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH));
            AppDatabase appDatabase = new AppDatabase();
            appDatabase.updateLists();

            out.writeObject(appDatabase);

            out.close();
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }
    }

    //Reading method
    public static AppDatabase readS(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH));

            AppDatabase appDatabase = (AppDatabase) in.readObject();

            in.close();

            return appDatabase;
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());
        }

        return null;
    }


}
