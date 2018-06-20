package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hero Attributes")
class HeroAttributesTest {

    @Test
    @DisplayName("should throw error when strengh is < 6")
    void shouldThrowErrorForStrengthBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            HeroTestFactory.instance()
                .getBuilder()
                .strength(5)
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when dexterity is < 6")
    void shouldThrowErrorForDexterityBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            HeroTestFactory.instance()
                .getBuilder()
                .dexterity(5)
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when intelligence is < 6")
    void shouldThrowErrorForIntelligenceBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            HeroTestFactory.instance()
                .getBuilder()
                .intelligence(5)
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when vitality is < 6")
    void shouldThrowErrorForVitalityBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            HeroTestFactory.instance()
                .getBuilder()
                .vitality(5)
                .build();
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }
}