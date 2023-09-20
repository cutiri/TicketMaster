package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleMultiColorText;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;

public class MultiTextComponent implements PassiveConsoleComponent {
    private ConsoleMultiColorText text;
    private ConsoleTextColor textColor = ConsoleTextColor.DEFAULT;
    private boolean isHidden = false;

    public MultiTextComponent(ConsoleText... consoleTextArray){
        this.text = new ConsoleMultiColorText(consoleTextArray);
    }

    public MultiTextComponent(ConsoleMultiColorText multiColorText){
        this.text = multiColorText;
    }

    @Override
    public void show() {
        if(!isHidden)
            Console.printNewLine(text);
    }

    public void hide(){
        isHidden = true;
    }

    public void unHide(){
        isHidden = false;
    }
}
