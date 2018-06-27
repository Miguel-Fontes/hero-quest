package br.com.miguelmf.heroquest.cli.subscribers;

import br.com.miguelmf.event.DomainEventSubscriber;
import br.com.miguelmf.heroquest.core.events.BattleTurnComputedEvent;

public class BattleTurnComputedSubscriber implements DomainEventSubscriber<BattleTurnComputedEvent> {

    private BattleTurnComputedSubscriber() {
        super();
    }

    public static BattleTurnComputedSubscriber instance() {
        return new BattleTurnComputedSubscriber();
    }

    @Override
    public void handleEvent(BattleTurnComputedEvent event) {
        System.out.println(event.getMessage());
    }

    @Override
    public Class<BattleTurnComputedEvent> subscribedToType() {
        return BattleTurnComputedEvent.class;
    }

}