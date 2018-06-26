package br.com.miguelmf.heroquest.port.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.miguelmf.heroquest.core.battle.Battle;

class BattleDTO {

    private final HeroDTO hero;
    private final HeroDTO opponent;
    private final List<TurnDTO> turns;

    private BattleDTO(HeroDTO hero, HeroDTO opponent, List<TurnDTO> turns) {
        this.hero = hero;
        this.opponent = opponent;
        this.turns = turns;
    }

    public static BattleDTO of(HeroDTO hero, HeroDTO opponent, List<TurnDTO> turns) {
        return new BattleDTO(hero, opponent, turns);
    }

    public static BattleDTO from(Battle battle) {
        List<HeroDTO> heroes = battle.getCombatants().stream()
                .map(c -> c.getHero())
                .map(HeroDTO::from)
                .collect(Collectors.toList());

        return BattleDTO.of(heroes.get(0),
                heroes.get(1),
                battle.getTurns().stream().map(TurnDTO::from).collect(Collectors.toList()));
    }

    public HeroDTO getHero() {
        return hero;
    }

    public HeroDTO getOpponent() {
        return opponent;
    }

    public List<TurnDTO> getTurns() {
        return Collections.unmodifiableList(turns);
    }

}