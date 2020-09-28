package pushgaming.test.task.demo.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pushgaming.test.task.demo.Balance;
import pushgaming.test.task.demo.model.Dices;

@Service
public class CalculatorService {
    private final int eyesOfSnakeMultiplier;
    private final int usualPairMultiplier;

    public CalculatorService(@Value("${eyes.of.snake.multiplier}") int eyesOfSnakeMultiplier,
                             @Value("${any.other.pair.of.integers.multiplier}") int usualPairMultiplier) {
        this.eyesOfSnakeMultiplier = eyesOfSnakeMultiplier;
        this.usualPairMultiplier = usualPairMultiplier;
    }

    public BigDecimal calculateWinning(Dices dices, BigDecimal stake) {
        BigDecimal winning;
        if (dices.getDiceOne() == dices.getDiceTwo()) {
            if (dices.getDiceOne() == 1) {
                winning = stake.multiply(BigDecimal.valueOf(eyesOfSnakeMultiplier));
            } else {
                winning = stake.multiply(BigDecimal.valueOf(usualPairMultiplier));
            }
        } else {
            winning = stake.negate();
        }
        Balance.adjustBalance(winning);
        return winning;
    }
}
