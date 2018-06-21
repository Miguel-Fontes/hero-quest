package br.com.miguelmf.heroquest;

import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.ActionStub;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;
import br.com.miguelmf.heroquest.core.hero.HeroType;
import br.com.miguelmf.heroquest.core.hero.SelectorStub;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;

@DisplayName("Hero Quest")
class HeroQuestTest {

    @Disabled
    @Test
    @DisplayName("Should execute a complete battle")
    void shouldExecuteACompleteBattle() {
        Hero smoothHero = buildRandomHero();
        Hero brutalHero = buildRandomHero();;

        Battle battle = Battle.of(smoothHero, brutalHero);

        while (!battle.isComplete()) {
            battle = battle.nextTurn();
        }

        Assertions.assertTrue(battle.getWinner().isPresent(), "The battle is done but there is no Winner");
    }

	private Hero buildRandomHero() {
        return HeroTestFactory.newInstance().getBuilder()
            .addAction(BasicAttackAction.newInstance())
            .selector(BasicAttackSelector.newInstance()).build();
	}

}
