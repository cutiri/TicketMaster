package com.ticketmaster.view.utils;

public enum RegexSelector {
    ANYTHING(".*"),
    NO_NUMBERS("^([^0-9]*)$");


    private String regex;

    private RegexSelector(String regex){
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public String toString(){
        return getRegex();
    }
}
