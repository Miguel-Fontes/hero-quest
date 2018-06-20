package br.com.miguelmf.heroquest;

import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.ActionStub;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroType;
import br.com.miguelmf.heroquest.core.hero.SelectorStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;

class HeroQuestTest {

    @Test
    @DisplayName("Should execute a complete battle")
    void shouldExecuteACompleteBattle() {
        Hero smoothHero =  Hero.builder()
            .name("Herod")
            .maxHp(20)
            .strength(10)
            .intelligence(12)
            .dexterity(18)
            .vitality(10)
            .actions(Collections.singleton(ActionStub.newInstance()))
            .type(HeroType.NPC)
            .selector(SelectorStub.newInstance())
            .build();

        Hero brutalHero =  Hero.builder()
            .name("Herod")
            .maxHp(28)
            .strength(20)
            .intelligence(8)
            .dexterity(10)
            .vitality(14)
            .actions(Collections.singleton(ActionStub.newInstance()))
            .type(HeroType.NPC)
            .selector(SelectorStub.newInstance())
            .build();

        Battle battle = Battle.of(smoothHero, brutalHero);

        while (!battle.isComplete()) {
            battle = battle.nextTurn();
        }

        Assertions.assertTrue(battle.getWinner().isPresent(),
            "The battle is done but there is no Winner");
    }

}
