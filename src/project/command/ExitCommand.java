package project.command;

import project.CashMachine;
import project.ConsoleHelper;
import project.exception.InterruptOperationException;
import java.util.ResourceBundle;

class ExitCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        if (ConsoleHelper.readString().equals("y")) ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
