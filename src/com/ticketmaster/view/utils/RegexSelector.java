package com.ticketmaster.view.utils;

public enum RegexSelector {
    ANYTHING(".*"),
    NO_NUMBERS("^([^0-9]*)$"),
    NUMBER_1_TO_20("^([1-9]|[1][0-9]|20)"),
    TICKET_NUMBER("[T][0-9]{4}"),
    LOCATION("^[A-Za-z]{3}[0-9]{1,2}$"),
    PAGE_THEN_ANY_NUMBER("^(?i)page [0-9]+$"),
    CHARACTER_N("^(?i)N"),
    CHARACTER_P("^(?i)P"),
    CHARACTER_S("^(?i)S"),
    CHARACTER_C("^(?i)C"),
    CHARACTER_U("^(?i)U"),
    CHARACTER_L("^(?i)L");

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
