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
	public Hero act(Hero hero, Hero target) {
		return target.takeDamage(hero.getStrength() + Dice.instance().d6());
	}

}