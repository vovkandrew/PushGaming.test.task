package pushgaming.test.task.demo;

import java.math.BigDecimal;

public class Balance {
    private static final BigDecimal STARTING_BALANCE = BigDecimal.valueOf(1000);
    private static BigDecimal currentBalance = STARTING_BALANCE;

    public static void adjustBalance(BigDecimal number) {
        currentBalance = currentBalance.add(number);
    }

    public static BigDecimal getCurrentMoneyBalance() {
        return currentBalance;
    }

    public static void resetCurrentBalance() {
        currentBalance = STARTING_BALANCE;
    }
}
