package br.com.miguelmf.heroquest.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattleTurnComputedEventTest {

    private static BattleTurnComputedEvent event;
    private static String message;

    @BeforeAll
    static void setup() {
        message = "Turn Computed!";
        event = BattleTurnComputedEvent.of(message);
    }

    @Test
    @DisplayName("should build a instance")
    void shouldBuildAInstance() {
        assertEquals(message, event.getMessage());
    }

}