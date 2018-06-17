package br.com.miguelmf.heroquest.core.hero;

public class Hero {

    private final int dexterity;

    private Hero(int dexterity) {
        this.dexterity = dexterity;
    }

    public static Hero of(int dexterity) {
        return new Hero(dexterity);
    }

    public int getDexterity() {
        return dexterity;
    }

}