package br.com.miguelmf.heroquest.port.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.miguelmf.heroquest.core.battle.Battle;

public class BattleDTO {

    private final HeroDTO winner;
    private final List<TurnDTO> turns;

    private BattleDTO(HeroDTO winner, List<TurnDTO> turns) {
        this.winner = winner;
        this.turns = turns;
    }

    public static BattleDTO of(HeroDTO winner, List<TurnDTO> turns) {
        return new BattleDTO(winner, turns);
    }

    public static BattleDTO from(Battle battle) {
        HeroDTO winner = battle.getWinner()
            .map(HeroDTO::from)
            .orElseThrow(() -> new IllegalArgumentException("Trying to create BattleDTO with incomplete Battle (there is no winner!)"));

        return BattleDTO.of(winner,
                battle.getTurns().stream()
                    .map(TurnDTO::from)
                    .collect(Collectors.toList()));
    }

    public HeroDTO getWinner() {
        return winner;
    }

    public List<TurnDTO> getTurns() {
        return Collections.unmodifiableList(turns);
    }

}