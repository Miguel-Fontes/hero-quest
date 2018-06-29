package br.com.miguelmf.heroquest.cli.subscribers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.events.BattleCompleteEvent;

@DisplayName("BattleCompletedSubscriber")
class BattleCompletedSubscriberTest {

    private static BattleCompletedSubscriber subscriber;
    private static BattleCompleteEvent event;
    private static String name;

    @BeforeAll
    static void setup() {
        name = "Hero name!";
        subscriber = BattleCompletedSubscriber.instance();
        event = BattleCompleteEvent.of(name);

    }

    @Test
    @DisplayName("should return the type subscribed to")
    void shouldReturnTypeSubscribedTo() {
        assertEquals(BattleCompleteEvent.class, subscriber.subscribedToType());
    }

    @Test
    @DisplayName("should handle event")
    void shouldHandleEvent() {
        subscriber.handleEvent(event);
    }

}