package br.com.miguelmf.heroquest.core.hero;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.miguelmf.heroquest.core.hero.Hero.Builder;

class HeroTestFactory {

    private static HeroTestFactory factory;
    private Builder heroBuilder;
    private static List<String> names = Arrays.asList(
        "Abrolk Of The Ghastly Outlaws",
        "Altag Of The Screaming Sadists",
        "Olag Of The Heckling Gluttons"
    );

    public static HeroTestFactory instance() {
        factory = factory != null ? factory : new HeroTestFactory();
        return factory;
    }

    private HeroTestFactory() {
        heroBuilder = Hero.builder()
            .name(getRandomName())
            .maxHp(100)
            .strength(16)
            .dexterity(12)
            .intelligence(10)
            .vitality(12)
            .addAction(ActionStub.newInstance())
            .type(HeroType.NPC)
            .selector(SelectorStub.newInstance());
    }

	private String getRandomName() {
		return names.get(new Random().nextInt(names.size()));
    }

    public Builder getBuilder() {
        return heroBuilder;
    }

    public Hero build() {
        return heroBuilder.build();
    }

}