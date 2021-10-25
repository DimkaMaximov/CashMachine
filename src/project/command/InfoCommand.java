package project.command;

import project.CashMachine;
import project.ConsoleHelper;
import project.CurrencyManipulator;
import project.CurrencyManipulatorFactory;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

class InfoCommand implements Command{

    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {

        ConsoleHelper.writeMessage(res.getString("before"));
        Collection map = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (map.size()==0 || map.isEmpty())
            ConsoleHelper.writeMessage(res.getString("no.money"));
        else {
            int count = 0;
            for (Iterator<CurrencyManipulator> iterator = map.iterator(); iterator.hasNext(); )
            {
                CurrencyManipulator pair =  iterator.next();
                if (pair.hasMoney())
                {
                    count++;
                    ConsoleHelper.writeMessage( pair.getCurrencyCode()+ " - " + pair.getTotalAmount());
                }
            }
            if (count == 0)
                ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
