package project.command;

import project.CashMachine;
import project.ConsoleHelper;
import project.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private  ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {

        String cardNumber;
        String pin;

        ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {

            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cardNumber = ConsoleHelper.readString();
            pin = ConsoleHelper.readString();

            if (validCreditCards.containsKey(cardNumber)) {

                if (validCreditCards.getString(cardNumber).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
