package br.com.miguelmf.heroquest.core.battle;

import javax.validation.constraints.NotNull;

import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.validator.ValidatedEntity;

class Turn extends ValidatedEntity {

    @NotNull
    private final Combatant hero;

    @NotNull
    private final Combatant oponnent;

    @NotNull
    private final Action action;

    private Turn(Combatant hero, Combatant oponnent, Action action) {
        this.hero = hero;
        this.oponnent = oponnent;
        this.action = action;
        validate();
    }

	public static Turn of(Combatant hero, Combatant oponnent, Action action) {
        return new Turn(hero, oponnent, action);
    }

}