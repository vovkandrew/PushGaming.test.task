package pushgaming.test.task.demo;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pushgaming.test.task.demo.model.Dices;
import pushgaming.test.task.demo.service.CalculatorService;

public class CalculatorTest {
    private static final int BIG_WIN_MULT = 30;
    private static final int SMALL_WIN_MULT = 7;
    private static final Dices TEST_DICES_SNAKE_EYES = new Dices(1, 1);
    private static final Dices TEST_DICES_OTHER_PAIR = new Dices(4, 4);
    private static final Dices TEST_DICES_NO_PAIR = new Dices(2, 5);
    private static final BigDecimal BIG_STAKE = BigDecimal.valueOf(10);
    private static final BigDecimal BIG_STAKE_SNAKE_EYES_WIN = BigDecimal.valueOf(300);
    private static final BigDecimal BIG_STAKE_SNAKE_EYES_BALANCE_AFTER_WIN = BigDecimal.valueOf(1300);
    private static final BigDecimal BIG_STAKE_OTHER_PAIR_WIN = BigDecimal.valueOf(70);
    private static final BigDecimal BIG_STAKE_OTHER_PAIR_BALANCE_AFTER_WIN = BigDecimal.valueOf(1070);
    private static final BigDecimal BIG_STAKE_NO_WIN = BigDecimal.valueOf(-10);
    private static final BigDecimal BIG_STAKE_NO_WIN_BALANCE = BigDecimal.valueOf(990);
    private static final BigDecimal MID_STAKE = BigDecimal.valueOf(2);
    private static final BigDecimal MID_STAKE_SNAKE_EYES_WIN = BigDecimal.valueOf(60);
    private static final BigDecimal MID_STAKE_SNAKE_BALANCE_AFTER_WIN = BigDecimal.valueOf(1060);
    private static final BigDecimal MID_STAKE_OTHER_PAIR_WIN = BigDecimal.valueOf(14);
    private static final BigDecimal MID_STAKE_OTHER_BALANCE_AFTER_WIN = BigDecimal.valueOf(1014);
    private static final BigDecimal MID_STAKE_NO_WIN = BigDecimal.valueOf(-2);
    private static final BigDecimal MID_STAKE_NO_WIN_BALANCE = BigDecimal.valueOf(998);
    private static final BigDecimal SMALL_STAKE = BigDecimal.valueOf(1);
    private static final BigDecimal SMALL_STAKE_SNAKE_EYES_WIN = BigDecimal.valueOf(30);
    private static final BigDecimal SMALL_STAKE_SNAKE_EYES_BALANCE_AFTER_WIN = BigDecimal.valueOf(1030);
    private static final BigDecimal SMALL_STAKE_OTHER_PAIR_WIN = BigDecimal.valueOf(7);
    private static final BigDecimal SMALL_STAKE_OTHER_PAIR_BALANCE_AFTER_WIN = BigDecimal.valueOf(1007);
    private static final BigDecimal SMALL_STAKE_NO_WIN = BigDecimal.valueOf(-1);
    private static final BigDecimal SMALL_STAKE_NO_WIN_BALANCE = BigDecimal.valueOf(999);
    private final CalculatorService calculatorService = new CalculatorService(BIG_WIN_MULT, SMALL_WIN_MULT);

    @Before
    public void resetBalance() {
        Balance.resetCurrentBalance();
    }

    @Test
    public void testCalculatorServiceBigStakeWithSnakeEyes() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_SNAKE_EYES, BIG_STAKE);
        Assert.assertEquals(BIG_STAKE_SNAKE_EYES_WIN, actual);
        Assert.assertEquals(BIG_STAKE_SNAKE_EYES_BALANCE_AFTER_WIN, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceBigStakeWithOtherPair() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_OTHER_PAIR, BIG_STAKE);
        Assert.assertEquals(BIG_STAKE_OTHER_PAIR_WIN, actual);
        Assert.assertEquals(BIG_STAKE_OTHER_PAIR_BALANCE_AFTER_WIN, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceBigStakeNoPair() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_NO_PAIR, BIG_STAKE);
        Assert.assertEquals(BIG_STAKE_NO_WIN, actual);
        Assert.assertEquals(BIG_STAKE_NO_WIN_BALANCE, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceMidStakeWithSnakeEyes() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_SNAKE_EYES, MID_STAKE);
        Assert.assertEquals(MID_STAKE_SNAKE_EYES_WIN, actual);
        Assert.assertEquals(MID_STAKE_SNAKE_BALANCE_AFTER_WIN, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceMidStakeWithOtherPair() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_OTHER_PAIR, MID_STAKE);
        Assert.assertEquals(MID_STAKE_OTHER_PAIR_WIN, actual);
        Assert.assertEquals(MID_STAKE_OTHER_BALANCE_AFTER_WIN, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceMidStakeNoPair() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_NO_PAIR, MID_STAKE);
        Assert.assertEquals(MID_STAKE_NO_WIN, actual);
        Assert.assertEquals(MID_STAKE_NO_WIN_BALANCE, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceSmallStakeWithSnakeEyes() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_SNAKE_EYES, SMALL_STAKE);
        Assert.assertEquals(SMALL_STAKE_SNAKE_EYES_WIN, actual);
        Assert.assertEquals(SMALL_STAKE_SNAKE_EYES_BALANCE_AFTER_WIN, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceSmallStakeWithOtherPair() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_OTHER_PAIR, SMALL_STAKE);
        Assert.assertEquals(SMALL_STAKE_OTHER_PAIR_WIN, actual);
        Assert.assertEquals(SMALL_STAKE_OTHER_PAIR_BALANCE_AFTER_WIN, Balance.getCurrentMoneyBalance());
    }

    @Test
    public void testCalculatorServiceSmallStakeWithNoPair() {
        BigDecimal actual = calculatorService.calculateWinning(TEST_DICES_NO_PAIR, SMALL_STAKE);
        Assert.assertEquals(SMALL_STAKE_NO_WIN, actual);
        Assert.assertEquals(SMALL_STAKE_NO_WIN_BALANCE, Balance.getCurrentMoneyBalance());
    }

    @After
    public void reset() {
        Balance.resetCurrentBalance();
    }
}
