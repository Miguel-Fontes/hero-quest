package br.com.miguelmf.heroquest.cli.subscribers;

import br.com.miguelmf.event.DomainEventSubscriber;

public class BattleTurnComputedSubscriber implements DomainEventSubscriber<String> {

    private BattleTurnComputedSubscriber() {
        super();
    }

    public static BattleTurnComputedSubscriber instance() {
        return new BattleTurnComputedSubscriber();
    }

    @Override
    public void handleEvent(String event) {
        System.out.println(event);
    }

    @Override
    public Class<String> subscribedToType() {
        return String.class;
    }

}