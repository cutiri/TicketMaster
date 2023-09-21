package com.ticketmaster.view.framework;

import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.InputValidator;

public class ValidatorInputCollector extends InputCollector {
    //This is an instance of a functional interface
    private InputValidator validator;

    private ValidatorInputCollector(String text, String errorMsg, String escapeText) {
        super(text, errorMsg, escapeText);
    }

    public ValidatorInputCollector(String text, String errorMsg, String escapeText, InputValidator validator){
        this(text, errorMsg, escapeText);
        this.validator = validator;
    }

    @Override
    protected void verifyInput() {
        if(validator == null)
        {
            setCurrentState(DialogResult.SUCCESS);
        }else {
            //we collect the user's input and call our functional interface method, which will trigger a lambda
            setCurrentState(validator.validate(getCollectedInput()) ? DialogResult.SUCCESS : DialogResult.INPUT_ERROR);
        }
    }
}
