package pushgaming.test.task.demo.model;

public class Dices {
    private final int diceOne;
    private final int diceTwo;

    public Dices(int diceOne, int diceTwo) {
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
    }

    public int getDiceOne() {
        return diceOne;
    }

    public int getDiceTwo() {
        return diceTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dices dices = (Dices) o;

        if (diceOne != dices.diceOne) return false;
        return diceTwo == dices.diceTwo;
    }

    @Override
    public int hashCode() {
        int result = diceOne;
        result = 31 * result + diceTwo;
        return result;
    }
}
