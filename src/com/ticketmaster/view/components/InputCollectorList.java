package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.DialogResult;

import java.util.ArrayList;
import java.util.List;

class InputCollectorList extends InputCollector {
    private List<String> matchesList = new ArrayList<>();

    public InputCollectorList(String text, String errorMsg, String escapeText) {
        super(text, errorMsg, escapeText);
    }

    public InputCollectorList(String text, String errorMsg, String escapeText, List<String> matchesList) {
        super(text, errorMsg, escapeText);
        this.matchesList = matchesList;
    }

    @Override
    protected void verifyInput() {
        boolean match = matchesList.stream()
                .anyMatch(text -> text
                        .trim()
                        .equalsIgnoreCase(getCollectedInput()));
        if(match){
            setCurrentState(DialogResult.SUCCESS);
        }else{
            setCurrentState(DialogResult.INPUT_ERROR);
        }
    }
}
