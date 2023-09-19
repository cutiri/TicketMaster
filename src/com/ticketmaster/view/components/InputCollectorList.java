package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;

import java.util.ArrayList;
import java.util.List;

public class InputCollectorList extends InputCollector {
    private final int COLUMNS_NUMBER = 5;

    private List<String> matchesList = new ArrayList<>();

    public InputCollectorList(String text, String errorMsg, String escapeText) {
        super(text, errorMsg, escapeText);
    }

    public InputCollectorList(String text, String errorMsg, String escapeText, List<String> matchesList) {
        super(text, errorMsg, escapeText);
        this.matchesList = matchesList;
    }

    @Override
    protected final void print(){
        super.print();
        int count = 0;

        Console.goToNextLine();
        for (String entry : matchesList){
            Console.printText(
                    new ConsoleText(String.valueOf(count + 1), ConsoleTextColor.WHITE),
                    new ConsoleText(": "),
                    new ConsoleText(entry, ConsoleTextColor.GREEN),
                    new ConsoleText("\t")
            );
            count++;
            if(count == COLUMNS_NUMBER)
                Console.goToNextLine();
        }
        Console.goToNextLine();
        Console.printText("Enter one of the options above: ");
    }

    @Override
    protected void verifyInput() {
        boolean match = matchesList.stream()
                .anyMatch(text -> text
                        .trim()
                        .equalsIgnoreCase(getCollectedInput()));
        if(!match)
        {
            try{
                int number = Integer.parseInt(getCollectedInput().trim());
                setCollectedInput(matchesList.get(number - 1));
                match=true;
            }catch (Exception e){

            }
        }
        if(match) {
            setCurrentState(DialogResult.SUCCESS);
        }else{
            setCurrentState(DialogResult.INPUT_ERROR);
        }
    }
}