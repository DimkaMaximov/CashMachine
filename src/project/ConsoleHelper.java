package project;

import project.exception.InterruptOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+".resources.common_en");


    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String request = null;
        try {
            request = bis.readLine();
            if(request.equalsIgnoreCase("EXIT")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException ignored) {
        }
        return request;
    }

    // считать код валюты
    public static String askCurrencyCode() throws InterruptOperationException {
        String cod = null;
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            if ((cod = ConsoleHelper.readString()).trim().length() == 3) break;
            writeMessage(res.getString("invalid.data"));
        }
        return cod.toUpperCase();
    }

    // считать номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] array;
        while (true) {
            writeMessage(res.getString("choose.denomination.and.count.format"));
            String request = readString();
            array = request.trim().split(" ");
            int nominal, quantity;
            try {
                nominal = Integer.parseInt(array[0]);
                quantity = Integer.parseInt(array[1]);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (nominal <= 0 || quantity <= 0 || array.length > 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return array;
    }

    // спросить у пользователя операцию
    public static Operation askOperation() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        ConsoleHelper.writeMessage(res.getString("operation.INFO"));
        ConsoleHelper.writeMessage(res.getString("operation.DEPOSIT"));
        ConsoleHelper.writeMessage(res.getString("operation.WITHDRAW"));
        ConsoleHelper.writeMessage(res.getString("operation.EXIT"));
        while (true) {
            String request = readString();
            if (Integer.parseInt(request) > 0 && Integer.parseInt(request) < 5)
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(request));
            else {
                writeMessage(res.getString("invalid.data"));
                ConsoleHelper.writeMessage(res.getString("choose.operation"));
            }
        }
    }

    public static void printExitMessage(){

        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
