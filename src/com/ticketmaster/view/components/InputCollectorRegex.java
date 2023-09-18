package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;

class InputCollectorRegex extends InputCollector {

    private String regex = RegexSelector.ANYTHING.getRegex();

    public InputCollectorRegex(String text, String errorMsg, String escapeText) {
        super(text, errorMsg, escapeText);
    }

    public InputCollectorRegex(String text, String errorMsg, String escapeText, String regex) {
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
