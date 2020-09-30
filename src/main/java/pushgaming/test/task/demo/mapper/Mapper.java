package pushgaming.test.task.demo.mapper;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import pushgaming.test.task.demo.Balance;
import pushgaming.test.task.demo.model.Dices;
import pushgaming.test.task.demo.model.GameResult;

@Component
public class Mapper {
    public GameResult getGameResult(Dices dices, BigDecimal stake, BigDecimal winning) {
        GameResult result = new GameResult();
        result.setDiceOne(dices.getDiceOne());
        result.setDiceTwo(dices.getDiceTwo());
        result.setStake(stake);
        result.setWinning(winning);
        result.setCurrentBalance(Balance.getCurrentMoneyBalance());
        return result;
    }
}
