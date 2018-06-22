package br.com.miguelmf.heroquest.core.battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.validator.ValidatedEntity;

public class Battle extends ValidatedEntity {

    private final List<Combatant> combatants;
    private final List<Turn> turns;

    private Battle(List<Combatant> combatants, List<Turn> turns) {
        this.combatants = combatants;
        this.turns = turns;
        validate();
    }

    public Battle of(List<Combatant> combatants, List<Turn> turns) {
        return new Battle(
            combatants,
            turns
        );
    }

    public static Battle newInstance(List<Combatant> combatants) {
        return new Battle(
            sortCombatantsByInitiativeDescending(combatants),
            Collections.emptyList()
        );
    }

	private static List<Combatant> sortCombatantsByInitiativeDescending(List<Combatant> combatants) {
        List<Combatant> orderedCombatants = new ArrayList<>(combatants);

		orderedCombatants.sort((cmb1, cmb2) -> {
            return cmb1.getInitiative() >= cmb2.getInitiative()
                ? -1
                : 1;
        });

        return Collections.unmodifiableList(orderedCombatants);
	}

    public static Battle of(Hero hero1, Hero hero2) {
        return Stream.of(hero1, hero2)
            .map(Combatant::from)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(), Battle::newInstance));
    }

	public boolean isComplete() {
        Combatant current = combatants.get(0);
        Combatant target = combatants.get(1);

		return !current.isAlive() || !target.isAlive();
	}

	public Battle nextTurn() {
        return isComplete() ? this : computeNextTurn();
    }

	private Battle computeNextTurn() {
        Combatant current = combatants.get(0);
        Action action = current.selectNextAction();

        Combatant target = Combatant.of(
            action.act(current.getHero(), combatants.get(1).getHero()),
            combatants.get(1).getInitiative()
        );

        Turn turn = Turn.of(current, target, action);
        List<Turn> updatedTurns = new ArrayList<>();
        updatedTurns.addAll(turns);
        updatedTurns.add(turn);

        System.out.println(
            String.format(
                "Hero %s attacks oponnent with a %s, leaving him with %s hit points!", current.getName(), action.getName(), target.getHero().getHp()));

        return this.of(Arrays.asList(target, current), turns);
  }

  public Optional<Combatant> getWinner() {
        Combatant current = combatants.get(0);
        Combatant target = combatants.get(1);

        return isComplete()
            ? Optional.of(current.isAlive() ? current : target)
            : Optional.empty();
	}

	public List<Combatant> getCombatants() {
		return Collections.unmodifiableList(combatants);
	}

}