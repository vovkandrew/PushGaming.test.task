package pushgaming.test.task.demo;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.MockMvc;
import pushgaming.test.task.demo.exception.BalanceException;
import pushgaming.test.task.demo.exception.StakeException;
import pushgaming.test.task.demo.model.GameResult;
import pushgaming.test.task.demo.service.GameService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest
public class GameControllerTest {
    private static final String TEST_REQUEST_PARAM = "10";
    private static final String TEST_REQUEST_PARAM_WRONG = "5";
    private static final double TEST_STAKE = 10;
    private static final double TEST_STAKE_WRONG = 5;
    private static final BigDecimal TEST_WINNING = BigDecimal.valueOf(300);
    private static final int TEST_DICE_ONE_NUMBER = 1;
    private static final int TEST_DICE_TWO_NUMBER = 1;
    private static final GameResult TEST_GAME_RESULT =
            new GameResult(TEST_DICE_ONE_NUMBER, TEST_DICE_TWO_NUMBER, BigDecimal.valueOf(10),
                    TEST_WINNING, Balance.getCurrentMoneyBalance());
    private static final int DICE_ONE_TO_CHECK = 1;
    private static final int DICE_TWO_TO_CHECK = 1;
    private static final int STAKE_TO_CHECK = 10;
    private static final int WINNING_TO_CHECK = 300;
    private static final int BALANCE_TO_CHECK = 1000;
    private static final String WRONG_STAKE_ERROR_MESSAGE = "Unsupported stake, please consider choosing a stake of " +
            "1.00, 2.00 or 10.00 only to play Snake eyes game";
    private static final String ZERO_BALANCE_ERROR_MESSAGE = "Your personal balance is completely run out of money, "
            + "please consider top up your balance";
    private static final String NOT_ENOUGH_BALANCE_ERROR_MESSAGE = "You don't have enough money on your personal" +
            " balance to play with this stake, please choose lesser stake or top up your balance";
    private static final String TEST_URL = "/snakeeyes/play";
    private static final String TEST_PARAM_NAME = "stake";
    private static final String DICE_ONE_EXPR = "$.diceOne";
    private static final String DICE_TWO_EXPR = "$.diceTwo";
    private static final String STAKE_EXPR = "$.stake";
    private static final String WINNING_EXPR = "$.winning";
    private static final String BALANCE_EXPR = "$.currentBalance";
    private static final String MESSAGE_EXPR = "$.message";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void testGameControllerGeneral() throws Exception {
        Balance.resetCurrentBalance();
        when(gameService.play(TEST_STAKE)).thenReturn(TEST_GAME_RESULT);
        mockMvc.perform(get(TEST_URL).param(TEST_PARAM_NAME, TEST_REQUEST_PARAM))
                .andExpect(status().isOk())
                .andExpect(jsonPath(DICE_ONE_EXPR, Matchers.equalTo(DICE_ONE_TO_CHECK)))
                .andExpect(jsonPath(DICE_TWO_EXPR, Matchers.equalTo(DICE_TWO_TO_CHECK)))
                .andExpect(jsonPath(STAKE_EXPR, Matchers.equalTo(STAKE_TO_CHECK)))
                .andExpect(jsonPath(WINNING_EXPR, Matchers.equalTo(WINNING_TO_CHECK)))
                .andExpect(jsonPath(BALANCE_EXPR, Matchers.equalTo(BALANCE_TO_CHECK)));
        verify(gameService).play(TEST_STAKE);
    }

    @Test
    public void testGameControllerWithWrongStake() throws Exception {
        when(gameService.play(TEST_STAKE_WRONG)).thenThrow(new StakeException(WRONG_STAKE_ERROR_MESSAGE));
        mockMvc.perform(get(TEST_URL).param(TEST_PARAM_NAME, TEST_REQUEST_PARAM_WRONG))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(MESSAGE_EXPR, Matchers.equalTo(WRONG_STAKE_ERROR_MESSAGE)));
        verify(gameService).play(TEST_STAKE_WRONG);
    }

    @Test
    public void testGameControllerWithZeroBalance() throws Exception {
        when(gameService.play(TEST_STAKE)).thenThrow(new BalanceException(ZERO_BALANCE_ERROR_MESSAGE));
        mockMvc.perform(get(TEST_URL).param(TEST_PARAM_NAME, TEST_REQUEST_PARAM))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath(MESSAGE_EXPR, Matchers.equalTo(ZERO_BALANCE_ERROR_MESSAGE)));
        verify(gameService).play(TEST_STAKE);

    }

    @Test
    public void testGameControllerWithStakeThatHigherThanRemainingBalance() throws Exception {
        when(gameService.play(TEST_STAKE)).thenThrow(new BalanceException(NOT_ENOUGH_BALANCE_ERROR_MESSAGE));
        mockMvc.perform(get(TEST_URL).param(TEST_PARAM_NAME, TEST_REQUEST_PARAM))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath(MESSAGE_EXPR, Matchers.equalTo(NOT_ENOUGH_BALANCE_ERROR_MESSAGE)));
        verify(gameService).play(TEST_STAKE);
    }
}
