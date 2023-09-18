package com.ticketmaster.view.utils;

public enum DialogResult {
    ESCAPE(true),
    SUCCESS(true),
    INPUT_ERROR(false),
    AWAITING(false);

    private boolean isFinal = false;

    private DialogResult(boolean value){
        isFinal = value;
    }

    public boolean isResultFinal(){
        return isFinal;
    }
}
