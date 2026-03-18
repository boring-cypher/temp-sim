package sims.event;

import sims.npc.NPC;
import sims.npc.NPCAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventChoice {
    private final String description;
    private final int energyCost;
    private final Map<String, Integer> effects;
    private final int moneyCost;
    private final List<Branch> branches;
    private final List<NPC> affectedNPCs;
    private final NPCAction action;
    private final StoryState.ProjectRoute setProjectRoute;

    public EventChoice(String description, int energyCost, Map<String, Integer> effects, int moneyCost) {
        this(description, energyCost, effects, moneyCost, List.of(), List.of(), null, null);
    }

    public EventChoice(
            String description,
            int energyCost,
            Map<String, Integer> effects,
            int moneyCost,
            List<Branch> branches,
            List<NPC> affectedNPCs,
            NPCAction action
    ) {
        this(description, energyCost, effects, moneyCost, branches, affectedNPCs, action, null);
    }

    public EventChoice(
            String description,
            int energyCost,
            Map<String, Integer> effects,
            int moneyCost,
            List<Branch> branches,
            List<NPC> affectedNPCs,
            NPCAction action,
            StoryState.ProjectRoute setProjectRoute
    ) {
        this.description = description;
        this.energyCost = energyCost;
        this.effects = (effects == null) ? new HashMap<>() : new HashMap<>(effects);
        this.moneyCost = moneyCost;
        this.branches = (branches == null) ? List.of() : branches;
        this.affectedNPCs = (affectedNPCs == null) ? List.of() : affectedNPCs;
        this.action = action;
        this.setProjectRoute = setProjectRoute;
    }

    public String getDescription() {
        return description;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public Map<String, Integer> getEffects() {
        return effects;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public List<NPC> getAffectedNPCs() {
        return affectedNPCs;
    }

    public NPCAction getAction() {
        return action;
    }

    public StoryState.ProjectRoute getSetProjectRoute() {
        return setProjectRoute;
    }

    public boolean hasNPCInteraction() {
        return action != null && !affectedNPCs.isEmpty();
    }
}
