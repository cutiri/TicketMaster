package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleMultiColorText;
import com.ticketmaster.view.utils.ConsoleText;
import com.ticketmaster.view.utils.ConsoleTextColor;

import java.util.*;

public class SheetComponent implements PassiveConsoleComponent{
    private Map<String, Integer> headerMap = new TreeMap<>();
    private List<List<ConsoleText>> contentList = new ArrayList<>();
    private ConsoleMultiColorText bannerMessage;
    private String tableName;
    private int currentPage;
    private int totalPages;
    private int totalRows;
    private int tableWidth;


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
        printHeader(format, horizontalSeparator);
        printBody(format, headerMap.values());

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

    private void printBody(String format, Collection<Integer> columnSize){
        for (var row : contentList){
            //printRow(format, row);
            printRow(columnSize, row);
        }
    }

    private void printRow(String format, List<ConsoleText> rowData){
        String rowText = String.format(format, rowData.toArray());

        Console.printNewLine(rowText);
    }

    private void printRow(Collection<Integer> columnSize, List<ConsoleText> rowData){
        int index = 0;
        Console.printText("| ");
        for(Integer column : columnSize){
            Console.printText(rowData.get(index), column);
            Console.printText(" | ");
            index++;
        }
        Console.goToNextLine();
    }

    private String generateFormat(Collection<Integer> values){
        StringBuilder stringBuilder = new StringBuilder("|");

        for (Integer value : values){
            stringBuilder.append(String.format(" %%-%s.%ss |", value, value));
        }

        return stringBuilder.toString();
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
}
