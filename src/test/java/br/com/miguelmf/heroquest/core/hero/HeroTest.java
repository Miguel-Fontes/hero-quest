package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hero")
class HeroTest {

    @Test
    @DisplayName("should build a valid hero")
    void shouldBuildAValidHero() {
        Hero hero = Hero.builder()
            .name("Herod")
            .maxHp(100)
            .strength(16)
            .dexterity(12)
            .intelligence(10)
            .vitality(14)
            .actions(Collections.singleton(new ActionStub()))
            .type(HeroType.NPC)
            .selector(new SelectorStub())
            .build();

        assertNotNull(hero);
    }

    @Test
    @DisplayName("a hero above 0 hp is alive")
    void shouldBeAliveIfAboveZeroHp() {
        Hero hero = Hero.builder()
            .name("Herod")
            .maxHp(100)
            .strength(16)
            .dexterity(12)
            .intelligence(10)
            .vitality(14)
            .actions(Collections.singleton(new ActionStub()))
            .type(HeroType.NPC)
            .selector(new SelectorStub())
            .build();

        assertEquals(100, hero.getHp());
        assertTrue(hero.isAlive());
    }

}
