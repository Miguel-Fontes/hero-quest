package br.com.miguelmf.heroquest.core.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.miguelmf.heroquest.core.hero.Hero;
import br.com.miguelmf.heroquest.core.hero.HeroTestFactory;

@DisplayName("BattleCompleteEvent")
class BattleCompleteEventTest {

    private static BattleCompleteEvent event;
    private static Hero hero;

    @BeforeAll
    static void setup() {
        hero = HeroTestFactory.newInstance().buildHero();
        event = BattleCompleteEvent.of(hero);
    }

    @Test
    @DisplayName("should build a instance")
    void shouldBuildAInstance() {
        assertEquals(hero, event.getWinner());
    }

}
