package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hero")
class HeroTest {

    public static Hero hero;
    private static SelectorStub SELECTOR = SelectorStub.newInstance();
    private static ActionStub ACTION = ActionStub.newInstance();

    @BeforeAll
    static void setup() {
        hero = HeroTestFactory.newInstance()
            .getBuilder()
            .maxHp(100)
            .actions(Arrays.asList(ACTION))
            .selector(SELECTOR)
            .build();
    }

    @Test
    @DisplayName("should build a valid hero")
    void shouldBuildAValidHero() {
        assertNotNull(hero);
    }

    @Test
    @DisplayName("a hero above 0 hp is alive")
    void shouldBeAliveIfAboveZeroHp() {
        assertEquals(100, hero.getHp());
        assertTrue(hero.isAlive());
    }

    @Test
    @DisplayName("a hero below 1 hp is dead")
    void shouldBeDeadIfAboveZeroHp() {
        Hero heroWithZeroHp = hero.takeDamage(100);
        Hero heroBelowZeroHp = hero.takeDamage(101);

        assertHeroIsDead(heroWithZeroHp);
        assertHeroIsDead(heroBelowZeroHp);
    }

	private void assertHeroIsDead(Hero damagedHero) {
        assertFalse(damagedHero.isAlive());
	}

    @Test
    @DisplayName("a hero should report it's actions")
    void shouldReturnActions() {
        assertEquals(1, hero.getKnownActions().size());
    }

    @Test
    @DisplayName("a hero actions list should not be modified directly")
    void aHeroActionListShouldNotBeModifiedDirectly() {
        Collection<Action> actions = hero.getKnownActions();

        Throwable throwable = assertThrows(UnsupportedOperationException.class, () -> {
            actions.add(ActionStub.newInstance());
        });

        assertNotNull(throwable);
    }

    @Test
    @DisplayName("should call a selector for choosing the next action")
    void shouldUseSelectorForChoosingNextAction() {
        hero.selectNextAction();

        assertTrue(SELECTOR.selectCalls == 1);
    }

}
