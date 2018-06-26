package br.com.miguelmf.heroquest.port.api;

public class CreateHeroRequest {

    private final String name;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int vitality;
    private final int maxHp;

    private CreateHeroRequest(String name, int strength, int dexterity, int intelligence, int vitality, int maxHp) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.vitality = vitality;
        this.maxHp = maxHp;
    }

    public static CreateHeroRequest of(String name, int strength, int dexterity, int intelligence, int vitality,
            int maxHp) {
        return new CreateHeroRequest(name, strength, dexterity, intelligence, vitality, maxHp);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return the dexterity
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return the vitality
     */
    public int getVitality() {
        return vitality;
    }

    /**
     * @return the maxHp
     */
    public int getMaxHp() {
        return maxHp;
    }

}