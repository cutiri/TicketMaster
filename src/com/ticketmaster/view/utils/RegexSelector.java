package com.ticketmaster.view.utils;

public enum RegexSelector {
    ANYTHING(".*"),
    NO_NUMBERS("^([^0-9]*)$"),
    NUMBER_1_TO_20("^([1-9]|[1][0-9]|20)"),
    TICKET_NUMBER("[T][0-9]{4}");


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
