package br.com.miguelmf.heroquest.cli;

import br.com.miguelmf.event.DomainEventPublisher;
import br.com.miguelmf.heroquest.cli.subscribers.BattleCompletedSubscriber;
import br.com.miguelmf.heroquest.cli.subscribers.BattleTurnComputedSubscriber;
import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroType;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;

class Application {

    public static void main(String[] args) throws InterruptedException {
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

        DomainEventPublisher
            .instance()
            .subscribe(BattleCompletedSubscriber.instance())
            .subscribe(BattleTurnComputedSubscriber.instance());

        Battle battle = Battle.of(jack, lothbrokson);

        while(!battle.isComplete()) {
            Thread.sleep(1000);
            battle = battle.nextTurn();
        }
    }

}