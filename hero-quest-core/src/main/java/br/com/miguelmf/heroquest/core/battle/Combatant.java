package br.com.miguelmf.heroquest.core.battle;

import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;

public class Combatant {

    private final Hero hero;
    private final int initiative;

    private Combatant(Hero hero, int initiative) {
        this.hero = hero;
        this.initiative = initiative;
    }

    public static Combatant from(Hero hero) {
        return new Combatant(hero, rollInitiative(hero));
    }

    public static Combatant of(Hero hero, int initiative) {
        return new Combatant(hero, initiative);
    }

    private static int rollInitiative(Hero hero) {
        return hero.getDexterity() + Dice.instance().d20();
    }

    public Action selectNextAction() {
        return hero.selectNextAction();
    }

    public Hero getHero() {
        return hero;
    }

    public int getInitiative() {
        return initiative;
    }

    public boolean isAlive() {
        return hero.isAlive();
    }

    public String getName() {
        return hero.getName();
    }

    public int getHp() {
        return hero.getHp();
    }

}