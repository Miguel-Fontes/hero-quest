package br.com.miguelmf.heroquest.core.hero;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.miguelmf.heroquest.core.hero.Hero.Builder;

public class HeroTestFactory {

    private static HeroTestFactory factory;
    private Builder heroBuilder;
    private static List<String> names = Arrays.asList(
        "Abrolk Of The Ghastly Outlaws",
        "Altag Of The Screaming Sadists",
        "Olag Of The Heckling Gluttons"
    );

    private HeroTestFactory(Builder heroBuilder) {
        this.heroBuilder = heroBuilder;
    }

    public static HeroTestFactory newInstance() {
        return new HeroTestFactory(newHeroBuilderInstance());
    }

    private static Builder newHeroBuilderInstance() {
        return Hero.builder()
            .name(getRandomName())
            .maxHp(getRandomAttribute(50, 100))
            .strength(getRandomAttribute(6, 20))
            .dexterity(getRandomAttribute(6, 20))
            .intelligence(getRandomAttribute(6, 20))
            .vitality(getRandomAttribute(6, 20))
            .addAction(ActionStub.newInstance())
            .type(HeroType.NPC)
            .selector(SelectorStub.newInstance());
    }

	private static String getRandomName() {
		return names.get(new Random().nextInt(names.size()));
    }

    private static int getRandomAttribute(int lowerBound, int upperBound) {
		return new Random().nextInt(upperBound - 1) + lowerBound;
    }

    public Builder getBuilder() {
        return heroBuilder;
    }

    public Hero build() {
        return heroBuilder.build();
    }

}