package br.com.miguelmf.heroquest.cli;

import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroType;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;

class Application {

    public static void main(String[] args) {
        Hero jack = new Hero.Builder()
            .name("Jack of Trades")
            .maxHp(100)
            .strength(16)
            .dexterity(10)
            .intelligence(10)
            .vitality(10)
            .addAction(BasicAttackAction.newInstance())
            .type(HeroType.NPC)
            .selector(BasicAttackSelector.newInstance())
            .build();

        Hero lothbrokson = new Hero.Builder()
            .name("Lothbrokson")
            .maxHp(100)
            .strength(16)
            .dexterity(10)
            .intelligence(10)
            .vitality(10)
            .addAction(BasicAttackAction.newInstance())
            .type(HeroType.NPC)
            .selector(BasicAttackSelector.newInstance())
            .build();

        Battle battle = Battle.of(jack, lothbrokson);

        while (!battle.isComplete()) {
            battle = battle.nextTurn();
        }

        battle.getWinner().ifPresent(h -> System.out.println("The Winner is: " + h.toString()));

    }

}