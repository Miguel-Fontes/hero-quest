package br.com.miguelmf.heroquest.core.battle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;

@DisplayName("Battle")
class BattleTest {

    private static Battle battle;

    @BeforeAll
    static void setup() {
        battle = Battle.of(
            HeroTestFactory.newInstance().buildHero(),
            HeroTestFactory.newInstance().buildHero()
        );
    }

    @RepeatedTest(10)
    @DisplayName("should order combatants by initiative descending")
    void shouldOrderCombatantsByInitiativeDescending() {
        List<Combatant> combatants = battle.getCombatants();
        int initiative1 = combatants.get(0).getInitiative();
        int initiative2 = combatants.get(1).getInitiative();

        assertTrue(initiative1 >= initiative2);
    }

    @Test
    @DisplayName("combatants list should be immutable")
    void combatantsListShouldBeImmutable() {
        List<Combatant> combatants = battle.getCombatants();

        Throwable throwable = assertThrows(UnsupportedOperationException.class, () -> {
            combatants.add(null);
        });

        assertNotNull(throwable);
    }


}