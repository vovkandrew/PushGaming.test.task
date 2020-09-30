package pushgaming.test.task.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pushgaming.test.task.demo.exception.BalanceException;
import pushgaming.test.task.demo.exception.StakeException;
import pushgaming.test.task.demo.mapper.Mapper;
import pushgaming.test.task.demo.model.Dices;
import pushgaming.test.task.demo.model.GameResult;
import pushgaming.test.task.demo.service.CalculatorService;
import pushgaming.test.task.demo.service.DiceRollerService;
import pushgaming.test.task.demo.service.GameService;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    private static final List<Double> LIST_OF_STAKES = Arrays.asList(1.00,2.00,10.00);
    private static final Dices TEST_DICES = new Dices(1, 1);
    private static final double TEST_STAKE = 10.00;
    private static final BigDecimal TEST_WINNING = BigDecimal.valueOf(300);
    private static final GameResult TEST_GAME_RESULT = new GameResult(TEST_DICES.getDiceOne(),
                    TEST_DICES.getDiceTwo(),
                    BigDecimal.valueOf(TEST_STAKE),
                    TEST_WINNING,
                    Balance.getCurrentMoneyBalance());
    private static final GameResult EXPECTED_GAME_RESULT = new GameResult(1, 1,
            BigDecimal.valueOf(10.00),
            BigDecimal.valueOf(300),
            Balance.getCurrentMoneyBalance());
    private static final double TEST_WRONG_STAKE = 3.00;
    private static final BigDecimal TEST_BALANCE_ADJUSTMENT = BigDecimal.valueOf(-995);

    @Mock
    private DiceRollerService diceRollerService;

    @Mock
    private CalculatorService calculatorService;

    @Mock
    private Mapper mapper;

    private GameService gameService;

    @Before
    public void init() {
        gameService = new GameService(diceRollerService, calculatorService, mapper, LIST_OF_STAKES);
        Balance.resetCurrentBalance();
    }

    @Test
    public void testGameServiceGeneral() {
        when(diceRollerService.roll()).thenReturn(TEST_DICES);
        when(calculatorService.calculateWinning(TEST_DICES, BigDecimal.valueOf(TEST_STAKE)))
                .thenReturn(TEST_WINNING);
        when(mapper.getGameResult(TEST_DICES, BigDecimal.valueOf(TEST_STAKE), TEST_WINNING))
                .thenReturn(TEST_GAME_RESULT);
        GameResult actual = gameService.play(TEST_STAKE);
        Assert.assertEquals(EXPECTED_GAME_RESULT, actual);
        verify(diceRollerService).roll();
        verify(calculatorService).calculateWinning(TEST_DICES, BigDecimal.valueOf(TEST_STAKE));
        verify(mapper).getGameResult(TEST_DICES, BigDecimal.valueOf(TEST_STAKE), TEST_WINNING);
    }

    @Test(expected = StakeException.class)
    public void testGameServiceWithUnavailableStake() {
        gameService.play(TEST_WRONG_STAKE);
    }

    @Test(expected = BalanceException.class)
    public void testGameServiceWithZeroBalance() {
        Balance.adjustBalance(Balance.getCurrentMoneyBalance().negate());
        gameService.play(TEST_STAKE);
    }

    @Test(expected = BalanceException.class)
    public void testGameServiceWithNotEnoughAvailableBalance() {
        Balance.adjustBalance(TEST_BALANCE_ADJUSTMENT);
        gameService.play(TEST_STAKE);
    }

    @After
    public void reset() {
        Balance.resetCurrentBalance();
    }
}
