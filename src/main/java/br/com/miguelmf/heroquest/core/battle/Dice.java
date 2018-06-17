package br.com.miguelmf.heroquest.core.battle;

import java.util.Random;

public class Dice {

    private Random random;

    private Dice() {
        random = new Random();
    }

    public static Dice instance() {
        return new Dice();
    }

    public int d20() {
        return random.nextInt(19) + 1;
    }

}