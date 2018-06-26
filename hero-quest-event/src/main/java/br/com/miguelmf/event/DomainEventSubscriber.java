package br.com.miguelmf.event;

public interface DomainEventSubscriber<T> {

    void handleEvent(T event);

    Class<T> subscribedToType();

}