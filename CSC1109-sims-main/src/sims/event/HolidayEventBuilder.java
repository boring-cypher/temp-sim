package sims.event;

import sims.npc.NPC;
import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HolidayEventBuilder {

    public static List<Events> buildHolidayEvents() {
        List<Events> holidayEvents = new ArrayList<>();

        add(holidayEvents, "main_t7_study_recess_start", 7, "Study", "Guilt-Free Reading",
                "Recess week is here. No deadlines, no submissions, but part of you wonders if you should still be doing something productive.",
                "neutral", List.of(),
                choice("Read ahead on a topic that's been confusing you", 4, Map.of("Knowledge", 6, "Happiness", 1), 0),
                choice("Skim through your notes just to feel less behind", 2, Map.of("Knowledge", 2), 0),
                choice("Close the laptop. It's recess week.", 1, Map.of("Happiness", 3, "Knowledge", -1), 0)
        );

        add(holidayEvents, "main_t7_cca_recess_hangout", 7, "attend CCA", "Casual CCA Meetup",
                "With no school pressure, your CCA group chat pings: someone's suggesting a casual meetup just for fun.",
                "warm", List.of(NPCFactory.ccaLead),
                npcChoice("Show up and hang out properly", 2, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Drop by for a bit then head off", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Give it a miss and enjoy the quiet", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t7_social_recess_plan", 7, "Social", "Break Plans",
                "Your friends are buzzing about what to do during recess week. Someone suggests catching a movie or going out for a meal.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Organise a proper outing together", 3, Map.of("Socialise", 4, "Happiness", 3), 15,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Join for a chill meal nearby", 2, Map.of("Socialise", 2, "Happiness", 2), 8),
                choice("Stay home", 1, Map.of("Happiness", 10, "Socialise", -10), 0)
        );

        add(holidayEvents, "main_t7_family_recess_first_day", 7, "Family", "First Day Back",
                "It's the first day of recess week and you're home earlier than usual. Your family notices.",
                "warm", List.of(NPCFactory.friends),
                choice("Have a proper meal together and catch up", 1, Map.of("Happiness", 3, "Socialise", 2), 0),
                choice("Help out around the house for a bit", 3, Map.of("Happiness", 2), 0),
                choice("Retreat to your room and decompress alone", 1, Map.of("Happiness", 2, "Socialise", -2), 0)
        );

        add(holidayEvents, "main_t8_study_recess_mid", 8, "Study", "Mid-break Motivation",
                "You're halfway through recess week. The initial relief has settled and a quiet voice says maybe one productive session wouldn't hurt.",
                "neutral", List.of(),
                choice("Sit down for a focused revision session", 4, Map.of("Knowledge", 4, "Happiness", 1), 0),
                choice("Watch a lecture recording you missed", 2, Map.of("Knowledge", 3), 0),
                choice("Ignore it. The break isn't over yet.", 1, Map.of("Happiness", 2, "Knowledge", -1), 0)
        );

        add(holidayEvents, "main_t8_cca_recess_practice", 8, "attend CCA", "Optional Practice",
                "Your CCA lead sends a message about an optional practice session mid-recess. No pressure, but it could be fun.",
                "warm", List.of(NPCFactory.ccaLead),
                npcChoice("Attend and make the most of the free time", 3, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Go just to stay in the loop", 1, Map.of("Socialise", 2), 0),
                choice("Skip it. You need the rest more", -3, Map.of("Happiness", 3, "Socialise", -2), 0)
        );

        add(holidayEvents, "main_t8_social_recess_spontaneous", 8, "Social", "Spontaneous Hangout",
                "A friend texts out of nowhere asking if you're free. No plans, just vibes.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Drop everything and head out", 2, Map.of("Socialise", 3, "Happiness", 3), 10,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Meet up briefly at a nearby spot", 1, Map.of("Socialise", 2, "Happiness", 2), 5),
                choice("Rain check — you're mid-rest", 1, Map.of("Happiness", 1), 0)
        );

        add(holidayEvents, "main_t8_family_recess_outing", 8, "Family", "Family Day Out",
                "Your family suggests a short outing.",
                "warm", List.of(NPCFactory.friends),
                choice("Join them and enjoy the change of scenery", 2, Map.of("Happiness", 4, "Socialise", 2), 12),
                choice("Tag along for part of it then head home", 1, Map.of("Happiness", 2, "Socialise", 1), 0),
                choice("Stay home and hold down the fort", -1, Map.of("Happiness", -2, "Socialise", -2), 0)
        );

        add(holidayEvents, "main_t17_study_decompress", 17, "Study", "Decompression Day",
                "The exams are over. For the first time in weeks, you have time without immediate academic danger attached to it.",
                "positive", List.of(),
                choice("Sleep in and recover properly", 2, Map.of("Happiness", 4), 0),
                choice("Tidy your notes and clear your desk", 2, Map.of("Happiness", 2, "Knowledge", 1), 0),
                choice("Jump straight into planning the break", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t17_cca_casual_wrapup", 17, "attend CCA", "CCA Wrap-Up",
                "Without deadlines looming, a relaxed CCA session feels more like community than obligation.",
                "warm", List.of(NPCFactory.ccaLead),
                npcChoice("Show up and enjoy the lighter mood", 2, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Drop by briefly to say hi", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Skip it and rest at home", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t17_social_postexam_meal", 17, "Social", "Post-Exam Meal",
                "Friends want to celebrate surviving the semester together.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Go out properly and enjoy the relief", 3, Map.of("Socialise", 4, "Happiness", 3), 18,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Join for a short meal and head home early", 2, Map.of("Socialise", 2, "Happiness", 2), 10),
                choice("Skip it and recharge alone", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t17_family_final_exhale", 17, "Family", "Final Exhale",
                "At home, everyone can finally feel that the hardest part has passed.",
                "warm", List.of(NPCFactory.friends),
                choice("Spend a relaxed evening with family", 2, Map.of("Happiness", 3, "Socialise", 1), 0),
                choice("Keep things quiet and recover privately", 1, Map.of("Happiness", 2), 0),
                choice("Sleep through most of the day and call it healing", 1, Map.of("Happiness", 3), 0)
        );

        add(holidayEvents, "main_t18_study_reflect_and_reset", 18, "Study", "Reflect and Reset",
                "With the semester over, you can finally look back clearly and decide what you want to carry forward.",
                "neutral", List.of(),
                choice("Write down lessons from the semester", 2, Map.of("Knowledge", 2, "Happiness", 2), 0),
                choice("Tidy your files and archive the chaos", 2, Map.of("Happiness", 2), 0),
                choice("Ignore all of it and enjoy the freedom", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t18_cca_relaxed_gathering", 18, "attend CCA", "Relaxed Gathering",
                "CCA no longer feels like another deadline. It feels like the part of campus life you actually chose.",
                "positive", List.of(NPCFactory.ccaLead),
                npcChoice("Stay for the full hangout", 2, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Drop by and keep it casual", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Use the free time for yourself instead", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t18_social_reconnect", 18, "Social", "Reconnect Properly",
                "Now that everyone has mental space again, conversations feel more real and less rushed.",
                "warm", List.of(NPCFactory.bestFriend),
                npcChoice("Spend proper time with someone you missed", 3, Map.of("Socialise", 4, "Happiness", 3), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Message a few people and restart old chats", 1, Map.of("Socialise", 2), 0),
                choice("Keep your circle small and your break quiet", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t18_family_rebalance", 18, "Family", "Rebalance",
                "Holiday time makes room for the parts of life that were squeezed by semester pressure.",
                "warm", List.of(NPCFactory.friends),
                choice("Be more present at home", 2, Map.of("Happiness", 3, "Socialise", 1), 0),
                choice("Help out with something useful", 2, Map.of("Happiness", 2), 0),
                choice("Protect your alone time and recover first", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t19_study_resume_refresh", 19, "Study", "Resume Refresh",
                "With the semester behind you, your thoughts start turning toward internships, jobs, and what comes next.",
                "neutral", List.of(),
                choice("Update your resume and list what you achieved", 3, Map.of("Knowledge", 3, "Happiness", 1), 0),
                choice("Clean up your notes first and think about jobs later", 2, Map.of("Happiness", 1), 0),
                choice("Browse opportunities casually without committing yet", 1, Map.of("Happiness", 1), 0)
        );

        add(holidayEvents, "main_t19_cca_networking", 19, "attend CCA", "Networking Through Campus",
                "Sometimes the best opportunities still come through people you already know around school.",
                "positive", List.of(NPCFactory.ccaLead),
                npcChoice("Ask around and stay open to opportunities", 2, Map.of("Socialise", 3, "Happiness", 1), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.ASK_TRAINING_TIPS),
                choice("Stay visible but keep expectations light", 1, Map.of("Socialise", 1), 0),
                choice("Skip the networking mood entirely", 1, Map.of("Happiness", 1), 0)
        );

        add(holidayEvents, "main_t19_social_future_talk", 19, "Social", "Future Talk",
                "A casual conversation with friends turns into a discussion about internships, plans, and what everyone wants next.",
                "neutral", List.of(NPCFactory.bestFriend),
                npcChoice("Talk seriously about future plans", 2, Map.of("Socialise", 2, "Knowledge", 1), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Keep it light and listen more than you speak", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Avoid future talk and keep things present", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t19_family_what_next", 19, "Family", "What Next?",
                "Family begins asking the inevitable question of what you want to do with the break.",
                "neutral", List.of(NPCFactory.friends),
                choice("Explain your plans clearly", 1, Map.of("Happiness", 1, "Socialise", 1), 0),
                choice("Say you are still figuring it out", 1, Map.of("Happiness", 0), 0),
                choice("Change the topic and buy yourself more time", 1, Map.of("Happiness", -1), 0)
        );

        add(holidayEvents, "main_t20_study_side_project", 20, "Study", "Side Project",
                "With rest behind you, you feel ready to build something beyond just surviving the semester.",
                "positive", List.of(),
                choice("Start a small side project or portfolio piece", 4, Map.of("Knowledge", 4, "Happiness", 2), 0),
                choice("Take an online course or learn a new skill", 3, Map.of("Knowledge", 3, "Happiness", 1), 0),
                choice("Sketch ideas and leave room to grow later", 2, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t20_cca_forward_momentum", 20, "attend CCA", "Forward Momentum",
                "Campus life no longer feels like something happening to you. It feels like something you can shape.",
                "positive", List.of(NPCFactory.ccaLead),
                npcChoice("Stay involved and carry momentum into the next term", 2, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Keep in touch but stay flexible", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Let yourself drift for now and revisit later", 1, Map.of("Happiness", 1), 0)
        );

        add(holidayEvents, "main_t20_social_closing_circle", 20, "Social", "Closing Circle",
                "Before the break moves on, you get one last moment to appreciate the people who made the semester survivable.",
                "warm", List.of(NPCFactory.bestFriend),
                npcChoice("Spend time with the people who mattered most", 3, Map.of("Socialise", 4, "Happiness", 3), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Send messages and keep the warmth alive", 1, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Keep the ending quiet and personal", 1, Map.of("Happiness", 2), 0)
        );

        add(holidayEvents, "main_t20_family_new_chapter", 20, "Family", "New Chapter",
                "The break ends not with a dramatic twist, but with the feeling that you are a steadier person than when the semester began.",
                "warm", List.of(NPCFactory.friends),
                choice("Spend a calm day at home and appreciate the reset", 2, Map.of("Happiness", 3), 0),
                choice("Use the day to plan the next chapter", 2, Map.of("Knowledge", 2, "Happiness", 1), 0),
                choice("Keep it simple and let the feeling sink in", 1, Map.of("Happiness", 2), 0)
        );

        return holidayEvents;
    }

    private static void add(List<Events> events, String id, int turn, String category, String title,
                            String desc, String mood, List<NPC> npcs, EventChoice... choices) {
        events.add(new Events(id, "main", turn, Phase.RECESS, category, title, desc, mood, npcs, new ArrayList<>(List.of(choices)), null));
    }

    private static EventChoice choice(String description, int energy, Map<String, Integer> effects, int money) {
        return new EventChoice(description, energy, effects, money);
    }

    private static EventChoice npcChoice(String description, int energy, Map<String, Integer> effects, int money,
                                         List<NPC> affectedNPCs, NPCAction action) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action);
    }
}
