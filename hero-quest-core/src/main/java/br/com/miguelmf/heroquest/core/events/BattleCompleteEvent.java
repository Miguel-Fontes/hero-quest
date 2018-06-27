package br.com.miguelmf.heroquest.core.events;

import br.com.miguelmf.heroquest.core.hero.Hero;

public class BattleCompleteEvent {

    private final Hero winner;

    private BattleCompleteEvent(Hero winner) {
        super();
        this.winner = winner;
    }

    public static BattleCompleteEvent of(Hero winner) {
        return new BattleCompleteEvent(winner);
    }

    public Hero getWinner() {
        return winner;
    }

}