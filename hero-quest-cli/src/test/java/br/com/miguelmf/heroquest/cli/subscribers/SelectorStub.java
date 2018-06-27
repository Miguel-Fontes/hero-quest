package br.com.miguelmf.heroquest.cli.subscribers;

import br.com.miguelmf.heroquest.core.hero.Action;
import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.Selector;

public class SelectorStub implements Selector {

	public int selectCalls;

	private SelectorStub() {
		this.selectCalls = 0;
	}

	public static SelectorStub newInstance() {
		return new SelectorStub();
	}

	@Override
	public Action select(Hero hero) {
		selectCalls++;
		return ActionStub.newInstance();
	}

}