package br.com.miguelmf.heroquest.events;

public class BattleCompleteEvent {

    private final String winner;

    private BattleCompleteEvent(String winner) {
        super();
        this.winner = winner;
    }

    public static BattleCompleteEvent of(String winner) {
        return new BattleCompleteEvent(winner);
    }

    public String getWinner() {
        return winner;
    }

}