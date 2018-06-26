package br.com.miguelmf.heroquest.port.api;

public class ExecuteBattleRequest {

    private final HeroDTO hero;
    private final HeroDTO opponent;

    private ExecuteBattleRequest(HeroDTO hero, HeroDTO opponent) {
        this.hero = hero;
        this.opponent = opponent;
    }

    public static ExecuteBattleRequest of(HeroDTO hero, HeroDTO opponent) {
        return new ExecuteBattleRequest(hero, opponent);
    }

    public HeroDTO getHero() {
        return hero;
    }

    public HeroDTO getOpponent() {
        return opponent;
    }

}