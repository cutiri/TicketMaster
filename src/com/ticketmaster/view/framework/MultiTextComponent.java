package com.ticketmaster.view.framework;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleMultiColorText;
import com.ticketmaster.view.utils.ConsoleText;

public class MultiTextComponent implements PassiveConsoleComponent {
    private ConsoleMultiColorText text;
    private boolean isHidden = false;

    public MultiTextComponent(ConsoleText... consoleTextArray){
        this.text = new ConsoleMultiColorText(consoleTextArray);
    }

    @Override
    public void show() {
        if(!isHidden)
            Console.printNewLine(text);
    }
}
