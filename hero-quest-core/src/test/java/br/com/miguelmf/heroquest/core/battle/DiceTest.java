package br.com.miguelmf.heroquest.core.battle;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class DiceTest {

    @RepeatedTest(30)
    @DisplayName("should generate d20 values")
    void shouldGenerateD20Values() {
        int minValue = 1;
        int maxValue = 20;
        int roll = Dice.instance().d20();

        assertTrue(roll >= minValue);
        assertTrue(roll <= maxValue);
    }

    @RepeatedTest(30)
    @DisplayName("should generate d6 values")
    void shouldGenerateD6Values() {
        int minValue = 1;
        int maxValue = 6;
        int roll = Dice.instance().d6();

        assertTrue(roll >= minValue);
        assertTrue(roll <= maxValue);
    }

}