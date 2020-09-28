package pushgaming.test.task.demo;

import org.junit.Assert;
import org.junit.Test;
import pushgaming.test.task.demo.mapper.Mapper;
import pushgaming.test.task.demo.model.Dices;
import pushgaming.test.task.demo.model.GameResult;

import java.math.BigDecimal;

public class MapperTest {
    private final static Dices TEST_DICES = new Dices(1, 1);
    private final static BigDecimal TEST_STAKE = BigDecimal.valueOf(10);
    private final static BigDecimal TEST_WINNING = BigDecimal.valueOf(300);

    @Test
    public void testMapper() {
        GameResult actual = new Mapper().getGameResult(TEST_DICES, TEST_STAKE, TEST_WINNING);
        GameResult expected = new GameResult();
        expected.setDiceOne(TEST_DICES.getDiceOne());
        expected.setDiceTwo(TEST_DICES.getDiceTwo());
        expected.setStake(TEST_STAKE);
        expected.setWinning(TEST_WINNING);
        expected.setCurrentBalance(Balance.getCurrentMoneyBalance());
        Assert.assertEquals(expected, actual);
    }
}
