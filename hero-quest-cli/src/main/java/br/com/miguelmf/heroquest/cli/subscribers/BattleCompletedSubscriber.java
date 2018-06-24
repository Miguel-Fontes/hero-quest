package br.com.miguelmf.heroquest.cli.subscribers;

import br.com.miguelmf.event.DomainEventSubscriber;
import br.com.miguelmf.heroquest.core.events.BattleCompleteEvent;

public class BattleCompletedSubscriber implements DomainEventSubscriber<BattleCompleteEvent> {

    private BattleCompletedSubscriber() {
        super();
    }

    public static BattleCompletedSubscriber instance() {
        return new BattleCompletedSubscriber();
    }

	@Override
	public void handleEvent(BattleCompleteEvent event) {
        System.out.println("The winner is " + event.getWinner().getName() + "!");
	}

	@Override
	public Class<BattleCompleteEvent> subscribedToType() {
		return BattleCompleteEvent.class;
	}

}