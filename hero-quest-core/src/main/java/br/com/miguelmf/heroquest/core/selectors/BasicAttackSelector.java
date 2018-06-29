package br.com.miguelmf.heroquest.core.selectors;

import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.Selector;

public class BasicAttackSelector implements Selector {

    private static BasicAttackSelector selector;

    private BasicAttackSelector() {
    }

    public static BasicAttackSelector instance() {
        return selector != null
            ? selector
            : cache(newInstance());
    }

    private static BasicAttackSelector cache(BasicAttackSelector instance) {
        selector = instance;

        return selector;
	}

	private static BasicAttackSelector newInstance() {
        return new BasicAttackSelector();
    }

    @Override
    public Action select(Hero hero) {
        return BasicAttackAction.instance();
    }

}