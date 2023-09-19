package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SheetComponent implements PassiveConsoleComponent{
    private Map<String, Integer> headerList = new TreeMap<>();
    private List<List<ConsoleText>> contentMap = new ArrayList<>();

    public SheetComponent(){

    }

    public SheetComponent(Map<String, Integer> headerList, List<List<ConsoleText>> content){
        setSheetComponentContent(headerList, content);
    }

    public void setSheetComponentContent(List<List<ConsoleText>> content){
        this.contentMap = content;
    }

    public void setSheetComponentContent(Map<String, Integer> headerList, List<List<ConsoleText>> content){
        this.headerList = headerList;
        setSheetComponentContent(content);
    }

    private void printHeaders(){

    }

    @Override
    public void show() {
        Console.printTable(headerList, contentMap);
    }
}
