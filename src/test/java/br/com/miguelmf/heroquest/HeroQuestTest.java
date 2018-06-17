package br.com.miguelmf.heroquest;

import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.Hero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class HeroQuestTest {

    @Test
    @DisplayName("Should execute a complete battle")
    void shouldExecuteACompleteBattle() {
        Hero hero1 = Hero.of(2);
        Hero hero2 = Hero.of(3);
        Battle battle = Battle.of(hero1, hero2);

        while (!battle.isComplete()) {
            battle = battle.nextTurn();
        }

        Assertions.assertTrue(battle.getWinner().isPresent(),
            "The battle is done but there is no Winner");
    }

}
