package br.com.miguelmf.heroquest.core.hero;

public class ActionStub implements Action {

	public int actCalls;

	private ActionStub() {
		this.actCalls = 0;
	}

	public static ActionStub newInstance() {
		return new ActionStub();
	}

	@Override
	public Hero act(Hero actor, Hero target) {
        actCalls++;
		return target;
	}

	@Override
	public String getName() {
		return "Action Stub";
	}

}
