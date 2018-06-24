package br.com.miguelmf.event;

import java.util.function.Consumer;

class MessageDomainEventSubscriber implements DomainEventSubscriber<String> {

    private final Consumer<String> consumer;
    private static int messagesReceived = 0;

    private MessageDomainEventSubscriber(Consumer<String> consumer) {
        super();
        this.consumer = consumer;
    }

    public static MessageDomainEventSubscriber newInstance(Consumer<String> consumer) {
        return new MessageDomainEventSubscriber(consumer);
    }

    @Override
    public void handleEvent(String event) {
        consumer.accept(event);
        messagesReceived++;
    }

    @Override
    public Class<String> subscribedToType() {
        return String.class;
    }

    public static boolean hasReceivedMessages() {
        return messagesReceived > 0;
    }

    public static void resetMessagesCounter() {
        messagesReceived = 0;
    }

}