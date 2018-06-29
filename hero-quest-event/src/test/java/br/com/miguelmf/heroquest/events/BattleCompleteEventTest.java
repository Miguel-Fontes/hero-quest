package br.com.miguelmf.heroquest.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BattleCompleteEvent")
class BattleCompleteEventTest {

    private static BattleCompleteEvent event;
    private static String name;

    @BeforeAll
    static void setup() {
        name = "Hero name!";
        event = BattleCompleteEvent.of(name);
    }

    @Test
    @DisplayName("should build a instance")
    void shouldBuildAInstance() {
        assertEquals(name, event.getWinner());
    }

}
