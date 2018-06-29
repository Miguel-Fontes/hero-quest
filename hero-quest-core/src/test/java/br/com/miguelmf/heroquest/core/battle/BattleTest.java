package br.com.miguelmf.heroquest.core.battle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;

@DisplayName("Battle")
class BattleTest {

    private static Battle battle;

    @BeforeAll
    static void setup() {
        battle = Battle.of(
            HeroTestFactory.newInstance().getBuilder().selector(BasicAttackSelector.instance()).build(),
            HeroTestFactory.newInstance().getBuilder().selector(BasicAttackSelector.instance()).build()
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

    @Test
    @DisplayName("should return no Winner if battle isn't over")
    void shouldReturnNoWinnerForIncompleteBattle() {
        assertFalse(battle.isComplete());
        assertFalse(battle.getWinner().isPresent());
    }

    @Test
    @DisplayName("should compute a turn")
    void shouldComputeATurn() {
        Battle nextTurnBattle = battle.nextTurn();

        Combatant combatantOnFirstTurn = battle.getCombatants().get(1);
        Combatant combatantOnSecondTurn = nextTurnBattle.getCombatants().get(0);
        assertEquals(combatantOnFirstTurn.getName(), combatantOnSecondTurn.getName());
        assertNotEquals(combatantOnFirstTurn.getHp(), combatantOnSecondTurn.getHp());
    }

    @Test
    @DisplayName("isComplete should return false when both heros are alive")
    void shouldReturnNotComplete() {
        assertFalse(battle.isComplete());
    }

}