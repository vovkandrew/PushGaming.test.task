package pushgaming.test.task.demo;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BalanceTest {
    private static final BigDecimal POSITIVE_BALANCE_ADJUSTMENT = BigDecimal.valueOf(100);
    private static final BigDecimal POSITIVE_ADJUSTED_BALANCE = BigDecimal.valueOf(1100);
    private static final BigDecimal POSITIVE_FLOAT_BALANCE_ADJUSTMENT = BigDecimal.valueOf(10.50);
    private static final BigDecimal POSITIVE_FLOAT_ADJUSTED_BALANCE = BigDecimal.valueOf(1010.50);
    private static final BigDecimal NEGATIVE_BALANCE_ADJUSTMENT = BigDecimal.valueOf(-150);
    private static final BigDecimal NEGATIVE_ADJUSTED_BALANCE = BigDecimal.valueOf(850);
    private static final BigDecimal NEGATIVE_FLOAT_BALANCE_ADJUSTMENT = BigDecimal.valueOf(-15.10);
    private static final BigDecimal NEGATIVE_FLOAT_ADJUSTED_BALANCE = BigDecimal.valueOf(984.90);
    private static final BigDecimal STARTING_BALANCE = BigDecimal.valueOf(1000);
    private static final BigDecimal TEST_BALANCE_ADJUSTMENT_ONE = BigDecimal.valueOf(150);
    private static final BigDecimal TEST_BALANCE_ADJUSTMENT_TWO = BigDecimal.valueOf(-80);
    private static final BigDecimal TEST_BALANCE_ADJUSTMENT_THREE = BigDecimal.valueOf(8.25);

    @Before
    public void resetBalance() {
        Balance.resetCurrentBalance();
    }

    @Test
    public void testAdjustBalancePositive() {
        Balance.adjustBalance(POSITIVE_BALANCE_ADJUSTMENT);
        BigDecimal actual = Balance.getCurrentMoneyBalance();
        Assert.assertEquals(POSITIVE_ADJUSTED_BALANCE, actual);
    }

    @Test
    public void testAdjustBalanceNegative() {
        Balance.adjustBalance(NEGATIVE_BALANCE_ADJUSTMENT);
        BigDecimal actual = Balance.getCurrentMoneyBalance();
        Assert.assertEquals(NEGATIVE_ADJUSTED_BALANCE, actual);
    }

    @Test
    public void testAdjustBalancePositiveFloat() {
        Balance.adjustBalance(POSITIVE_FLOAT_BALANCE_ADJUSTMENT);
        BigDecimal actual = Balance.getCurrentMoneyBalance();
        Assert.assertEquals(POSITIVE_FLOAT_ADJUSTED_BALANCE, actual);
    }

    @Test
    public void testAdjustBalanceNegativeFloat() {
        Balance.adjustBalance(NEGATIVE_FLOAT_BALANCE_ADJUSTMENT);
        BigDecimal actual = Balance.getCurrentMoneyBalance();
        Assert.assertEquals(NEGATIVE_FLOAT_ADJUSTED_BALANCE, actual);
    }

    @Test
    public void testStartingBalance() {
        BigDecimal actual = Balance.getCurrentMoneyBalance();
        Assert.assertEquals(STARTING_BALANCE, actual);
    }

    @Test
    public void testResetBalance() {
        Balance.adjustBalance(TEST_BALANCE_ADJUSTMENT_ONE);
        Balance.adjustBalance(TEST_BALANCE_ADJUSTMENT_TWO);
        Balance.adjustBalance(TEST_BALANCE_ADJUSTMENT_THREE);
        Balance.resetCurrentBalance();
        BigDecimal actual = Balance.getCurrentMoneyBalance();
        Assert.assertEquals(STARTING_BALANCE, actual);
    }

    @After
    public void reset() {
        Balance.resetCurrentBalance();
    }
}
