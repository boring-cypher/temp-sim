package sims.event;

import sims.npc.NPC;
import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomEventBuilder {
    public static ArrayList<Events> buildRandomEvents() {
        ArrayList<Events> events = new ArrayList<>();

        add(events, "random_study_weird_guy_in_your_group", Phase.STUDY, "Study", "Weird guy in your group",
                "A weird guy asks to join your study group.",
                "neutral", List.of(NPCFactory.grpMate),
                choice("Reject him", 0, Map.of("Socialise", -5), 0),
                branchChoice("Accept him", 1, Map.of("Socialise", 10), 0,
                        List.of(new Branch(Map.of("Knowledge", 8)), new Branch(Map.of("Knowledge", -8))),
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT)
        );

        add(events, "random_cca_party_time", Phase.STUDY, "CCA", "PARTY TIME!!!",
                "Your friends are asking you to go clubbing.",
                "neutral", List.of(NPCFactory.bestFriend),
                npcChoice("LESGO!", 4, Map.of("Socialise", 8, "Knowledge", -8, "Happiness", 2), 80,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("nah, I'm locked in.", 0, Map.of("Socialise", -8, "Knowledge", 8), 0)
        );

        add(events, "random_study_pop_quiz_surprise", Phase.STUDY, "Study", "Surprise GRADED Pop Quiz!",
                "Your professor suddenly announces a graded pop quiz!",
                "neutral", List.of(NPCFactory.grpMate),
                choice("Generative Pre-trained Transformer", 0, Map.of("Happiness", -3), 30),
                branchChoice("Confidence 100", 0, Map.of("Socialise", 3), 0,
                        List.of(new Branch(Map.of("Knowledge", 5, "Happiness", 8)), new Branch(Map.of("Knowledge", -15, "Happiness", -8))),
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT)
        );

        add(events, "random_study_partner", Phase.STUDY, "CCA", "BFF Study Sesh",
                "Your best friend asks to study with you",
                "neutral", List.of(NPCFactory.grpMate),
                npcChoice("Sure!", 1, Map.of("Socialise", 8, "Knowledge", 3, "Happiness", 3), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.BORROW_NOTES),
                npcChoice("Not feeling it today", -2, Map.of("Socialise", -3, "Knowledge", -1), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.VENT)
        );

        add(events, "random_vague_answer", Phase.STUDY, "CCA", "Vague answer",
                "Dr Cao did not fully answer your question...",
                "neutral", List.of(NPCFactory.prof),
                npcChoice("Awkwardly tell him you understood everything", 0, Map.of("Socialise", 3, "Knowledge", -1), 0,
                        List.of(NPCFactory.prof), NPCAction.ASK_FEEDBACK),
                npcChoice("Gotta get to the bottom of this...", 1, Map.of("Socialise", -3, "Knowledge", 10), 0,
                        List.of(NPCFactory.prof), NPCAction.BOOK_CONSULT)
        );

        add(events, "random_game_losing", Phase.STUDY, "CCA", "Can't end on a loss",
                "It's getting late but you're on a losing streak",
                "neutral", List.of(NPCFactory.friends),
                branchChoice("Play until you win", 3, Map.of("Socialise", 10), 0,
                        List.of(new Branch(Map.of("Knowledge", -5)), new Branch(Map.of("Knowledge", -5)),
                                new Branch(Map.of("Knowledge", -5, "Happiness", 10)), new Branch(Map.of("Knowledge", -5, "Happiness", -10))),
                        List.of(NPCFactory.friends), NPCAction.HANG_OUT),
                npcChoice("Just one more game", 1, Map.of("Socialise", 2, "Knowledge", -2), 0,
                        List.of(NPCFactory.prof), NPCAction.HANG_OUT),
                npcChoice("Be disciplined", -2, Map.of("Socialise", 2, "Happiness", -2), 0,
                        List.of(NPCFactory.prof), NPCAction.HANG_OUT)
        );

        add(events, "random_laptop_water", Phase.STUDY, "Study", "Water and electricity do not mix.",
                "You accidentally poured water onto your laptop and now it no longer works",
                "neutral", List.of(NPCFactory.prof),
                choice("Repair at the store", 0, Map.of("Happiness", -15, "Knowledge", 3), 90),
                choice("Use an old and laggy laptop instead", 0, Map.of("Happiness", -80, "Knowledge", -3), 0)
        );

        add(events, "random_study_sussy_baka", Phase.RECESS, "Study", "Sussy baka",
                "A classmate approaches you with a strange request...",
                "neutral", List.of(NPCFactory.grpMate),
                npcChoice("DO IT", 4, Map.of("Socialise", 10, "Happiness", 5), 0,
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("mmm... nah", 0, Map.of("Socialise", -10), 0)
        );

        return events;
    }

    private static void add(List<Events> events, String id, Phase phase, String category,
                            String title, String desc, String mood, List<NPC> npcs, EventChoice... choices) {
        events.add(new Events(id, "random", 0, phase, category, title, desc, mood, npcs,
                new ArrayList<>(List.of(choices)), null));
    }

    private static EventChoice choice(String description, int energy, Map<String, Integer> effects, int money) {
        return new EventChoice(description, energy, effects, money);
    }

    private static EventChoice npcChoice(String description, int energy, Map<String, Integer> effects, int money,
                                         List<NPC> affectedNPCs, NPCAction action) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action);
    }

    private static EventChoice branchChoice(String description, int energy, Map<String, Integer> effects, int money,
                                            List<Branch> branches, List<NPC> affectedNPCs, NPCAction action) {
        return new EventChoice(description, energy, effects, money, branches, affectedNPCs, action);
    }
}
