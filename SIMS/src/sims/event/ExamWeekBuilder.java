package sims.event;

import sims.npc.NPC;
import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExamWeekBuilder {

    public static List<Events> buildExamEvents() {
        List<Events> examEvents = new ArrayList<>();

        add(examEvents, "main_t15_study_exam_mode", 15, "Study", "Exam Mode",
                "The semester compresses into revision blocks, past-year papers, and the strange seriousness of exam week.",
                "stress", List.of(NPCFactory.prof),
                choice("Follow a timed revision schedule", 4, Map.of("Knowledge", 8, "Happiness", -1), 0),
                choice("Revise by instinct and tackle weak topics", 3, Map.of("Knowledge", 5), 0),
                choice("Refresh only the parts that feel familiar", 2, Map.of("Knowledge", 2, "Happiness", 1), 0)
        );

        add(examEvents, "main_t15_cca_exam_week_ping", 15, "attend CCA", "One More Ping",
                "Even in exam week, a message from your CCA appears asking for a quick favour or response.",
                "neutral", List.of(NPCFactory.ccaLead),
                choice("Reply politely and keep boundaries clear", 1, Map.of("Socialise", 1), 0),
                choice("Help briefly, then return to revision", 2, Map.of("Socialise", 2, "Knowledge", -1), 0),
                choice("Ignore it and protect your focus", 1, Map.of("Knowledge", 1), 0)
        );

        add(examEvents, "main_t15_social_groupchat_spiral", 15, "Social", "Group Chat Spiral",
                "The class group chat turns into a mix of rumours, reassurance, and low-level panic.",
                "stress", List.of(NPCFactory.bestFriend),
                choice("Mute it and protect your headspace", 1, Map.of("Knowledge", 2, "Happiness", 1), 0),
                choice("Fact-check and calm people down", 2, Map.of("Socialise", 3, "Knowledge", 1), 0),
                choice("Join the spiral and overthink together", 2, Map.of("Socialise", 2, "Happiness", -2), 0)
        );

        add(examEvents, "main_t15_family_wellness_check", 15, "Family", "Wellness Check",
                "At home, people appear with fruit, water, and the very fair question of whether you are taking care of yourself.",
                "warm", List.of(NPCFactory.friends),
                choice("Eat properly and rest for a short while", 2, Map.of("Happiness", 3, "Knowledge", 1), 0),
                choice("Say you are fine and keep studying", 1, Map.of("Happiness", -1), 0),
                choice("Ask for quiet and some space", 1, Map.of("Happiness", 1, "Socialise", 1), 0)
        );

        add(examEvents, "main_t16_study_exam_day", 16, "Study", "Exam Day",
                "The paper is finally here. Everything now comes down to focus, timing, and staying steady under pressure.",
                "stress", List.of(NPCFactory.prof),
                choice("Arrive early, breathe, and trust your preparation", 3, Map.of("Knowledge", 7, "Happiness", 1), 0),
                choice("Do a last-minute skim before entering", 2, Map.of("Knowledge", 4), 0),
                choice("Rush in late and hope adrenaline carries you", 2, Map.of("Happiness", -2), 0)
        );

        add(examEvents, "main_t16_cca_postpaper_ping", 16, "attend CCA", "After the Paper",
                "Once a paper ends, campus life starts creeping back in, even if your brain is still half inside the exam hall.",
                "neutral", List.of(NPCFactory.ccaLead),
                choice("Reply later and rest first", 1, Map.of("Happiness", 1), 0),
                choice("Answer now and clear the notification", 1, Map.of("Socialise", 1), 0),
                choice("Pretend your phone does not exist", 1, Map.of("Happiness", 0), 0)
        );

        add(examEvents, "main_t16_social_postpaper_debrief", 16, "Social", "Post-Paper Debrief",
                "Friends want to compare answers. Part of you knows this is a terrible idea, and part of you cannot resist.",
                "neutral", List.of(NPCFactory.bestFriend),
                npcChoice("Debrief briefly, then let it go", 2, Map.of("Socialise", 2, "Happiness", 1), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Avoid the discussion entirely", 1, Map.of("Happiness", 1), 0),
                choice("Analyse every answer and make yourself worse", 2, Map.of("Happiness", -2), 0)
        );

        add(examEvents, "main_t16_family_release", 16, "Family", "The Release",
                "Once the hardest stretch ends, home feels different. The tension finally starts to leave your body.",
                "warm", List.of(NPCFactory.friends),
                choice("Rest properly and let yourself exhale", 2, Map.of("Happiness", 4), 0),
                choice("Stay keyed up and think about the paper", 1, Map.of("Happiness", -1), 0),
                choice("Shift straight into recovery mode", 1, Map.of("Happiness", 2), 0)
        );

        return examEvents;
    }

    private static void add(List<Events> events, String id, int turn, String category, String title,
                            String desc, String mood, List<NPC> npcs, EventChoice... choices) {
        events.add(new Events(id, "main", turn, Phase.EXAM, category, title, desc, mood, npcs, List.of(choices), null));
    }

    private static EventChoice choice(String description, int energy, Map<String, Integer> effects, int money) {
        return new EventChoice(description, energy, effects, money);
    }

    private static EventChoice npcChoice(String description, int energy, Map<String, Integer> effects, int money,
                                         List<NPC> affectedNPCs, NPCAction action) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action);
    }
}
