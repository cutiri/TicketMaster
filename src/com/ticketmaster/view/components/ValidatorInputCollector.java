package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.InputValidator;

public class ValidatorInputCollector extends InputCollector {
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
            setCurrentState(validator.validate(getCollectedInput()) ? DialogResult.SUCCESS : DialogResult.INPUT_ERROR);
        }
    }
}