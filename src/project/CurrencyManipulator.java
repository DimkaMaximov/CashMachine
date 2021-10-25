package project;

import project.exception.NotEnoughMoneyException;
import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private  Map<Integer, Integer> denominations = new HashMap<>();
//    static{
//        denominations.put(500, 2);
//        denominations.put(200, 3);
//        denominations.put(100, 1);
//        denominations.put(50, 12);
//    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    // добавить номинал и сумму
    public void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination)) denominations.put(denomination, denominations.get(denomination)+count);
        else denominations.put(denomination, count);
    }
    // вывести сумму конкретной валюты
    public int getTotalAmount(){
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair: denominations.entrySet()){
            sum = sum + (pair.getKey() * pair.getValue());
        }
        return sum;
    }

    // добавлены ли какие-то банкноты или нет
    public boolean hasMoney(){
        return denominations.size() != 0;
    }

    // достаточно ли средств на счету.
    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount()>=expectedAmount;
    }

    // списать деньги со счета
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int sum = expectedAmount;
        HashMap<Integer, Integer> temp = new HashMap<>(denominations);
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            list.add(pair.getKey());

        Collections.sort(list);
        Collections.reverse(list);

        TreeMap<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (Integer aList : list) {
            int key = aList;
            int value = temp.get(key);
            while (true) {
                if (sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;

                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }
        if (sum > 0)
            throw new NotEnoughMoneyException();
        else
        {
            for (Map.Entry<Integer, Integer> pair : result.entrySet())
                ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());

            denominations.clear();
            denominations.putAll(temp);
            ConsoleHelper.writeMessage("Транзакция выполнена успешно");
        }
        return result;
    }
}
