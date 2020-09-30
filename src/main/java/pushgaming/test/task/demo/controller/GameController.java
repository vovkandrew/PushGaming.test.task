package pushgaming.test.task.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pushgaming.test.task.demo.model.GameResult;
import pushgaming.test.task.demo.service.GameService;

@RestController
@RequestMapping("/snakeeyes")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @RequestMapping("/play")
    public GameResult play(@RequestParam String stake) {
        return gameService.play(Double.parseDouble(stake));
    }
}

