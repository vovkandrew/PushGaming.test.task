package pushgaming.test.task.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import pushgaming.test.task.demo.client.CustomHttpClient;

public class HttpClientTest {
    private final String pathOk = "https://www.random.org/integers/?num=2&min=1&max=6&col=2&base=10&format=plain";
    private final String pathWrong = "https://www.random.org/integers/?num=2&min=1&max=6&col=2&base=10&format=";
    private final CustomHttpClient client = new CustomHttpClient();
    private final String responseBody = client.getResponseBodyFromClient(pathOk);
    private final static int DEFAULT_RESPONSE_BODY_LENGTH = 4;
    private final static int FIRST_DICE_NUMBER_POS = 0;
    private final static int SECOND_DICE_NUMBER_POS = 2;

    @Test
    public void testIfResponseBodyIsEmpty() {
        Assert.assertFalse(responseBody.isEmpty());
    }

    @Test
    public void testIfResponseBodyHasSpecifiedLength() {
        int actual = responseBody.length();
        Assert.assertEquals(DEFAULT_RESPONSE_BODY_LENGTH, actual);
    }

    @Test
    public void testIfFirstCharacterIsDigit() {
        Assert.assertTrue(Character.isDigit(responseBody.charAt(FIRST_DICE_NUMBER_POS)));
    }

    @Test
    public void testIfSecondCharacterIsDigit() {
        Assert.assertTrue(Character.isDigit(responseBody.charAt(SECOND_DICE_NUMBER_POS)));
    }

    @Test(expected = HttpClientErrorException.class)
    public void test() {
        client.getResponseBodyFromClient(pathWrong);
    }
}
