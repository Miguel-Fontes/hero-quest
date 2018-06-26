package br.com.miguelmf.heroquest.port.api;

import br.com.miguelmf.commons.Stringfy;
import br.com.miguelmf.heroquest.core.hero.Hero;

public class HeroDTO {

    private final String name;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int vitality;
    private final int hp;
    private final int maxHp;

    private HeroDTO(String name, int strength, int dexterity, int intelligence, int vitality, int hp,
            int maxHp) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.vitality = vitality;
        this.hp = hp;
        this.maxHp = maxHp;
    }

    public static HeroDTO of(String name, int strength, int dexterity, int intelligence, int vitality, int hp,
            int maxHp) {
        return new HeroDTO(name, strength, dexterity, intelligence, vitality, hp, maxHp);
    }

    public static HeroDTO from(Hero hero) {
        return HeroDTO.of(hero.getName(),
        hero.getStrength(),
        hero.getDexterity(),
        hero.getIntelligence(),
        hero.getDexterity(),
        hero.getHp(),
        hero.getMaxHp());
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getVitality() {
        return vitality;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public String toString() {
        return Stringfy.curly(this);
    }

}