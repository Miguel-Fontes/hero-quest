package br.com.miguelmf.heroquest.core.hero;

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



}
