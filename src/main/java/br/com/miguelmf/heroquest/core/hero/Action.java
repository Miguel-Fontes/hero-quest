package br.com.miguelmf.heroquest.core.hero;

public interface Action {
    public Hero act(Hero hero, Hero target);

	public String getName();
}