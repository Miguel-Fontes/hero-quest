package br.com.miguelmf.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DomainEventPublisher {

    private static DomainEventPublisher publisher;

    @SuppressWarnings("rawtypes")
    private List<DomainEventSubscriber> subscribers = new ArrayList<>();

    private DomainEventPublisher() {
        super();
    }

    public static DomainEventPublisher instance() {
        return publisher != null ? publisher : cache(newInstance());
    }

    private static DomainEventPublisher cache(DomainEventPublisher instance) {
        publisher = instance;
        return publisher;
    }

    public static DomainEventPublisher newInstance() {
        return new DomainEventPublisher();
    }

    @SuppressWarnings("rawtypes")
    public void subscribe(DomainEventSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public <T> void publish(T event) {
        subscribers
            .stream()
            .filter(isSubscribedTo(event))
            .forEach(s -> s.handleEvent(event));
    }

    private <T> Predicate<? super DomainEventSubscriber> isSubscribedTo(T event) {
        return s -> s.subscribedToType() == (event.getClass());
    }

    public Boolean hasSubscribers() {
        return subscribers.size() > 0;
    }

    public void reset() {
        subscribers = new ArrayList<>();
    }

}