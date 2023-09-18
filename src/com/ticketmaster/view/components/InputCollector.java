package com.ticketmaster.view.components;

import com.ticketmaster.view.utils.ConsoleTextColor;
import com.ticketmaster.view.utils.DialogResult;
import com.ticketmaster.view.utils.RegexSelector;
import com.ticketmaster.view.utils.Console;

import java.util.Scanner;

class InputCollector extends ActiveConsoleComponent {

    private String text;
    private String regex;
    private String errorMsg;
    private String escapeText;

    private Scanner scanner = new Scanner(System.in);

    public InputCollector(String text, String regex, String errorMsg, String escapeText){
        this.text = text;
        this.regex = regex;
        this.errorMsg = errorMsg;
        this.escapeText = escapeText;

        //If the regex is null or empty then we set it to .* which will match everything the user enters
        if(regex == null || regex.equals("")){
            this.regex = RegexSelector.ANYTHING.getRegex();
        }
    }

    @Override
    public DialogResult show(){
        if(getCurrentState() == DialogResult.INPUT_ERROR)
            Console.printNewLine(errorMsg, ConsoleTextColor.RED);

        Console.printText(text);

        if(getCurrentState() != DialogResult.SUCCESS) {
            setCollectedInput(scanner.nextLine());
        }else{
            Console.printNewLine(getCollectedInput(), ConsoleTextColor.GREEN);
        }

        if(getCollectedInput().equals(escapeText)){
            setCurrentState(DialogResult.ESCAPE);
            return getCurrentState();
        }

        if(getCollectedInput().matches(regex)){
            setCurrentState(DialogResult.SUCCESS);
        }else{
            setCurrentState(DialogResult.INPUT_ERROR);
        }

        return getCurrentState();
    }

    public String prompt(String var1, String var2, String var3) {
        String var4 = null;
        boolean var5 = false;

        while(!var5) {
            System.out.print(var1);
            var4 = this.scanner.nextLine();
            var5 = var4.matches(var2);
            if (var5) {
                break;
            }

            System.out.println(var3);
        }

        return var4;
    }
}
