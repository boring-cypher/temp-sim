package sims.event;

import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;

import java.util.HashMap;
import java.util.Map;

public class EffectRunnerTest {

    @Test
    public void testExecuteChoiceAppliesEffectsAndCaps() {
        // Start near the cap so we can verify clamping behavior.
        Character character = new Character("Sam", "Male", 90, 90, 90);

        // Apply mixed positive and negative effects, including money spend.
        Map<String, Integer> effects = new HashMap<>();
        effects.put("Happiness", 15);
        effects.put("Social", -20);
        effects.put("Knowledge", 15);
        effects.put("Money", -40);

        EventChoice choice = new EventChoice("Test", 1, effects, 0);
        EffectRunner runner = new EffectRunner();
        runner.executeChoice(choice, character);

        // Happiness and knowledge should cap at 100, social should decrease, money should deduct.
        Assert.assertEquals(100, character.getHappiness());
        Assert.assertEquals(70, character.getSocial());
        Assert.assertEquals(100, character.getKnowledge());
        Assert.assertEquals(60.0, character.getBalance(), 0.0001);
    }

    @Test
    public void testExecuteChoiceMoneyCannotGoBelowZero() {
        // Spending more than balance should clamp to zero.
        Character character = new Character("Sam", "Male", 50, 50, 50);

        Map<String, Integer> effects = new HashMap<>();
        effects.put("Money", -200);

        EventChoice choice = new EventChoice("Test", 1, effects, 0);
        EffectRunner runner = new EffectRunner();
        runner.executeChoice(choice, character);

        // Balance should not drop below zero.
        Assert.assertEquals(0.0, character.getBalance(), 0.0001);
    }
}
