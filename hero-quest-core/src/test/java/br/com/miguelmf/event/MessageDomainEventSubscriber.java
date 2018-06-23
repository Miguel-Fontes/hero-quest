package br.com.miguelmf.event;

import java.util.function.Consumer;

class MessageDomainEventSubscriber implements DomainEventSubscriber<String> {

    private final Consumer<String> consumer;
    private static MessageDomainEventSubscriber subscriber;
    private static int messagesReceived = 0;

    private MessageDomainEventSubscriber(Consumer<String> consumer) {
        super();
        this.consumer = consumer;
    }

    public static MessageDomainEventSubscriber instance(Consumer<String> consumer) {
        return subscriber != null ? subscriber : cache(newInstance(consumer));
    }

    private static MessageDomainEventSubscriber cache(MessageDomainEventSubscriber instance) {
        subscriber = instance;
        return subscriber;
    }

    private static MessageDomainEventSubscriber newInstance(Consumer<String> consumer) {
        return new MessageDomainEventSubscriber(consumer);
    }

    @Override
    public void handleEvent(String event) {
        consumer.accept(event);
        messagesReceived++;
    }

    @Override
    public Class subscribedToType() {
        return String.class;
    }

    public static boolean hasReceivedMessages() {
        return messagesReceived > 0;
    }

    public static void resetMessagesCounter() {
        messagesReceived = 0;
    }

}