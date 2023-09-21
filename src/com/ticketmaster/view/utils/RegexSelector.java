package com.ticketmaster.view.utils;

public enum RegexSelector {
    ANYTHING(".*"),
    NON_EMPTY_STRING(".+"),
    NO_NUMBERS("^([^0-9]*)$"),
    NUMBERS("^[1-9]\\d{0,4}$"),
    NUMBER_1_TO_20("^([1-9]|[1][0-9]|20)"),
    TICKET_NUMBER("[T][0-9]{4}"),
    LOCATION("^[A-Za-z]{3}[0-9]{1,2}$"),
    PAGE_THEN_ANY_NUMBER("^(?i)page [0-9]+$"),
    CHARACTER_N("^(?i)N"),
    CHARACTER_P("^(?i)P"),
    START_WITH_DIGIT("^[0-9].*"),
    CHARACTER_S("^(?i)S"),
    CHARACTER_C("^(?i)C"),
    CHARACTER_U("^(?i)U"),
    CHARACTER_L("^(?i)L"),
    CHARACTER_A("^(?i)A"),
    CHARACTER_F("^(?i)F"),
    CHARACTER_R("^(?i)R"),
    CHARACTER_Y("^(?i)Y"),
    EDIT_TICKET_OPTIONS("[PpSsCcUuLlaA]");


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
