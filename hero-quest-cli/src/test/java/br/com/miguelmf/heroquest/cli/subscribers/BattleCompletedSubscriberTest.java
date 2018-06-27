package br.com.miguelmf.heroquest.cli.subscribers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.events.BattleCompleteEvent;

@DisplayName("BattleCompletedSubscriber")
class BattleCompletedSubscriberTest {

    private static BattleCompletedSubscriber subscriber;
    private static BattleCompleteEvent event;

    @BeforeAll
    static void setup() {
        subscriber = BattleCompletedSubscriber.instance();
        event = BattleCompleteEvent.of(HeroTestFactory.newInstance().buildHero());

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