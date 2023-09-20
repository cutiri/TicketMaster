package com.ticketmaster.view.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 *
 */
public class Console {
    private static final String os = System.getProperty("os.name").toLowerCase();

    private Console(){
    }

    public static void printText(String text){
        System.out.print(text);
    }

    public static void printText(String line, ConsoleTextColor color){
        System.out.print(color.getColorCode() + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printText(String line, ConsoleTextBackgroundColor backgroundColor){
        System.out.print(backgroundColor.getColorCode() + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printText(String line, ConsoleTextColor color, ConsoleTextBackgroundColor backgroundColor){
        System.out.print(color.getColorCode() + backgroundColor + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printText(String line, ConsoleTextColor color, ConsoleTextBackgroundColor backgroundColor, int length){
        String format = String.format("%%-%s.%ss", length, length);
        line = String.format(format, line);
        System.out.print(color.getColorCode() + backgroundColor + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printText(ConsoleText firstConsoleText, ConsoleText... consoleTextArray){
        printText(firstConsoleText.toString(), firstConsoleText.getConsoleTextColor(), firstConsoleText.getConsoleTextBackgroundColor());
        for (ConsoleText consoleText : consoleTextArray){
            printText(consoleText);
        }
    }

    public static void printText(ConsoleText consoleText, int length){
        String format = String.format("%%-%s.%ss", length, length);
        printText(String.format(format, consoleText.toString()), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());
    }

    public static void printText(String text, int length){
        String format = String.format("%%-%s.%ss", length, length);
        printText(String.format(format, text));
    }

    public static void printText(ConsoleMultiColorText consoleMultiColorText, int totalLength){
        int count = 0;
        for (ConsoleText consoleText : consoleMultiColorText.getTextList()){
            int length = count > totalLength ? totalLength - count : consoleText.length();

            String format = String.format("%%-%s.%ss", length, length);
            printText(String.format(format, consoleText.toString()), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());

            count += consoleText.length();
            if(count >= totalLength)
                break;
        }
        if(count < totalLength){
            printText(" ".repeat(totalLength - count));
        }
    }

    public static void printNewLine(String line){
        System.out.println(line);
    }

    public static void printNewLine(String line, ConsoleTextColor color){
        System.out.println(color.getColorCode() + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printNewLine(String line, ConsoleTextBackgroundColor backgroundColor){
        System.out.println(backgroundColor.getColorCode() + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printNewLine(String line, ConsoleTextColor color, ConsoleTextBackgroundColor backgroundColor){
        System.out.println(color.getColorCode() + backgroundColor + line + ConsoleTextColor.RESET.getColorCode());
    }

    public static void printNewLine(ConsoleText... consoleTextArray){
        for (ConsoleText consoleText : consoleTextArray){
            printNewLine(consoleText.toString(), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());
        }
    }

    public static void printNewLine(ConsoleMultiColorText consoleMultiColorText){
        for (ConsoleText consoleText : consoleMultiColorText.getTextList()){
            printText(consoleText.toString(), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());
        }
        goToNextLine();
    }

    public static void printNewLine(ConsoleText consoleText, int length){
        String format = String.format("%%-%s.%ss", length, length);
        printNewLine(String.format(format, consoleText.toString()), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());
    }

    public static void printNewLine(ConsoleMultiColorText consoleMultiColorText, int totalLength){
        printText(consoleMultiColorText, totalLength);
        goToNextLine();
    }

    public static void goToNextLine() {
        printText("\n");
    }

    public static void blankLines(int lines){
        com.apps.util.Console.blankLines(lines);
    }

    public static void clear(){
        com.apps.util.Console.clear();
    }

    public static void pause(long var0) {
        com.apps.util.Console.pause(var0);
    }
}
