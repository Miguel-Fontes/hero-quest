package br.com.miguelmf.heroquest.core.hero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.miguelmf.commons.Stringfy;
import br.com.miguelmf.validator.ValidatedEntity;

public class Hero extends ValidatedEntity {

    @NotBlank
    @Length(min = 3, message = "Name should be at least 3 characters long")
    private final String name;

    @NotNull
    private final Attributes attributes;

    @NotNull(message = "HP should not be null")
    private final int hp;

    @NotNull(message = "MaxHP should not be null")
    @Min(value = 12, message = "HP should be at least 12")
    private final int maxHp;

    @NotNull(message = "Actions should not be null")
    @Size(min = 1, message = "Actions length should be at least 1")
    private final Collection<Action> actions;

    @NotNull(message = "Selector should not be null")
    private final Selector selector;

    @NotNull(message = "Type should not be null")
    private final HeroType type;

    private Hero(String name, int hp, int maxHp, Attributes attributes, Collection<Action> actions, Selector selector,
            HeroType type) {
        this.name = name;
        this.attributes = attributes;
        this.hp = hp;
        this.maxHp = maxHp;
        this.actions = actions;
        this.selector = selector;
        this.type = type;
        validate();
    }

    public static Hero of(String name, int hp, int maxHp, Attributes attributes, Collection<Action> actions,
            Selector selector, HeroType type) {
        return new Hero(name, hp, maxHp, attributes, actions, selector, type);
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public Collection<Action> getKnownActions() {
		return Collections.unmodifiableCollection(actions);
	}

    public Hero takeDamage(int damage) {
		return Hero.of(name, hp - damage, maxHp, attributes, actions, selector, type);
    }

    public Action selectNextAction() {
        return selector.select(this);
    }

    public String getName() {
        return name;
      }

    public int getStrength() {
        return attributes.getStrength();
    }

    public int getDexterity() {
        return attributes.getDexterity();
    }

    public int getIntelligence() {
        return attributes.getIntelligence();
    }

    public int getVitality() {
        return attributes.getVitality();
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

    public static class Builder {

        private String name;
        private int strength;
        private int dexterity;
        private int intelligence;
        private int vitality;
        private int hp;
        private int maxHp;
        private Collection<Action> actions;
        private Selector selector;
        private HeroType type;

        public Builder () {
            actions = new ArrayList<>();
        }

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

        public Builder dexterity(int dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Builder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder vitality(int vitality) {
            this.vitality = vitality;
            return this;
        }

        public Builder attributes(Attributes attributes) {
            this.strength = attributes.getStrength();
            this.dexterity = attributes.getDexterity();
            this.intelligence = attributes.getIntelligence();
            this.vitality = attributes.getVitality();

            return this;
        }

        public Builder actions(Collection<Action> actions) {
            this.actions = actions;
            return this;
        }

        public Builder addAction(Action action) {
            this.actions.add(action);
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
            return Hero.of(name, getMaxHpIfHpWasNotSet(), maxHp, getAttributes(), actions, selector, type);
        }

        private Attributes getAttributes() {
            return Attributes.of(strength, dexterity, intelligence, vitality);
        }

        private int getMaxHpIfHpWasNotSet() {
            return hp == 0 ? maxHp : hp;
        }

    }

}
