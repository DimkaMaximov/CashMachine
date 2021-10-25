package project;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        if (i<=0 || i>4)  throw new IllegalArgumentException();
        switch (i){
            case 1: return Operation.INFO;
            case 2: return Operation.DEPOSIT;
            case 3: return Operation.WITHDRAW;
            case 4: return Operation.EXIT;
        }
        return null;
    }

}
