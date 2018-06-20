package br.com.miguelmf.heroquest.core.actions;

import br.com.miguelmf.heroquest.core.battle.Dice;
import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;

public class BasicAttackAction implements Action {

	private final static String NAME = "Basic Attack";

	private BasicAttackAction() {
	}

	public static BasicAttackAction newInstance() {
		return new BasicAttackAction();
	}

	public String getName() {
		return NAME;
	}

	@Override
	public Hero act(Hero actor, Hero target) {
		return target.takeDamage(actor.getStrength() + Dice.instance().d6());
	}

}