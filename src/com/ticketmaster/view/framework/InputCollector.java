package com.ticketmaster.view.framework;

import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.Console;

import java.util.Scanner;

public abstract class InputCollector extends ActiveConsoleComponent {

    private String text;
    private String errorMsg;
    private String escapeText;

    private Scanner scanner = new Scanner(System.in);

    public InputCollector(String text, String errorMsg, String escapeText){
        this.text = text;
        this.errorMsg = errorMsg;
        this.escapeText = escapeText;
    }

    protected void print(){
        if(getCurrentState() == DialogResult.INPUT_ERROR)
            Console.printNewLine(errorMsg, ConsoleTextColor.RED);

        Console.printText(text);
    }

    @Override
    public final DialogResult show(){
        print();

        if(getCurrentState() != DialogResult.SUCCESS) {
            setCollectedInput(scanner.nextLine());
        }else{
            Console.printNewLine(getCollectedInput(), ConsoleTextColor.GREEN);
        }

        if(getCollectedInput().equals(escapeText)){
            setCurrentState(DialogResult.ESCAPE);
            return getCurrentState();
        }

        verifyInput();

        return getCurrentState();
    }

    protected abstract void verifyInput();
}
