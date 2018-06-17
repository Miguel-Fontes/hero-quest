package br.com.miguelmf.heroquest.core.battle;

import br.com.miguelmf.heroquest.core.hero.Hero;

class Combatant {

    private final Hero hero;
    private final int initiative;

    private Combatant(Hero hero) {
        this.hero = hero;
        this.initiative = rollInitiative(hero);
    }

    public static Combatant from(Hero hero) {
        return new Combatant(hero);
    }

    private int rollInitiative(Hero hero) {
        return hero.getDexterity() + Dice.instance().d20();
    }

}