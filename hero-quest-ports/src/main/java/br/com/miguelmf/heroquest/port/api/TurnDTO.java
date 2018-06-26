package br.com.miguelmf.heroquest.port.api;

import br.com.miguelmf.heroquest.core.battle.Turn;

public class TurnDTO {

    public static TurnDTO from(Turn turn) {
        return new TurnDTO();
    }

}