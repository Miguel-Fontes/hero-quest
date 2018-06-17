package br.com.miguelmf.heroquest.core.battle;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.miguelmf.heroquest.core.hero.Hero;

public class Battle {

    private final Collection<Combatant> combatants;
    private final boolean complete;

    private Battle(Collection<Combatant> combatants, boolean complete) {
        this.combatants = combatants;
        this.complete = complete;
    }

    public static Battle of(List<Combatant> combatants) {
        return new Battle(combatants, false);
    }

    public static Battle of(Hero hero1, Hero hero2) {
        return Stream.of(hero1, hero2)
            .map(Combatant::from)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(), Battle::of));
    }

	public boolean isComplete() {
		return complete;
	}

	public Battle nextTurn() {
        return complete();
    }

    private Battle complete() {
        return new Battle(combatants, true);
    }

	public Optional<Combatant> getWinner() {
		return Optional.empty();
	}

}