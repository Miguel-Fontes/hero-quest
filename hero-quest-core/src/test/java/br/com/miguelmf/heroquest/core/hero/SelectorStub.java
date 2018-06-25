package br.com.miguelmf.heroquest.core.hero;

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