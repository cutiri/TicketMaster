package com.ticketmaster.view.utils;

public class ConsoleText {
    private String text;
    private ConsoleTextColor consoleTextColor = ConsoleTextColor.DEFAULT;
    private ConsoleTextBackgroundColor consoleTextBackgroundColor = ConsoleTextBackgroundColor.DEFAULT;

    public ConsoleText(String text){
        this.text = text;
    }

    public ConsoleText(String text, ConsoleTextColor consoleTextColor){
        this(text);
        this.consoleTextColor = consoleTextColor;
    }

    public ConsoleText(String text, ConsoleTextBackgroundColor consoleTextBackgroundColor){
        this(text);
        this.consoleTextBackgroundColor = consoleTextBackgroundColor;
    }

    public ConsoleText(String text, ConsoleTextColor consoleTextColor, ConsoleTextBackgroundColor consoleTextBackgroundColor){
        this(text, consoleTextColor);
        this.consoleTextBackgroundColor = consoleTextBackgroundColor;
    }

    @Override
    public String toString(){
        return text;
    }

    public String toStringWithColor(){
        String result = this.consoleTextColor.toString() + this.consoleTextBackgroundColor.toString() + text;
        if(consoleTextColor != ConsoleTextColor.DEFAULT || consoleTextBackgroundColor != ConsoleTextBackgroundColor.DEFAULT) {
            result = result + ConsoleTextColor.RESET.toString();
        }
        return text;
    }

    public ConsoleTextColor getConsoleTextColor() {
        return consoleTextColor;
    }

    public ConsoleTextBackgroundColor getConsoleTextBackgroundColor() {
        return consoleTextBackgroundColor;
    }

    public void setConsoleTextColor(ConsoleTextColor consoleTextColor) {
        this.consoleTextColor = consoleTextColor;
    }

    public void setConsoleTextBackgroundColor(ConsoleTextBackgroundColor consoleTextBackgroundColor) {
        this.consoleTextBackgroundColor = consoleTextBackgroundColor;
    }
}
