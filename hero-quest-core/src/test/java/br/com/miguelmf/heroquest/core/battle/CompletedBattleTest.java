package br.com.miguelmf.heroquest.core.battle;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;

class CompletedBattleTest {

    private static Battle battle;
    private static Hero winner;

    @BeforeAll
    static void setup() {
        winner = HeroTestFactory.newInstance().getBuilder().selector(BasicAttackSelector.instance()).build();
        Hero loser = HeroTestFactory.newInstance().getBuilder()
            .hp(1)
            .build()
            .takeDamage(1);

        battle = Battle.of(loser, winner);
    }

    @Test
    @DisplayName("isComplete should return true when one of the heroes is dead")
    void shouldReturnComplete() {
        assertTrue(battle.isComplete());
    }

    @Test
    @DisplayName("getWinner should return alive hero")
    void winnerShouldReturnAliveHero() {
        assertTrue(battle.getWinner().isPresent());
        battle.getWinner().ifPresent(hero -> {
            assertTrue(hero.isAlive());
            assertTrue(hero.getName().equals(winner.getName()));
        });
    }

}