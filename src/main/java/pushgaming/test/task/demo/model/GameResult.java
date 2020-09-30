package pushgaming.test.task.demo.model;

import java.math.BigDecimal;

public class GameResult {
    private int diceOne;
    private int diceTwo;
    private BigDecimal stake;
    private BigDecimal winning;
    private BigDecimal currentBalance;

    public GameResult() {
    }

    public GameResult(int diceOne, int diceTwo, BigDecimal stake, BigDecimal winning, BigDecimal currentBalance) {
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
        this.stake = stake;
        this.winning = winning;
        this.currentBalance = currentBalance;
    }

    public int getDiceOne() {
        return diceOne;
    }

    public void setDiceOne(int diceOne) {
        this.diceOne = diceOne;
    }

    public int getDiceTwo() {
        return diceTwo;
    }

    public void setDiceTwo(int diceTwo) {
        this.diceTwo = diceTwo;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public BigDecimal getWinning() {
        return winning;
    }

    public void setWinning(BigDecimal winning) {
        this.winning = winning;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameResult that = (GameResult) o;

        if (diceOne != that.diceOne) return false;
        if (diceTwo != that.diceTwo) return false;
        if (stake != null ? !stake.equals(that.stake) : that.stake != null) return false;
        if (winning != null ? !winning.equals(that.winning) : that.winning != null) return false;
        return currentBalance != null ?
                currentBalance.equals(that.currentBalance) :
                that.currentBalance == null;
    }

    @Override
    public int hashCode() {
        int result = diceOne;
        result = 31 * result + diceTwo;
        result = 31 * result + (stake != null ? stake.hashCode() : 0);
        result = 31 * result + (winning != null ? winning.hashCode() : 0);
        result = 31 * result + (currentBalance != null ? currentBalance.hashCode() : 0);
        return result;
    }
}
