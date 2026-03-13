package sims.event;

import sims.npc.NPCFactory;
import sims.npc.NPCAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExamWeekBuilder {

    public static List<Events> buildExamEvents() {
        List<Events> examEvents = new ArrayList<>();

        // =========================
        // TURN 13 (REVISION WEEK)
        // =========================

        examEvents.add(
                new Events(
                        "finals_war_room_setup",
                        "main",
                        16,
                        Phase.EXAM,
                        "Study",
                        "Finals War Room Setup",
                        "You open your calendar. It opens back-menacingly. Every box says \"deadline\" in different fonts of pain.",
                        "stress",
                        List.of(NPCFactory.prof),
                        List.of(
                                new EventChoice(
                                        "Make a realistic plan with breaks and priorities",
                                        4,
                                        Map.of("Knowledge", 6, "Happiness", 1),
                                        0
                                ),
                                new EventChoice(
                                        "Make an unrealistic plan (confidence is free)",
                                        2,
                                        Map.of("Knowledge", 2, "Happiness", -1),
                                        0
                                ),
                                new EventChoice(
                                        "Avoid planning. Vibes only.",
                                        1,
                                        Map.of("Happiness", 1, "Knowledge", -1),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Happiness", 2)),
                                                new Branch(Map.of("Knowledge", -2))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "past_year_papers_rabbit_hole",
                        "main",
                        16,
                        Phase.EXAM,
                        "Study",
                        "Past-Year Paper Rabbit Hole",
                        "You open \"just one\" past-year paper. Three hours later you're negotiating with Question 4(b) like it's a landlord.",
                        "stress",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Time yourself properly and mark it",
                                        5,
                                        Map.of("Knowledge", 10, "Happiness", -1),
                                        0
                                ),
                                new EventChoice(
                                        "Cherry-pick only the scariest questions",
                                        4,
                                        Map.of("Knowledge", 7, "Happiness", -1),
                                        0
                                ),
                                new EventChoice(
                                        "Search 'exam tips' and fall into a motivational video spiral",
                                        2,
                                        Map.of("Happiness", 2, "Knowledge", -1),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Knowledge", 3)),
                                                new Branch(Map.of("Happiness", -3))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "groupchat_rumour_storm",
                        "main",
                        16,
                        Phase.EXAM,
                        "Social",
                        "The Group Chat Is On Fire",
                        "Someone types: \"I heard the exam is ALL chapter 7.\" Another replies: \"Bro I studied chapter 6.\" The chaos spreads like Wi-Fi.",
                        "social",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Mute the chat and keep revising",
                                        1,
                                        Map.of("Knowledge", 2, "Happiness", 1),
                                        0
                                ),
                                new EventChoice(
                                        "Fact-check calmly and post clarifications",
                                        2,
                                        Map.of("Socialise", 3, "Knowledge", 1),
                                        0
                                ),
                                new EventChoice(
                                        "Panic with everyone (caps-lock included)",
                                        2,
                                        Map.of("Socialise", 2, "Happiness", -2),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Knowledge", -2)),
                                                new Branch(Map.of("Happiness", 1))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "last_minute_duty_call",
                        "main",
                        16,
                        Phase.EXAM,
                        "CCA",
                        "The \"Quick One\" Duty Call",
                        "Your CCA lead messages: \"Can help for 30 minutes?\" The message was sent 30 minutes ago.",
                        "stress",
                        List.of(NPCFactory.ccaLead),
                        List.of(
                                new EventChoice(
                                        "Go help anyway (you are a good person)",
                                        3,
                                        Map.of("Socialise", 3, "Happiness", 1, "Knowledge", -1),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.JOIN_EVENT
                                ),
                                new EventChoice(
                                        "Decline politely and protect your revision time",
                                        1,
                                        Map.of("Knowledge", 2, "Socialise", -1),
                                        0
                                ),
                                new EventChoice(
                                        "Compromise: help remotely for 10 minutes",
                                        2,
                                        Map.of("Socialise", 2, "Knowledge", 1),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Happiness", 1)),
                                                new Branch(Map.of("Happiness", -2))
                                        ),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.JOIN_EVENT
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "exam_week_wellness_check",
                        "main",
                        16,
                        Phase.EXAM,
                        "Family",
                        "Exam Week Wellness Check",
                        "Your family appears with fruit, snacks, and the question: \"Are you sleeping enough?\" (You are not.)",
                        "neutral",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Eat properly and take a short rest",
                                        2,
                                        Map.of("Happiness", 3, "Knowledge", 1),
                                        0
                                ),
                                new EventChoice(
                                        "Tell them you're fine (you are not fine)",
                                        1,
                                        Map.of("Happiness", -1),
                                        0
                                ),
                                new EventChoice(
                                        "Ask for quiet time and set boundaries respectfully",
                                        1,
                                        Map.of("Happiness", 1, "Socialise", 1),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Happiness", 2)),
                                                new Branch(Map.of("Happiness", -2))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        // =========================
        // TURN 14 (EXAM DAY)
        // =========================

        examEvents.add(
                new Events(
                        "exam_day_detour",
                        "main",
                        17,
                        Phase.EXAM,
                        "Study",
                        "Exam Day Detour",
                        "On the way to your exam venue, campus decides to host an event. The walkway becomes a maze of booths, balloons, and one guy doing a TikTok dance battle.",
                        "stress",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Take a ride-hailing shortcut",
                                        1,
                                        Map.of("Happiness", -1),
                                        18
                                ),
                                new EventChoice(
                                        "Sprint like it's a sports anime opening",
                                        3,
                                        Map.of("Happiness", -2),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Happiness", 2)),
                                                new Branch(Map.of("Knowledge", -2))
                                        ),
                                        List.of(),
                                        null
                                ),
                                new EventChoice(
                                        "Ask a stranger for directions (humbling but effective)",
                                        2,
                                        Map.of("Socialise", 2, "Happiness", 1),
                                        0
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "calculator_betrayal",
                        "main",
                        17,
                        Phase.EXAM,
                        "Study",
                        "Calculator Betrayal",
                        "You press your calculator. Nothing. It has chosen violence. The exam clock ticks louder than your thoughts.",
                        "stress",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Borrow a spare (ask nicely, eyes watery)",
                                        2,
                                        Map.of("Happiness", 1),
                                        0
                                ),
                                new EventChoice(
                                        "Buy batteries from the vending machine",
                                        1,
                                        Map.of("Happiness", -1),
                                        5
                                ),
                                new EventChoice(
                                        "Wing it with mental math",
                                        3,
                                        Map.of("Knowledge", 2, "Happiness", -2),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Knowledge", 3)),
                                                new Branch(Map.of("Knowledge", -3))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "whispered_sos_in_exam_hall",
                        "main",
                        17,
                        Phase.EXAM,
                        "Study",
                        "Whispered SOS",
                        "Mid-paper, your friend leans over and whispers: \"Hey... hey... I need your help with this question.\" The invigilator's footsteps sound like boss music.",
                        "climax",
                        List.of(NPCFactory.bestFriend),
                        List.of(
                                new EventChoice(
                                        "Shake your head and focus on your own paper",
                                        1,
                                        Map.of("Knowledge", 2, "Socialise", -2),
                                        0
                                ),
                                new EventChoice(
                                        "Whisper: \"After this paper, I'll explain properly\"",
                                        1,
                                        Map.of("Socialise", 2, "Happiness", 1),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.bestFriend),
                                        NPCAction.VENT
                                ),
                                new EventChoice(
                                        "Risk it and give a tiny hint",
                                        1,
                                        Map.of("Socialise", 3, "Happiness", -1),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Happiness", -4, "Socialise", -2, "Knowledge", -2)),
                                                new Branch(Map.of("Socialise", 1))
                                        ),
                                        List.of(NPCFactory.bestFriend),
                                        NPCAction.VENT
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "post_exam_autopsy_session",
                        "main",
                        17,
                        Phase.EXAM,
                        "Social",
                        "Post-Exam Autopsy Session",
                        "Outside the hall, people start dissecting answers immediately. Someone says: \"I put 42.\" Another says: \"I put emotional damage.\"",
                        "neutral",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Join the debrief (trauma bonding)",
                                        2,
                                        Map.of("Socialise", 3),
                                        0
                                ),
                                new EventChoice(
                                        "Politely escape before your brain combusts",
                                        1,
                                        Map.of("Happiness", 2),
                                        0
                                ),
                                new EventChoice(
                                        "Argue about a question with full confidence",
                                        2,
                                        Map.of("Socialise", 2),
                                        0,
                                        List.of(
                                                new Branch(Map.of("Happiness", 2)),
                                                new Branch(Map.of("Happiness", -3))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        examEvents.add(
                new Events(
                        "celebration_plans_appear",
                        "main",
                        17,
                        Phase.EXAM,
                        "Family",
                        "Celebration Plans Appear",
                        "Your family says: \"Good job! Let's eat nice tonight!\" Your brain says: \"I want to become furniture.\"",
                        "neutral",
                        List.of(),
                        List.of(
                                new EventChoice(
                                        "Go celebrate (you earned it)",
                                        3,
                                        Map.of("Happiness", 4, "Socialise", 2),
                                        20
                                ),
                                new EventChoice(
                                        "Ask to celebrate later; you need quiet recovery",
                                        1,
                                        Map.of("Happiness", 2),
                                        0
                                ),
                                new EventChoice(
                                        "Compromise: short meal then early sleep",
                                        2,
                                        Map.of("Happiness", 3, "Knowledge", 1),
                                        10,
                                        List.of(
                                                new Branch(Map.of("Happiness", 1)),
                                                new Branch(Map.of("Happiness", -2))
                                        ),
                                        List.of(),
                                        null
                                )
                        )
                )
        );

        return examEvents;
    }
}