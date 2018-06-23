package br.com.miguelmf.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DomainEventPublisher Subscribe")
class DomainEventPublisherSubscribeTest {

    private static DomainEventPublisher publisher;

    @BeforeAll
    static void setup() {
        publisher = DomainEventPublisher.instance();
    }

    @BeforeEach
    void subscribe() {
        publisher.subscribe(StubDomainEventSubscriber.instance());
    }

    @Test
    @DisplayName("should register a new subscriber")
    void shouldRegisterANewSubscriber() {
        assertTrue(publisher.hasSubscribers());
    }

    @Test
    @DisplayName("should clear subscribers")
    void shouldClearSubscribers() {
        DomainEventPublisher.instance().reset();
        assertFalse(publisher.hasSubscribers(), "DomainEventPublisher still has publishers!");
    }

}