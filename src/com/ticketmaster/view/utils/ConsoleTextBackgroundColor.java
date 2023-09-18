package com.ticketmaster.view.utils;

public enum ConsoleTextBackgroundColor {
    DEFAULT(""),
    RESET("\u001B[0m"),
    RED("\u001B[41m");

    private String code;

    private ConsoleTextBackgroundColor(String code){
        this.code = code;
    }

    public String getColorCode(){
        return this.code;
    }

    @Override
    public String toString(){
        return this.code;
    }
}
