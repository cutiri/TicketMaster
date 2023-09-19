package com.ticketmaster.view.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleMultiColorText {
    private List<ConsoleText> textList = new ArrayList<>();

    public ConsoleMultiColorText(ConsoleText... consoleTextArray){
        for (ConsoleText consoleText : consoleTextArray)
            textList.add(consoleText);
    }

    public List<ConsoleText> getTextList(){
        return Collections.unmodifiableList(textList);
    }

    public void add(ConsoleText consoleText){
        textList.add(consoleText);
    }

    public int length(){
        int count = 0;
        for (ConsoleText consoleText : textList)
            count += consoleText.toString().length();

        return count;
    }

    @Override
    public String toString(){
        return null;
    }
}
