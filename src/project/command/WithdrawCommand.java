package project.command;

import project.CashMachine;
import project.ConsoleHelper;
import project.CurrencyManipulator;
import project.CurrencyManipulatorFactory;
import project.exception.InterruptOperationException;
import project.exception.NotEnoughMoneyException;


import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String request = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(request);
        int sumRequest = 0;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                sumRequest = Integer.parseInt(ConsoleHelper.readString());
            } catch (Exception e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(sumRequest))
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));

            try {
                manipulator.withdrawAmount(sumRequest);

            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            break;
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sumRequest, request));
    }
}
