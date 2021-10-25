package project.command;

import project.CashMachine;
import project.ConsoleHelper;
import project.CurrencyManipulator;
import project.CurrencyManipulatorFactory;
import project.exception.InterruptOperationException;
import java.util.ResourceBundle;

class DepositCommand implements Command {

    private  ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String [] arg = new String[0];
        try {
            arg = ConsoleHelper.getValidTwoDigits(code);
            int sum = Integer.parseInt(arg[0]) * Integer.parseInt(arg[1]);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, code));

        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator.addAmount(Integer.parseInt(arg[0]), Integer.parseInt(arg[1]));

        System.out.println(currencyManipulator.getTotalAmount());

    }
}
