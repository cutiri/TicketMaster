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

    public static void printText(ConsoleText consoleText, int length){
        String format = String.format("%%-%s.%ss", length, length);
        printText(String.format(format, consoleText.toString()), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());
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

    public static void printNewLine(ConsoleText consoleText, int length){
        String format = String.format("%%-%s.%ss", length, length);
        printNewLine(String.format(format, consoleText.toString()), consoleText.getConsoleTextColor(), consoleText.getConsoleTextBackgroundColor());
    }

    public static void blankLines(int lines){
        com.apps.util.Console.blankLines(lines);
    }

    public static void clear(){
        //com.apps.util.Console.clear();
        ProcessBuilder var0 = os.contains("windows") ? new ProcessBuilder(new String[]{"cmd", "/c", "cls"}) : new ProcessBuilder(new String[]{"clear"});

        try {
            var0.inheritIO().start().waitFor();
        } catch (InterruptedException var2) {
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    public static void pause(long var0) {
        try {
            Thread.sleep(var0);
        } catch (InterruptedException var3) {
        }

    }

    public static void printTable(Map<String, Integer> headerMap, List<List<ConsoleText>> data){
        String format = generateFormat(headerMap.values());
        String horizontalSeparator = generateHorizontalSeparator(headerMap);

        printHeader(headerMap, format, horizontalSeparator);
        printBody(data, format, headerMap.values());

        printNewLine(horizontalSeparator.toString());
    }

    private static void printHeader(Map<String, Integer> headerMap, String format, String horizontalSeparator){
        int count = headerMap.values().stream().mapToInt(i -> i.intValue()).sum() + headerMap.values().size() + 1;
        String bars = "-".repeat(count + 1);

        String header = String.format(format, headerMap.keySet().toArray());

        printNewLine(horizontalSeparator.toString());
        printNewLine(header);
        printNewLine(horizontalSeparator.toString());
    }

    private static String generateHorizontalSeparator(Map<String, Integer> headerMap){
        StringBuilder separatorStringBuilder = new StringBuilder("+");

        for(Map.Entry<String, Integer> entry : headerMap.entrySet()){
            separatorStringBuilder.append("-".repeat(entry.getValue() + 2)).append("+");
        }

        return separatorStringBuilder.toString();
    }

    private static void printBody(List<List<ConsoleText>> data, String format, Collection<Integer> columnSize){
        for (var row : data){
            //printRow(format, row);
            printRow(columnSize, row);
        }
    }

    private static void printRow(String format, List<ConsoleText> rowData){
        String rowText = String.format(format, rowData.toArray());

        printNewLine(rowText);
    }

    private static void printRow(Collection<Integer> columnSize, List<ConsoleText> rowData){
        int index = 0;
        printText("| ");
        for(Integer column : columnSize){
            printText(rowData.get(index), column);
            printText(" | ");
            index++;
        }
        goToNextLine();
    }

    private static void goToNextLine() {
        printText("\n");
    }

    private static String generateFormat(Collection<Integer> values){
        StringBuilder stringBuilder = new StringBuilder("|");

        for (Integer value : values){
            stringBuilder.append(String.format(" %%-%s.%ss |", value, value));
        }

        return stringBuilder.toString();
    }
}
