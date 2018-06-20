package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
            .intelligence(10)
            .dexterity(12)
            .vitality(14)
            .actions(Collections.singleton(new ActionStub()))
            .type(HeroType.NPC)
            .selector(new SelectorStub())
            .build();

        assertNotNull(hero);
    }

    @Test
    @DisplayName("should build a valid attributes entity")
    void shouldTestAttributes() {
        Attributes attributes = Attributes.of(6, 7, 8, 9);

        assertNotNull(attributes);
        assertEquals(6, attributes.getStrength());
        assertEquals(7, attributes.getDexterity());
        assertEquals(8, attributes.getIntelligence());
        assertEquals(9, attributes.getVitality());
    }

}
