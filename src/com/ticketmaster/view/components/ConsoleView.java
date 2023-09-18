package com.ticketmaster.view.components;


import com.apps.util.Console;
import com.ticketmaster.view.utils.DialogResult;

import java.util.ArrayList;
import java.util.List;

public class ConsoleView extends ActiveConsoleComponent {
    private final List<PassiveConsoleComponent> componentList = new ArrayList<>();
    private final List<ActiveConsoleComponent> inputCollectorsList = new ArrayList<>();


    @Override
    public DialogResult show(){
        DialogResult result = DialogResult.AWAITING;

        restartInputCollectors();

        ActiveConsoleComponent lastInputCollector = inputCollectorsList.get(inputCollectorsList.size() - 1);

        while (result != DialogResult.ESCAPE && lastInputCollector.getCurrentState() != DialogResult.SUCCESS) {
            Console.clear();
            showComponents();
            result = showInputCollectors();
        }

        return result;
    }

    private void restartInputCollectors(){
        for (ActiveConsoleComponent inputCollector : inputCollectorsList){
            inputCollector.resetCurrentState();
        }
    }

    private void showComponents(){
        for (PassiveConsoleComponent consoleComponent : componentList){
            consoleComponent.show();
        }
    }

    private DialogResult showInputCollectors(){
        DialogResult result = DialogResult.AWAITING;

        for (ActiveConsoleComponent inputCollector : inputCollectorsList) {
            DialogResult previousDialogResult = inputCollector.getCurrentState();
            result = inputCollector.show();

            if(result != DialogResult.SUCCESS || previousDialogResult == DialogResult.INPUT_ERROR)
                return result;
        }

        return result;
    }

    public List<String> getUserInputs(){
        List<String> result = new ArrayList<>();
        for (ActiveConsoleComponent inputCollector : inputCollectorsList){
            result.add(inputCollector.getCollectedInput());
        }
        return result;
    }

    public void addInputCollector(InputCollector inputCollector){
        inputCollectorsList.add(inputCollector);
    }

    public void addPassiveComponents(PassiveConsoleComponent consoleComponent){
        componentList.add(consoleComponent);
    }
}
