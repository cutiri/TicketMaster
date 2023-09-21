package com.ticketmaster.view.framework;

import com.ticketmaster.view.utils.*;

import java.util.ArrayList;
import java.util.List;

public class ListInputCollector extends InputCollector {
    private final int COLUMNS_NUMBER = 5;

    private List<String> matchesList = new ArrayList<>();

    public ListInputCollector(String text, String errorMsg, String escapeText, List<String> matchesList) {
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
            if(getCollectedInput().trim().matches(RegexSelector.NUMBERS.getRegex())){
                int number = Integer.parseInt(getCollectedInput().trim());
                if(number > 0 && number <= matchesList.size()) {
                    setCollectedInput(matchesList.get(number - 1));
                    match = true;
                }
            }
        }
        if(match) {
            setCurrentState(DialogResult.SUCCESS);
        }else{
            setCurrentState(DialogResult.INPUT_ERROR);
        }
    }
}