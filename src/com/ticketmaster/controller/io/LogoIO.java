package com.ticketmaster.controller.io;

import java.nio.file.Files;
import java.nio.file.Path;

/*
 * A class to read the logo file and convert it to String
 */
public final class LogoIO {
    private static final String logoFilePath = "data/logo.dat";

    private LogoIO(){}

    public static String getLogo(){
        try {
            return Files.readString(Path.of(logoFilePath));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
