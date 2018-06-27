package br.com.miguelmf.heroquest.cli;

import br.com.miguelmf.heroquest.api.HeroQuestApiImpl;
import br.com.miguelmf.event.DomainEventPublisher;
import br.com.miguelmf.heroquest.cli.subscribers.BattleCompletedSubscriber;
import br.com.miguelmf.heroquest.cli.subscribers.BattleTurnComputedSubscriber;
import br.com.miguelmf.heroquest.port.api.ExecuteBattleRequest;
import br.com.miguelmf.heroquest.port.api.HeroDTO;
import br.com.miguelmf.heroquest.port.api.HeroQuestApi;

class Application {

    public static void main(String[] args) {
        final HeroQuestApi api = new HeroQuestApiImpl();

        HeroDTO jack = HeroDTO.of("Jack of Trades", 10, 10, 10, 10, 100, 100);
        HeroDTO lothbrokson = HeroDTO.of("Lothbrokson", 10, 10, 10, 10, 100, 100);

        DomainEventPublisher
            .instance()
            .subscribe(BattleCompletedSubscriber.instance())
            .subscribe(BattleTurnComputedSubscriber.instance());

        api.executeBattle(ExecuteBattleRequest.of(jack, lothbrokson));
    }

}