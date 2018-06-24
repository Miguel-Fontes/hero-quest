package br.com.miguelmf.heroquest.core.battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import br.com.miguelmf.event.DomainEventPublisher;
import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.events.BattleCompleteEvent;
import br.com.miguelmf.validator.ValidatedEntity;

public class Battle extends ValidatedEntity {

    @NotNull
    private final Combatant current;

    @NotNull
    private final Combatant opponent;

    @NotNull
    private final List<Turn> turns;


    private Battle(Combatant current, Combatant opponent, List<Turn> turns) {
        this.current = current;
        this.opponent = opponent;
        this.turns = turns;
        validate();
    }

    public Battle of(Combatant current, Combatant opponent, List<Turn> turns) {
        return new Battle(current, opponent, turns);
    }

    public static Battle newInstance(List<Combatant> combatants) {
        guardMaximumOfTwoCombatants(combatants);
        return newInstance(combatants.get(0), combatants.get(1));
    }

	private static void guardMaximumOfTwoCombatants(List<Combatant> combatants) {
		if (combatants.size() > 2)
            throw new IllegalArgumentException("Battle supports a maximum of 2 combatants, but was given " + combatants.size());
	}

    public static Battle newInstance(Combatant one, Combatant two) {
        Combatant first = one.getInitiative() >= two.getInitiative() ? one : two;
        Combatant second = two.getInitiative() < one.getInitiative() ? two : one;

        return new Battle(first, second, Collections.emptyList());
    }

    public static Battle of(Hero hero1, Hero hero2) {
        return Stream.of(hero1, hero2)
            .map(Combatant::from)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Battle::newInstance));
    }

    public boolean isComplete() {
        return !current.isAlive() || !opponent.isAlive();
    }

    public Battle nextTurn() {
        return isComplete() ? this : computeNextTurn();
    }

	private void publishBattleCompleteEvent(Battle battle) {
		DomainEventPublisher
            .instance()
            .publish(BattleCompleteEvent.of(
                battle.getWinner()
                    .orElseThrow(IllegalStateException::new)));
    }

	private Battle computeNextTurn() {
        Action action = current.selectNextAction();
        Turn turn = Turn.of(current, opponent, action);
        updateTurns(turn);
        Combatant nextHeroToAct = actOnOpponent(action);
        Battle battle = this.of(nextHeroToAct, current, turns);


        publishTurnComputedEvent(action, nextHeroToAct);

        if (battle.isComplete())
            publishBattleCompleteEvent(battle);

        return battle;
    }

	private Combatant actOnOpponent(Action action) {
		Combatant nextHeroToAct = Combatant.of(
            action.act(
                current.getHero(),
                opponent.getHero()),
                opponent.getInitiative());

		return nextHeroToAct;
	}

	private void updateTurns(Turn turn) {
		List<Turn> updatedTurns = new ArrayList<>();
        updatedTurns.addAll(turns);
        updatedTurns.add(turn);
	}

	private void publishTurnComputedEvent(Action action, Combatant nextHeroToAct) {
		DomainEventPublisher.instance()
            .publish(String.format("Hero %s attacks oponnent with a %s, leaving him with %s hit points!",
                current.getName(), action.getName(), nextHeroToAct.getHero().getHp()));
	}

    public Optional<Hero> getWinner() {
        return isComplete()
            ? Optional.of(getSurvivor())
            : Optional.empty();
    }

	private Hero getSurvivor() {
		return current.isAlive() ? current.getHero() : opponent.getHero();
	}

    public List<Combatant> getCombatants() {
        return Arrays.asList(current, opponent);
    }

}