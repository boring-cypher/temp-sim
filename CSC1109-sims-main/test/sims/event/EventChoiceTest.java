package sims.event;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EventChoiceTest {

    @Test
    public void testEffectsCopied() {
        // Build effects map and pass it into the EventChoice.
        Map<String, Integer> effects = new HashMap<>();
        effects.put("Happiness", 5);

        EventChoice choice = new EventChoice("Test", 1, effects, 0);
        // Mutate the original map to ensure EventChoice kept a copy.
        effects.put("Happiness", 999);

        // The stored effects should remain unchanged.
        Assert.assertEquals(5, (int) choice.getEffects().get("Happiness"));
    }
}
