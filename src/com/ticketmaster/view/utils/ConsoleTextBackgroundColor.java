package com.ticketmaster.view.utils;

public enum ConsoleTextBackgroundColor {
    DEFAULT(""),
    RESET("\u001B[0m"),
    RED("\u001B[41m"),
    YELLOW("\u001B[43m"),
    GREEN("\u001B[42m");

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
