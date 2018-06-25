package br.com.miguelmf.heroquest.core.hero;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.miguelmf.commons.Stringfy;
import br.com.miguelmf.validator.ValidatedEntity;

class Attributes extends ValidatedEntity {

    @NotNull(message = "Strength should not be null")
    @Min(value = 6, message = "Strength should be at least 6")
    private final int strength;

    @NotNull(message = "Dexterity should not be null")
    @Min(value = 6, message = "Dexterity should be at least 6")
    private final int dexterity;

    @NotNull(message = "Intelligence should not be null")
    @Min(value = 6, message = "Intelligence should be at least 6")
    private final int intelligence;

    @NotNull(message = "Vitality should not be null")
    @Min(value = 6, message = "Vitality should be at least 6")
    private final int vitality;

    private Attributes(int strength, int dexterity, int intelligence, int vitality) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.vitality = vitality;
        validate();
    }

    public static Attributes of(int strength, int dexterity, int intelligence, int vitality) {
        return new Attributes(strength, dexterity, intelligence, vitality);
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

    @Override
    public String toString() {
        return Stringfy.curly(this);
    }

}