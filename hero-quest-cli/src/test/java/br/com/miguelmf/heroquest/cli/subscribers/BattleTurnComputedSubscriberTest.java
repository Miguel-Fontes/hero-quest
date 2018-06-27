package br.com.miguelmf.heroquest.cli.subscribers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.events.BattleTurnComputedEvent;

@DisplayName("BattleTurnComputed")
class BattleTurnComputedTest {

    private static BattleTurnComputedSubscriber subscriber;
    private static BattleTurnComputedEvent event;

    @BeforeAll
    static void setup() {
        subscriber = BattleTurnComputedSubscriber.instance();
        event = BattleTurnComputedEvent.of("Turn computed!");
    }

    @Test
    @DisplayName("should return the type subscribed to")
    void shouldReturnTypeSubscribedTo() {
        assertEquals(BattleTurnComputedEvent.class, subscriber.subscribedToType());
    }

    @Test
    @DisplayName("should handle event")
    void shouldHandleEvent() {
        subscriber.handleEvent(event);
    }

}