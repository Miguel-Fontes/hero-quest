package br.com.miguelmf.heroquest.core.hero;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.MultipleFailuresError;

import br.com.miguelmf.heroquest.core.hero.Hero.Builder;

@DisplayName("Hero Builder")
class HeroBuilderTest {

    private static Builder builder = Hero.builder();

    private static final int strength = 6;
    private static final int dexterity = 6;
    private static final int intelligence = 6;
    private static final int vitality = 6;
    private static final String name = "a name";
    private static final Attributes attributes = Attributes.of(strength, dexterity, intelligence, vitality);
    private static final int hp = 100;
    private static final int maxHp = 100;
    private static final Action action = ActionStub.newInstance();
    private static final List<Action> actions = Collections.singletonList(action);
    private static final Selector selector = SelectorStub.newInstance();
    private static final HeroType type = HeroType.NPC;


    @Test
    @DisplayName("should build a valid hero")
    void shouldBuildAValidHero() {
        Hero hero = builder.name(name)
            .hp(hp)
            .maxHp(maxHp)
            .strength(strength)
            .dexterity(dexterity)
            .intelligence(intelligence)
            .vitality(vitality)
            .actions(actions)
            .selector(selector)
            .type(type)
            .build();

        assertValidHero(hero);
    }

	private void assertValidHero(Hero hero) throws MultipleFailuresError {
		assertAll(
            () -> assertEquals(strength, hero.getStrength()),
            () -> assertEquals(dexterity, hero.getDexterity()),
            () -> assertEquals(intelligence, hero.getIntelligence()),
            () -> assertEquals(vitality, hero.getVitality()),
            () -> assertEquals(hp, hero.getHp()),
            () -> assertTrue(actions.containsAll(hero.getKnownActions()))
        );
	}

    @Test
    @DisplayName("should use maxHp as Hp if Hp was not set")
    void shouldUseMaxHpIfHpWasNotSet() {
        Hero hero = builder.name(name)
            .maxHp(maxHp)
            .strength(strength)
            .dexterity(dexterity)
            .intelligence(intelligence)
            .vitality(vitality)
            .actions(actions)
            .selector(selector)
            .type(type)
            .build();

        assertEquals(maxHp, hero.getHp());
    }

    @Test
    @DisplayName("should set attributes with a Attributes ojbect")
    void shouldBuildWithAAttributesObject() {
        Hero hero = builder.name(name)
            .maxHp(maxHp)
            .attributes(attributes)
            .actions(actions)
            .selector(selector)
            .type(type)
            .build();

        assertEquals(maxHp, hero.getHp());
    }

}