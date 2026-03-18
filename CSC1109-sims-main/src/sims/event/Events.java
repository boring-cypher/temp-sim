package sims.event;

import sims.npc.NPC;

import java.util.ArrayList;
import java.util.List;

/**
 * @param actionCategory Category of event, e.g., "neutral"
 * @param title Event title
 * @param desc Event description
 * @param phase Phase of the game, e.g., STUDY / RECESS / EXAM
 * @param npcs NPCs involved in the event
 * @param choices List of choices for this event
 */
public record Events(
        String id,
        String source,
        int turn,
        Phase phase,
        String actionCategory,
        String title,
        String desc,
        String mood,
        List<NPC> npcs,
        ArrayList<EventChoice> choices,
        StoryState.ProjectRoute requiredProjectRoute
) {
    public Events(
            String id,
            String source,
            int turn,
            Phase phase,
            String actionCategory,
            String title,
            String desc,
            String mood,
            List<NPC> npcs,
            ArrayList<EventChoice> choices
    ) {
        this(id, source, turn, phase, actionCategory, title, desc, mood, npcs, choices, null);
    }
}
