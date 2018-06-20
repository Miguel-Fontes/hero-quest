package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("should throw error when strengh is < 6")
    void shouldThrowErrorForStrengthBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Hero.builder()
                .name("Herod")
                .maxHp(100)
                .strength(5)
                .dexterity(12)
                .intelligence(10)
                .vitality(12)
                .actions(Collections.singleton(new ActionStub()))
                .type(HeroType.NPC)
                .selector(new SelectorStub())
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when dexterity is < 6")
    void shouldThrowErrorForDexterityBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Hero.builder()
                .name("Herod")
                .maxHp(100)
                .strength(16)
                .dexterity(5)
                .intelligence(10)
                .vitality(6)
                .actions(Collections.singleton(new ActionStub()))
                .type(HeroType.NPC)
                .selector(new SelectorStub())
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when intelligence is < 6")
    void shouldThrowErrorForIntelligenceBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Hero.builder()
                .name("Herod")
                .maxHp(100)
                .strength(16)
                .dexterity(12)
                .intelligence(5)
                .vitality(10)
                .actions(Collections.singleton(new ActionStub()))
                .type(HeroType.NPC)
                .selector(new SelectorStub())
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when vitality is < 6")
    void shouldThrowErrorForVitalityBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Hero.builder()
                .name("Herod")
                .maxHp(100)
                .strength(16)
                .intelligence(10)
                .dexterity(12)
                .vitality(5)
                .actions(Collections.singleton(new ActionStub()))
                .type(HeroType.NPC)
                .selector(new SelectorStub())
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }


}
