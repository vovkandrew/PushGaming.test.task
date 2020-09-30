package pushgaming.test.task.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pushgaming.test.task.demo.client.CustomHttpClient;
import pushgaming.test.task.demo.model.Dices;

@Service
public class DiceRollerService {
    private String path;
    private final static int FIRST_DICE_NUMBER_POS = 0;
    private final static int SECOND_DICE_NUMBER_POS = 2;
    private final CustomHttpClient client;

    @Autowired
    public DiceRollerService(CustomHttpClient client, @Value("${path.to.randomizer}") String path) {
        this.client = client;
        this.path = path;
    }

    public Dices roll() {
        String results = client.getResponseBodyFromClient(path);
        int firstNumber = Integer.parseInt(String.valueOf(results.charAt(FIRST_DICE_NUMBER_POS)));
        int secondNumber = Integer.parseInt(String.valueOf(results.charAt(SECOND_DICE_NUMBER_POS)));
        return new Dices(firstNumber, secondNumber);
    }
}
