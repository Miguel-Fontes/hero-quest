package br.com.miguelmf.heroquest.core.selectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;

@DisplayName("Basic Attack Selector")
class BasicAttackSelectorTest {

    private static BasicAttackSelector selector = BasicAttackSelector.instance();

    @RepeatedTest(10)
    @DisplayName("should always select a basic attack")
    void shouldSelectABasicAttack() {
        Hero hero = HeroTestFactory.newInstance().buildHero();

        Action action = selector.select(hero);

        assertEquals("Basic Attack", action.getName());
    }

}