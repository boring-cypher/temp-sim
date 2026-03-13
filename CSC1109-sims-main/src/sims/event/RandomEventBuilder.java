package sims.event;

import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomEventBuilder {
    public static List<Events> buildRandomEvents() {
        List<Events> events = new ArrayList<>();

        events.add(new Events(
                "random_study_weird_guy_in_your_group",
                "random",
                0,
                Phase.STUDY,
                "Study",
                "Weird guy in your group",
                "A weird guy asks to join your study group.",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice("Reject him", 0, Map.of("Socialise", -5), 0),
                        new EventChoice(
                                "Accept him",
                                1,
                                Map.of("Socialise", 10),
                                0,
                                List.of(
                                        new Branch(Map.of("Knowledge", 8)),
                                        new Branch(Map.of("Knowledge", -8))
                                ),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        )
                )
        ));

        events.add(new Events(
                "random_cca_party_time",
                "random",
                0,
                Phase.STUDY,
                "CCA",
                "PARTY TIME!!!",
                "Your friends are asking you to go clubbing.",
                "neutral",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "LESGO!",
                                4,
                                Map.of("Socialise", 8, "Knowledge", -8, "Happiness", 2),
                                80,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("nah, I'm locked in.", 0, Map.of("Socialise", -8, "Knowledge", 8), 0)
                )
        ));

        events.add(new Events(
                "random_study_pop_quiz_surprise",
                "random",
                0,
                Phase.STUDY,
                "Study",
                "Surprise GRADED Pop Quiz!",
                "Your professor suddenly announces a graded pop quiz!",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice("Generative Pre-trained Transformer", 0, Map.of("Happiness", -3), 30),
                        new EventChoice(
                                "Confidence 100",
                                0,
                                Map.of("Socialise", 3),
                                0,
                                List.of(
                                        new Branch(Map.of("Knowledge", 5, "Happiness", 8)),
                                        new Branch(Map.of("Knowledge", -15, "Happiness", -8))
                                ),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        )
                )
        ));

        events.add(new Events(
                "random_study_partner",
                "random",
                0,
                Phase.STUDY,
                "CCA",
                "BFF Study Sesh",
                "Your best friend asks to study with you",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Sure!",
                                1,
                                Map.of("Socialise", 8, "Knowledge", 3, "Happiness", 3),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.BORROW_NOTES
                        ),
                        new EventChoice(
                                "Not feeling it today",
                                -2,
                                Map.of("Socialise", -3, "Knowledge", -1),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.VENT
                        )
                )
        ));

        events.add(new Events(
                "random_vague_answer",
                "random",
                0,
                Phase.STUDY,
                "CCA",
                "Vague answer",
                "Dr Cao did not fully answer your question...",
                "neutral",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice(
                                "Awkwardly tell him you understood everything",
                                0,
                                Map.of("Socialise", 3, "Knowledge", -1),
                                0,
                                List.of(),
                                List.of(NPCFactory.prof),
                                NPCAction.ASK_FEEDBACK
                        ),
                        new EventChoice(
                                "Gotta get to the bottom of this...",
                                1,
                                Map.of("Socialise", -3, "Knowledge", 10),
                                0,
                                List.of(),
                                List.of(NPCFactory.prof),
                                NPCAction.BOOK_CONSULT
                        )
                )
        ));

        events.add(new Events(
                "random_game_losing",
                "random",
                0,
                Phase.STUDY,
                "CCA",
                "Can't end on a loss",
                "It's getting late but you're on a losing streak",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice(
                                "Play until you win",
                                3,
                                Map.of("Socialise", 10),
                                0,
                                List.of(
                                        new Branch(Map.of("Knowledge", -5)),
                                        new Branch(Map.of("Knowledge", -5)),
                                        new Branch(Map.of("Knowledge", -5, "Happiness", 10)),
                                        new Branch(Map.of("Knowledge", -5, "Happiness", -10))
                                ),
                                List.of(NPCFactory.friends),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice(
                                "Just one more game",
                                1,
                                Map.of("Socialise", 2, "Knowledge", -2),
                                0,
                                List.of(),
                                List.of(NPCFactory.prof),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice(
                                "Be disciplined",
                                -2,
                                Map.of("Socialise", 2, "Happiness", -2),
                                0,
                                List.of(),
                                List.of(NPCFactory.prof),
                                NPCAction.HANG_OUT
                        )
                )
        ));

        events.add(new Events(
                "random_laptop_water",
                "random",
                0,
                Phase.STUDY,
                "Study",
                "Water and electricity do not mix.",
                "You accidentally poured water onto your laptop and now it no longer works",
                "neutral",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Repair at the store", 0, Map.of("Happiness", -15, "Knowledge", 3), 90),
                        new EventChoice("Use an old and laggy laptop instead", 0, Map.of("Happiness", -80, "Knowledge", -3), 0)
                )
        ));

        events.add(new Events(
                "random_study_sussy_baka",
                "random",
                0,
                Phase.RECESS,
                "Study",
                "Sussy baka",
                "A classmate approaches you with a strange request...",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "DO IT",
                                4,
                                Map.of("Socialise", 10, "Happiness", 5),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        ),
                        new EventChoice("mmm... nah", 0, Map.of("Socialise", -10), 0)
                )
        ));

        return events;
    }
}