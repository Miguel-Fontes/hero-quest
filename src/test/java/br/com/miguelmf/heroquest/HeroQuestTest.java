package br.com.miguelmf.heroquest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;

@DisplayName("Hero Quest")
class HeroQuestTest {

    @Disabled
    @Test
    @DisplayName("Should execute a complete battle")
    void shouldExecuteACompleteBattle() {
        Hero smoothHero = buildRandomHero();
        Hero brutalHero = buildRandomHero();;

        Battle battle = Battle.of(smoothHero, brutalHero);

        int maxIterations = 100;
        while (!battle.isComplete() && maxIterations > 0) {
            battle = battle.nextTurn();
            maxIterations--;
        }

        Assertions.assertTrue(battle.getWinner().isPresent(), "The battle is done but there is no Winner");
    }

	private Hero buildRandomHero() {
        return HeroTestFactory.newInstance().getBuilder()
            .addAction(BasicAttackAction.newInstance())
            .selector(BasicAttackSelector.newInstance())
            .build();
	}

}
