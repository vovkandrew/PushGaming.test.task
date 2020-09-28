package pushgaming.test.task.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pushgaming.test.task.demo.client.CustomHttpClient;
import pushgaming.test.task.demo.model.Dices;
import pushgaming.test.task.demo.service.DiceRollerService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiceRollerTest {
    private static final String PATH_FOR_TEST = "https://www.random.org/integers/" +
                                    "?num=2&min=1&max=6&col=2&base=10&format=plain";
    private static final int DICE_NUMBER_ONE = 2;
    private static final int DICE_NUMBER_TWO = 2;
    private static final String BODY_TEST = "2 2 ";
    private static final String BODY_TEST_EMPTY = "";
    private static final String BODY_TEST_SPACES = "    ";
    private static final String BODY_TEST_NO_DIGITS = "a b ";

    @Mock
    private CustomHttpClient client;

    private DiceRollerService diceRollerService;

    @Before
    public void init() {
        diceRollerService = new DiceRollerService(client, PATH_FOR_TEST);
    }

    @Test
    public void testDiceRollerServiceWithCorrectPathAndBody() {
        Dices expected = new Dices(DICE_NUMBER_ONE, DICE_NUMBER_TWO);
        when(client.getResponseBodyFromClient(PATH_FOR_TEST)).thenReturn(BODY_TEST);
        Dices actual = diceRollerService.roll();
        Assert.assertEquals(expected, actual);
        verify(client).getResponseBodyFromClient(anyString());
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testDiceRollerServiceWithEmptyStringReceivedFromBody() {
        when(client.getResponseBodyFromClient(anyString())).thenReturn(BODY_TEST_EMPTY);
        Dices dices = diceRollerService.roll();
        verify(client).getResponseBodyFromClient(anyString());
    }

    @Test(expected = NumberFormatException.class)
    public void testDiceRollerServiceWithStringFullOfSpaces() {
        when(client.getResponseBodyFromClient(anyString())).thenReturn(BODY_TEST_SPACES);
        Dices dices = diceRollerService.roll();
        verify(client).getResponseBodyFromClient(anyString());
    }

    @Test(expected = NumberFormatException.class)
    public void testDiceRollerServiceWithStringWithoutDigits() {
        when(client.getResponseBodyFromClient(anyString())).thenReturn(BODY_TEST_NO_DIGITS);
        Dices dices = diceRollerService.roll();
        verify(client).getResponseBodyFromClient(anyString());
    }
}
