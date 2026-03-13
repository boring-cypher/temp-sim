package sims.event;

import sims.npc.NPC;

import java.util.List;

/**
 * @param actionCategory Category of event, e.g., "neutral"
 * @param title    Event title
 * @param desc     Event description
 * @param phase    Phase of the game, e.g., STUDY / RECESS / EXAM
 * @param npcs     NPCs involved in the event
 * @param choices  List of choices for this event
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
        List<EventChoice> choices
) {}