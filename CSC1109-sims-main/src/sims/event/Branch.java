package sims.event;

import java.util.HashMap;
import java.util.Map;

public class Branch {

    private final Map<String, Integer> effects;

    public Branch(Map<String, Integer> effects) {
        this.effects = new HashMap<>(effects); // defensive copy
    }

    public Map<String, Integer> effects() {
        return effects;
    }
}
