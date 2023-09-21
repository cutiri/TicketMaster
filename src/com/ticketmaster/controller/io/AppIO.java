package com.ticketmaster.controller.io;

import com.ticketmaster.controller.db.AppDatabase;

import java.io.*;

public class AppIO {
    public static final String DATA_FILE_PATH = "data/data.dat";

    private AppIO(){}

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
