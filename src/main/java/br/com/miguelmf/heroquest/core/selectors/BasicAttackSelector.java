package br.com.miguelmf.heroquest.core.selectors;

import br.com.miguelmf.heroquest.core.actions.BasicAttackAction;
import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.Selector;

public class BasicAttackSelector implements Selector {

    private BasicAttackSelector() {
    }

    public static BasicAttackSelector newInstance() {
        return new BasicAttackSelector();
    }

    @Override
    public Action select(Hero hero) {
        return BasicAttackAction.newInstance();
    }

}