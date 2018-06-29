package br.com.miguelmf.heroquest.core.actions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;

@DisplayName("Basic Attack")
class BasicAttackActionTest {

    @RepeatedTest(10)
    @DisplayName("a BasicAttack should damage it's target")
    void shouldHitWithABasicAttack() {
        BasicAttackAction basicAttack = BasicAttackAction.instance();
        Hero hero = getHeroWith100Hp();
        Hero target = getHeroWith100Hp();

        Hero damagedTarget = basicAttack.act(hero, target);

        assertTrue(target.getHp() > damagedTarget.getHp());
    }

	private Hero getHeroWith100Hp() {
		return HeroTestFactory.newInstance().buildHero();
	}

}