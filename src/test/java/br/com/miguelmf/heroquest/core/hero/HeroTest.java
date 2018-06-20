package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hero")
class HeroTest {

    public static Hero hero;

    @BeforeAll
    static void setup() {
        hero = Hero.builder()
            .name("Herod")
            .maxHp(100)
            .strength(16)
            .dexterity(12)
            .intelligence(10)
            .vitality(14)
            .actions(Collections
            .singleton(new ActionStub()))
            .type(HeroType.NPC)
            .selector(new SelectorStub())
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
        assertEquals(1, hero.getActions().size());
    }

    @Test
    @DisplayName("a hero actions list should not be modified directly")
    void aHeroActionListShouldNotBeModifiedDirectly() {
        Collection<Action> actions = hero.getActions();

        Throwable throwable =assertThrows(UnsupportedOperationException.class, () -> {
            actions.add(new ActionStub());
        });

        assertNotNull(throwable);
    }

}
