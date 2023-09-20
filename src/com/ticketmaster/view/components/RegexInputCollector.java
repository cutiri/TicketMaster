package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

public class RegexInputCollector extends InputCollector {

    private String regex = RegexSelector.ANYTHING.getRegex();

    public RegexInputCollector(String text, String errorMsg, String escapeText) {
        super(text, errorMsg, escapeText);
        this.regex = RegexSelector.ANYTHING.getRegex();
    }

    public RegexInputCollector(String text, String errorMsg, String escapeText, String regex) {
        super(text, errorMsg, escapeText);

        this.regex = regex;
    }

    @Override
    protected void verifyInput() {
        if(getCollectedInput().matches(regex)){
            setCurrentState(DialogResult.SUCCESS);
        }else{
            setCurrentState(DialogResult.INPUT_ERROR);
        }
    }
}
