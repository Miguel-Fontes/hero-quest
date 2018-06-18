package br.com.miguelmf.heroquest.core.hero;

import java.util.Collection;

public class Hero {

    private final String name;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int vitality;
    private final int hp;
    private final int maxHp;
    private final HeroType type;
    private final Collection<Action> actions;
    private final Selector selector;

    private Hero(String name, int hp, int maxHp, int strength, int intelligence, int dexterity, int vitality,
            Collection<Action> actions, HeroType type, Selector selector) {
        this.name = validateName(name);
        this.strength = strength;
        this.dexterity = validateDexterity(dexterity);
        this.intelligence = intelligence;
        this.vitality = vitality;
        this.hp = hp;
        this.maxHp = maxHp;
        this.type = type;
        this.actions = actions;
        this.selector = selector;
    }

    public static Hero of(String name, int hp, int maxHp, int strength, int intelligence, int dexterity, int vitality,
            Collection<Action> actions, HeroType type, Selector selector) {
        return new Hero(name, hp, maxHp, strength, intelligence, dexterity, vitality, actions, type, selector);
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getDexterity() {
        return dexterity;
    }

    private String validateName(String name) {
        if (name.equals("") || name == null) {
            throw new IllegalArgumentException("A Hero's name should be blank or null!");
        }

        return name;
    }

    private int validateDexterity(int dexterity) {
        if (dexterity == 0) {
            throw new IllegalArgumentException("A Hero's dexterity should be greater than zero");
        }

        return dexterity;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{\n" + "name: " + name + "}";
    }

    public static class Builder {

        private String name;
        private int strength;
        private int dexterity;
        private int intelligence;
        private int vitality;
        private int hp;
        private int maxHp;
        private Collection<Action> actions;
        private HeroType type;
        private Selector selector;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder hp(int hp) {
            this.hp = hp;
            return this;
        }

        public Builder maxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public Builder strength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder dexterity(int dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Builder vitality(int vitality) {
            this.vitality = vitality;
            return this;
        }

        public Builder actions(Collection<Action> actions) {
            this.actions = actions;
            return this;
        }

        public Builder type(HeroType type) {
            this.type = type;
            return this;
        }

        public Builder selector(Selector selector) {
            this.selector = selector;
            return this;
        }

        public Hero build() {
            return Hero.of(name, hp, maxHp, strength, intelligence, dexterity, vitality, actions,
                    type, selector);
        }

    }

}
