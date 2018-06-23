package br.com.miguelmf.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DomainEventPublisher Publish")
class DomainEventPublisherPublishTest {

    private static DomainEventPublisher publisher;

    @BeforeAll
    static void setup() {
        publisher = DomainEventPublisher.instance();
    }

    @BeforeEach
    void reset() {
        DomainEventPublisher.instance().reset();
        MessageDomainEventSubscriber.resetMessagesCounter();
    }

    @Test
    @DisplayName("should publish a event")
    void shouldPublishAEvent() {
        final String message = "Hello World!";

        publisher
            .subscribe(MessageDomainEventSubscriber.instance(event -> {
                assertEquals(event, message);
            }));
        DomainEventPublisher.instance().publish(message);

        assertTrue(MessageDomainEventSubscriber.hasReceivedMessages(), "No message was received!");
    }

    @Test
    @DisplayName("should publish events only to correct subscribers")
    void shouldPublishEventsOnlyToTheCorrectSubscribers() {
        final Long aNumber = 10L;
        publisher
            .subscribe(MessageDomainEventSubscriber.instance(event -> {
                fail("A Number was published to a Subscriber of Strings");
            }));

        DomainEventPublisher.instance().publish(aNumber);

        assertFalse(MessageDomainEventSubscriber.hasReceivedMessages(), "A Number was published to a Subscriber of Strings");
    }

}