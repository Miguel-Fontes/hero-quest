package br.com.miguelmf.heroquest.core.actions;

import br.com.miguelmf.heroquest.core.battle.Dice;
import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;

public class BasicAttackAction implements Action {

	private static final String NAME = "Basic Attack";
	private static BasicAttackAction action;

	private BasicAttackAction() {
	}

	public static BasicAttackAction instance() {
		return action != null
			? action
			: cache(new BasicAttackAction());
	}

	private static BasicAttackAction cache(BasicAttackAction instance) {
		action = instance;
		return action;
	}

	public static BasicAttackAction newInstance() {
		return new BasicAttackAction();
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Hero act(Hero hero, Hero target) {
		return target.takeDamage(hero.getStrength() + Dice.instance().d6());
	}

}