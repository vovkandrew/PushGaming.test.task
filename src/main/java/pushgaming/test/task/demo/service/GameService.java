package pushgaming.test.task.demo.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pushgaming.test.task.demo.Balance;
import pushgaming.test.task.demo.exception.BalanceException;
import pushgaming.test.task.demo.exception.StakeException;
import pushgaming.test.task.demo.mapper.Mapper;
import pushgaming.test.task.demo.model.Dices;
import pushgaming.test.task.demo.model.GameResult;

@Service
public class GameService {
    private static final int ZERO = 0;
    private final DiceRollerService rollerService;
    private final CalculatorService calculatorService;
    private final Mapper mapper;
    private final List<Double> availableStakes;
    private static final String WRONG_STAKE_MSG =
            "Unsupported stake, please consider choosing a stake of 1.00, 2.00 or 10.00 only to play Snake eyes game";
    private static final String ZERO_BALANCE_MSG =
            "Your personal balance is completely run out of money, please consider top it up";
    private static final String STAKE_HIGHER_THAN_REMAINING_BALANCE_MSG =
            "You don't have enough money on your personal balance to play with this stake, please choose lesser stake" +
                    " or top up your balance";

    public GameService(DiceRollerService rollerService, CalculatorService calculatorService,
                       Mapper mapper, @Value("${list.of.available.stakes}") List<Double> availableStakes) {
        this.rollerService = rollerService;
        this.calculatorService = calculatorService;
        this.mapper = mapper;
        this.availableStakes = availableStakes;
    }

    public GameResult play(double stake) {
        if(!availableStakes.contains(stake)) {
            throw new StakeException(WRONG_STAKE_MSG);
        }
        if (Balance.getCurrentMoneyBalance().equals(BigDecimal.ZERO)) {
            throw new BalanceException(ZERO_BALANCE_MSG);
        }
        if (Balance.getCurrentMoneyBalance().compareTo(BigDecimal.valueOf(stake)) < ZERO) {
            throw new BalanceException(STAKE_HIGHER_THAN_REMAINING_BALANCE_MSG);
        }
        Balance.adjustBalance(BigDecimal.valueOf(stake));
        Dices dices = rollerService.roll();
        BigDecimal winning = calculatorService.calculateWinning(dices, BigDecimal.valueOf(stake));
        return mapper.getGameResult(dices, BigDecimal.valueOf(stake), winning);
    }
}
