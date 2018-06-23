package br.com.miguelmf.event;

class StubDomainEventSubscriber implements DomainEventSubscriber<String> {

    private StubDomainEventSubscriber() {
    }

    public static StubDomainEventSubscriber instance() {
        return new StubDomainEventSubscriber();
    }

    @Override
    public void handleEvent(String event) {

    }

    @Override
    public Class<String> subscribedToType() {
        return String.class;
    }

}