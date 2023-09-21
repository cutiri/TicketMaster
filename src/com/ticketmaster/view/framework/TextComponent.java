package com.ticketmaster.view.framework;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleTextColor;

public class TextComponent implements PassiveConsoleComponent{
    private String text;
    private ConsoleTextColor textColor = ConsoleTextColor.DEFAULT;
    private boolean isHidden = false;

    public TextComponent(){}

    public TextComponent(String text){
        this.text = text;
    }

    public TextComponent(String text, ConsoleTextColor consoleTextColor){
        this(text);
        this.textColor = consoleTextColor;
    }

    public TextComponent(String text, ConsoleTextColor consoleTextColor, boolean isHidden){
        this(text, consoleTextColor);
        this.isHidden = isHidden;
    }

    @Override
    public void show() {
        if(!isHidden)
            Console.printNewLine(textColor.getColorCode() + text + ConsoleTextColor.RESET.getColorCode());
    }

    public void hide(){
        isHidden = true;
    }

    public void unHide(){
        isHidden = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(ConsoleTextColor textColor) {
        this.textColor = textColor;
    }
}
