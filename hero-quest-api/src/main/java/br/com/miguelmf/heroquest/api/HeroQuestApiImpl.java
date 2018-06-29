package br.com.miguelmf.heroquest.api;

import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.battle.Battle;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroType;
import br.com.miguelmf.heroquest.core.selectors.BasicAttackSelector;
import br.com.miguelmf.heroquest.port.api.BattleDTO;
import br.com.miguelmf.heroquest.port.api.ExecuteBattleRequest;
import br.com.miguelmf.heroquest.port.api.HeroDTO;
import br.com.miguelmf.heroquest.port.api.HeroQuestApi;

public class HeroQuestApiImpl implements HeroQuestApi {

	@Override
	public BattleDTO executeBattle(ExecuteBattleRequest request) {
		Hero jack = toHero(request.getHero());
        Hero lothbrokson = toHero(request.getOpponent());

		Battle battle = Battle.of(jack, lothbrokson);

		return BattleDTO.from(battle.evaluate());
	}

	private Hero toHero(HeroDTO hero) {
		return new Hero.Builder()
            .name(hero.getName())
            .maxHp(hero.getMaxHp())
            .strength(hero.getStrength())
            .dexterity(hero.getDexterity())
            .intelligence(hero.getIntelligence())
            .vitality(hero.getVitality())
            .addAction(BasicAttackAction.instance())
            .type(HeroType.NPC)
            .selector(BasicAttackSelector.instance())
			.build();
	}

}