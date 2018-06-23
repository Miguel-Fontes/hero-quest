package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Attributes")
class AttributesTest {

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

    @Test
    @DisplayName("should throw error when strength is < 6")
    void shouldThrowErrorForStrenghtBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Attributes.of(5, 7, 8, 9);
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when dexterity is < 6")
    void shouldThrowErrorForDexterityBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Attributes.of(6, 5, 8, 9);
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when intelligence is < 6")
    void shouldThrowErrorForIntelligenceBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Attributes.of(6, 7, 5, 9);
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

    @Test
    @DisplayName("should throw error when vitality is < 6")
    void shouldThrowErrorForVitalityBelowMinimum() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Attributes.of(6, 7, 8, 5);
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("at least 6"));
    }

}