package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.DialogResult;

public abstract class ActiveConsoleComponent {
    private DialogResult currentState = DialogResult.AWAITING;
    private String collectedInput;

    public abstract DialogResult show();


    public DialogResult getCurrentState(){
        return this.currentState;
    }
    public void resetCurrentState(){
        this.currentState = DialogResult.AWAITING;
    }

    protected void setCollectedInput(String collectedInput) {
        this.collectedInput = collectedInput;
    }

    public String getCollectedInput() {
        return collectedInput;
    }

    protected void setCurrentState(DialogResult currentState) {
        this.currentState = currentState;
    }
}
