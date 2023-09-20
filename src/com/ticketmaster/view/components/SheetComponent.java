package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleMultiColorText;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class SheetComponent implements PassiveConsoleComponent{
    private Map<String, Integer> headerMap = new TreeMap<>();
    private List<List<ConsoleText>> contentList = new ArrayList<>();
    private ConsoleMultiColorText bannerMessage = new ConsoleMultiColorText(new ConsoleText(""));
    private String tableName;
    private int currentPage;
    private int totalPages;
    private int totalRows;
    private int tableWidth;
    private boolean showHeader = true;
    private boolean multiLineRows = false;
    private boolean rowSeparator = false;


    public SheetComponent(){

    }

    public SheetComponent(Map<String, Integer> headerList, List<List<ConsoleText>> content){
        setSheetComponentContent(headerList, content);
    }

    public void setSheetComponentContent(List<List<ConsoleText>> content){
        this.contentList = content;
    }

    public void setSheetComponentContent(Map<String, Integer> headerList, List<List<ConsoleText>> content){
        this.headerMap = headerList;
        setSheetComponentContent(content);
    }

    private void printHeaders(){

    }

    @Override
    public void show() {
        String format = generateFormat(headerMap.values());
        String horizontalSeparator = generateHorizontalSeparator();
        tableWidth = horizontalSeparator.length();
        String finalSeparator = "+" + "-".repeat(tableWidth - 2) + "+";


        printBanner(finalSeparator);
        if(hasShowHeader())
            printHeader(format, horizontalSeparator);
        printBody(format, List.copyOf(headerMap.values()));

        Console.printNewLine(horizontalSeparator.toString());

        printFooter();

        Console.printNewLine(finalSeparator.toString());
    }

    private void printFooter() {
        Console.printText("| ");
        Console.printText(new ConsoleMultiColorText(
                new ConsoleText("       Pages "),
                new ConsoleText(Integer.toString(currentPage), ConsoleTextColor.GREEN),
                new ConsoleText("/"),
                new ConsoleText(Integer.toString(totalPages)),
                new ConsoleText("    Total: "),
                new ConsoleText(Integer.toString(totalRows), ConsoleTextColor.CYAN)
        ), tableWidth - 4);
        Console.printNewLine(" |");
    }

    private void printBanner(String finalSeparator){
        Console.printNewLine(finalSeparator.toString());

        Console.printText("| ");
        Console.printText(bannerMessage, tableWidth - 4);
        Console.printNewLine(" |");
    }

    private void printHeader(String format, String horizontalSeparator){
        int count = headerMap.values().stream().mapToInt(i -> i.intValue()).sum() + headerMap.values().size() + 1;
        String bars = "-".repeat(count + 1);

        String header = String.format(format, headerMap.keySet().toArray());

        Console.printNewLine(horizontalSeparator.toString());
        Console.printNewLine(header);
        Console.printNewLine(horizontalSeparator.toString());
    }

    private String generateHorizontalSeparator(){
        StringBuilder separatorStringBuilder = new StringBuilder("+");

        for(Map.Entry<String, Integer> entry : headerMap.entrySet()){
            separatorStringBuilder.append("-".repeat(entry.getValue() + 2)).append("+");
        }

        return separatorStringBuilder.toString();
    }

    private void printBody(String format, List<Integer> columnSizes){
        for(int i = 0; i < contentList.size(); i++){
        //for (var row : contentList){
            printRow(List.copyOf(headerMap.values()), contentList.get(i));
            //printRow(List.copyOf(headerMap.values()), row);
            if(hasRowSeparator() && i != contentList.size() - 1)
                Console.printNewLine(generateHorizontalSeparator().toString());
        }
    }

    private void printRow(String format, List<ConsoleText> rowData){
        String rowText = String.format(format, rowData.toArray());

        Console.printNewLine(rowText);
    }

    /*
     * This printRow allows to have different colors per column
     */
    private void printRow(List<Integer> columnSizes, List<ConsoleText> rowData){
        if(hasMultiLineRows()) {
            List<List<String>> textList = new ArrayList<>();
            int maxLines = 0;
            for (int i = 0; i < rowData.size(); i++) {
                List<String> list = (splitInLines(columnSizes.get(i), rowData.get(i).toString()));
                maxLines = list.size() > maxLines ? list.size() : maxLines;
                textList.add(list);
            }
            for (int lineNumber = 0; lineNumber < maxLines; lineNumber++) {
                Console.printText("| ");
                for (int columnNumber = 0; columnNumber < rowData.size(); columnNumber++) {
                    if (textList.get(columnNumber).size() > lineNumber) {
                        Console.printText(textList.get(columnNumber).get(lineNumber), rowData.get(columnNumber).getConsoleTextColor(), rowData.get(columnNumber).getConsoleTextBackgroundColor(), columnSizes.get(columnNumber));
                    } else {
                        Console.printText(" ", columnSizes.get(columnNumber));
                    }
                    Console.printText(" | ");
                }
                Console.goToNextLine();
            }
        }else {
            int index = 0;
            Console.printText("| ");
            for(Integer columnSize : columnSizes){
                Console.printText(rowData.get(index), columnSize);
                Console.printText(" | ");
                index++;
            }
            Console.goToNextLine();
        }
    }

    /*
     * This method splits a String into multiple words, each word will be an element in a List of String
     * It will keep each element in the List not longer than the maxLength
     * If a word is longer than maxLength then it will split that word to fit it
     */
    public List<String> splitInLines(int maxLength, String text){
        List<String> result = new ArrayList<>();
        String[] words = text.split("\\s+");

        StringBuilder lineBuilder = new StringBuilder();
        for(String word : words){
            if(lineBuilder.length() + word.length() <= maxLength){
                lineBuilder.append(word + " ");
            }else{
                if(lineBuilder.length() != 0)
                    result.add(lineBuilder.toString());
                lineBuilder = new StringBuilder();
                if(word.length() > maxLength){
                    String[] splittedWord = splitWord(word, maxLength);
                    for(int i = 0; i < splittedWord.length - 1; i++){
                        result.add(splittedWord[i]);
                    }
                    lineBuilder.append(splittedWord[splittedWord.length - 1]);
                }else{
                    lineBuilder.append(word + " ");
                }
            }
        }
        result.add(lineBuilder.toString());
        return result;
    }

    private String[] splitWord(String word, int maxLength){
        return word.split(String.format("(?<=\\G.{%s})", maxLength));
    }

    private String generateFormat(Collection<Integer> values){
        StringBuilder stringBuilder = new StringBuilder("|");

        for (Integer value : values){
            stringBuilder.append(String.format(" %%-%s.%ss |", value, value));
        }

        return stringBuilder.toString();
    }

    private String generateSingleColumnFormat(int value){
        return String.format(" %%-%s.%ss |", value, value);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setBannerMessage(ConsoleMultiColorText bannerMessage) {
        this.bannerMessage = bannerMessage;
    }

    public boolean hasShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean hasMultiLineRows() {
        return multiLineRows;
    }

    public void setMultiLineRows(boolean multiLineRows) {
        this.multiLineRows = multiLineRows;
    }

    public boolean hasRowSeparator() {
        return rowSeparator;
    }

    public void setRowSeparator(boolean rowSeparator) {
        this.rowSeparator = rowSeparator;
    }
}
