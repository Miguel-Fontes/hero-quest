package br.com.miguelmf.heroquest.core.battle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;

class BattleConstructionTest {

    private static List<Combatant> combatants;
    private static Combatant combatant;

    @BeforeAll
    static void setup() {
        combatant = Combatant.from(HeroTestFactory.newInstance().buildHero());
        combatants = new ArrayList<>();
    }

    @Test
    @DisplayName("should throw if built with more than two combatants")
    void shouldThrowForMoreThanTwoCombatants() {
        combatants = Arrays.asList(combatant, combatant, combatant, combatant);

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            Battle.newInstance(combatants);
        });

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("maximum of 2 combatants"));
    }

}