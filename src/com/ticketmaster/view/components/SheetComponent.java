package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.Console;
import com.ticketmaster.view.utils.ConsoleText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class SheetComponent implements PassiveConsoleComponent{
    private Map<String, Integer> headerList = new TreeMap<>();
    private List<List<ConsoleText>> contentMap = new ArrayList<>();

    public SheetComponent(Map<String, Integer> headerList, List<List<ConsoleText>> content){
        updateSheetComponentContent(headerList, content);
    }

    public void updateSheetComponentContent(List<List<ConsoleText>> content){
        this.contentMap = content;
    }

    public void updateSheetComponentContent(Map<String, Integer> headerList, List<List<ConsoleText>> content){
        this.headerList = headerList;
        updateSheetComponentContent(content);
    }

    private void printHeaders(){

    }

    @Override
    public void show() {
        Console.printTable(headerList, contentMap);
    }
}
