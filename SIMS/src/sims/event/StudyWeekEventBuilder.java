package sims.event;

import sims.npc.NPC;
import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudyWeekEventBuilder {

    public static List<Events> buildStudyEvents() {
        List<Events> events = new ArrayList<>();

        // Weeks 1-3: linear setup
        add(events, "main_t01_study_orientation_briefing", 1, "Study", "Orientation Briefing",
                "The first week starts with module briefings, orientation talks, and the feeling that university is finally real.",
                "neutral", List.of(NPCFactory.prof), null,
                choice("Write down deadlines and build a weekly plan", 4, Map.of("Knowledge", 6, "Happiness", 1), 0),
                choice("Take some quick notes and sort it out later", 2, Map.of("Knowledge", 3), 0),
                choice("Just survive the briefing and think later", 1, Map.of("Happiness", 1), 0)
        );

        add(events, "main_t01_cca_orientation_fair", 1, "attend CCA", "Orientation Fair",
                "Student groups are everywhere, and a CCA senior enthusiastically tries to rope you into campus life.",
                "positive", List.of(NPCFactory.ccaLead), null,
                npcChoice("Attend a trial session and introduce yourself", 3, Map.of("Socialise", 4, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Collect brochures and say you'll think about it", 1, Map.of("Happiness", 1), 0),
                choice("Skip the fair and keep your schedule free", 2, Map.of("Knowledge", 2), 0)
        );

        add(events, "main_t01_social_new_faces", 1, "Social", "New Faces",
                "A few classmates invite you to sit with them after orientation. It is awkward, but it could be the start of something.",
                "positive", List.of(NPCFactory.bestFriend), null,
                npcChoice("Join the casual meetup and stay awhile", 3, Map.of("Socialise", 5, "Happiness", 3), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Introduce yourself, then head off early", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Keep to yourself and leave quietly", 1, Map.of("Happiness", 0), 0)
        );

        add(events, "main_t01_family_first_call_home", 1, "Family", "First Call Home",
                "Your family checks in and asks how the first week is going.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Share honestly that you are nervous but excited", 2, Map.of("Happiness", 3, "Socialise", 1), 0),
                choice("Keep it short and say everything is fine", 1, Map.of("Happiness", 1), 0),
                choice("Delay the call until later", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t02_study_first_real_week", 2, "Study", "First Real Week",
                "Orientation energy fades and weekly tasks begin to pile up. The semester starts to feel structured.",
                "neutral", List.of(NPCFactory.prof), null,
                choice("Attend lectures properly and organise your notes", 4, Map.of("Knowledge", 6), 0),
                choice("Watch recordings and catch up from home", 3, Map.of("Knowledge", 4, "Happiness", 1), 0),
                choice("Assume you can catch up later", 1, Map.of("Knowledge", -2, "Happiness", 1), 0)
        );

        add(events, "main_t02_cca_first_trial", 2, "attend CCA", "First Trial Session",
                "The CCA senior remembers you from orientation and asks if you are serious about joining.",
                "social", List.of(NPCFactory.ccaLead), null,
                npcChoice("Show up and give it a proper try", 3, Map.of("Socialise", 4, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Stay for a short while and observe", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip it this week and prioritise classes", 2, Map.of("Knowledge", 2), 0)
        );

        add(events, "main_t02_social_lunch_group", 2, "Social", "Lunch Group",
                "A tutorial group invites you to lunch. You recognise one classmate who could become important later.",
                "positive", List.of(NPCFactory.grpMate), null,
                npcChoice("Join and get to know everyone", 3, Map.of("Socialise", 4, "Happiness", 2), 10,
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Join briefly, then leave to study", 2, Map.of("Socialise", 2, "Knowledge", 1), 8),
                choice("Decline and keep your day free", 1, Map.of("Knowledge", 1), 0)
        );

        add(events, "main_t02_family_weekend_errand", 2, "Family", "Weekend Errand",
                "A family member asks for help with a small weekend task.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Help out and clear it quickly", 3, Map.of("Happiness", 2, "Socialise", 1), 0),
                choice("Explain you are busy and reschedule", 1, Map.of("Happiness", 0), 0),
                choice("Refuse bluntly and stay home", 1, Map.of("Happiness", -2), 0)
        );

        add(events, "main_t03_study_tutorial_pressure", 3, "Study", "Tutorial Pressure",
                "Participation starts to matter. You realise preparation cannot be left until the last minute.",
                "tense", List.of(NPCFactory.prof), null,
                choice("Prepare properly and answer when called on", 4, Map.of("Knowledge", 7, "Happiness", 1), 0),
                choice("Read just enough to survive", 2, Map.of("Knowledge", 3), 0),
                choice("Wing it and hope your group carries", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
        );

        add(events, "main_t03_cca_commitment_check", 3, "attend CCA", "Commitment Check",
                "Your CCA senior starts noticing who keeps showing up and who was only there for orientation vibes.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                npcChoice("Attend again and show consistency", 3, Map.of("Socialise", 4, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Send a polite message that you are busy", 1, Map.of("Socialise", 0), 0),
                choice("Ghost the session entirely", 1, Map.of("Socialise", -2), 0)
        );

        add(events, "main_t03_social_study_group", 3, "Social", "Study Group Start",
                "A small study group begins forming. Joining now could make the semester feel less lonely.",
                "positive", List.of(NPCFactory.bestFriend, NPCFactory.grpMate), null,
                npcChoice("Join regularly and contribute notes", 3, Map.of("Socialise", 4, "Knowledge", 2, "Happiness", 2), 0,
                        List.of(NPCFactory.bestFriend, NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Drop in once to see how it feels", 2, Map.of("Socialise", 2, "Knowledge", 1), 0),
                choice("Study alone for now", 2, Map.of("Knowledge", 2), 0)
        );

        add(events, "main_t03_family_small_pressure", 3, "Family", "Small Pressure",
                "At home, people start asking whether university is harder than you expected.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Admit it is getting busy and ask for understanding", 2, Map.of("Happiness", 2), 0),
                choice("Laugh it off and downplay everything", 1, Map.of("Happiness", 0), 0),
                choice("Brush the conversation aside", 1, Map.of("Happiness", -1), 0)
        );

        // Weeks 4-6: first branch pocket in tone (academic/social/CCA leaning)
        add(events, "main_t04_study_first_assignment", 4, "Study", "First Graded Assignment",
                "The first graded task lands. Uni stops feeling theoretical and starts feeling measured.",
                "tense", List.of(NPCFactory.prof), null,
                choice("Block out time and work ahead", 4, Map.of("Knowledge", 7, "Happiness", -1), 0),
                choice("Do enough to stay on track", 3, Map.of("Knowledge", 4), 0),
                choice("Push it later and hope you catch up", 1, Map.of("Knowledge", -2, "Happiness", 1), 0)
        );

        add(events, "main_t04_cca_planning_meeting", 4, "attend CCA", "Planning Meeting",
                "The CCA shifts from fun introductions to actual responsibility.",
                "social", List.of(NPCFactory.ccaLead), null,
                npcChoice("Volunteer for a small role", 3, Map.of("Socialise", 4, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Attend but keep your commitment light", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip this week and focus on coursework", 2, Map.of("Knowledge", 2), 0)
        );

        add(events, "main_t04_social_friend_needs_company", 4, "Social", "A Friend Needs Company",
                "Someone close to you looks tired and asks if you have time to talk after class.",
                "warm", List.of(NPCFactory.bestFriend), null,
                npcChoice("Stay and listen properly", 3, Map.of("Socialise", 4, "Happiness", 3), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.VENT),
                choice("Keep them company for a short while", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Say you are swamped and leave", 1, Map.of("Knowledge", 1, "Socialise", -1), 0)
        );

        add(events, "main_t04_family_budget_check", 4, "Family", "Budget Check",
                "You realise semester expenses are starting to add up.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Review your spending and cut back a little", 2, Map.of("Happiness", 1), 0),
                choice("Ask for a bit more support this month", 1, Map.of("Happiness", 0), 0),
                choice("Ignore it and hope it works out", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t05_study_project_kickoff", 5, "Study", "Project Kickoff",
                "Group project work officially begins, and your first meeting decides how organised the semester will feel.",
                "neutral", List.of(NPCFactory.grpMate), null,
                npcChoice("Set deadlines and assign roles clearly", 4, Map.of("Knowledge", 6, "Socialise", 1), 0,
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Keep the meeting casual and flexible", 3, Map.of("Knowledge", 3, "Socialise", 2), 0),
                choice("Let things stay vague for now", 1, Map.of("Knowledge", -2), 0)
        );

        add(events, "main_t05_cca_extra_practice", 5, "attend CCA", "Extra Practice",
                "The CCA senior asks whether you can stay a little longer after the main session.",
                "social", List.of(NPCFactory.ccaLead), null,
                npcChoice("Stay and help out", 3, Map.of("Socialise", 4, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Attend the main session only", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip practice to keep your evening free", 1, Map.of("Knowledge", 1), 0)
        );

        add(events, "main_t05_social_project_lunch", 5, "Social", "Project Lunch",
                "Your project mate asks if you want to grab lunch and align expectations before things get messy.",
                "neutral", List.of(NPCFactory.grpMate), null,
                npcChoice("Go and talk through the project properly", 3, Map.of("Socialise", 3, "Knowledge", 2), 12,
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Grab something quick and keep it casual", 2, Map.of("Socialise", 2), 8),
                choice("Decline and keep working alone", 1, Map.of("Knowledge", 1), 0)
        );

        add(events, "main_t05_family_dinner_invite", 5, "Family", "Dinner Invite",
                "Family asks if you can be present for dinner this week instead of hiding behind your schedule.",
                "warm", List.of(NPCFactory.friends), null,
                choice("Show up and be present", 2, Map.of("Happiness", 3), 0),
                choice("Stay for a short while then return to work", 1, Map.of("Happiness", 1), 0),
                choice("Skip and keep grinding", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
        );

        add(events, "main_t06_study_quiz_week", 6, "Study", "Quiz Week",
                "A small quiz reminds everyone that weekly effort actually matters.",
                "tense", List.of(NPCFactory.prof), null,
                choice("Revise properly and test yourself", 4, Map.of("Knowledge", 7, "Happiness", -1), 0),
                choice("Do a quick review and hope for the best", 2, Map.of("Knowledge", 3), 0),
                choice("Trust your memory and walk in cold", 1, Map.of("Knowledge", -3), 0)
        );

        add(events, "main_t06_cca_event_rehearsal", 6, "attend CCA", "Event Rehearsal",
                "The CCA event is getting closer and attendance suddenly matters a lot more.",
                "social", List.of(NPCFactory.ccaLead), null,
                npcChoice("Be there and help tighten things up", 3, Map.of("Socialise", 4, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Show up late but still contribute", 2, Map.of("Socialise", 2), 0),
                choice("Miss it and focus on academics", 2, Map.of("Knowledge", 2), 0)
        );

        add(events, "main_t06_social_keep_in_touch", 6, "Social", "Keeping In Touch",
                "You realise that if you do not make time for people now, the semester will slowly become all work.",
                "warm", List.of(NPCFactory.bestFriend), null,
                npcChoice("Make time for a proper catch-up", 3, Map.of("Socialise", 4, "Happiness", 3), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Reply to messages and keep things warm", 1, Map.of("Socialise", 2), 0),
                choice("Go quiet and focus only on tasks", 1, Map.of("Knowledge", 1, "Socialise", -1), 0)
        );

        add(events, "main_t06_family_energy_check", 6, "Family", "Energy Check",
                "At home, someone notices you look more tired than usual.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Rest a little and eat properly", 2, Map.of("Happiness", 3), 0),
                choice("Say you are fine and keep moving", 1, Map.of("Happiness", -1), 0),
                choice("Use the quiet time to study instead", 1, Map.of("Knowledge", 1), 0)
        );

        // Weeks 7-8: merge back into main spine, then project branch begins
        add(events, "main_t07_study_checkpoint_week", 7, "Study", "Checkpoint Week",
                "Early-semester choices catch up with everyone. This week feels like a real academic checkpoint.",
                "tense", List.of(NPCFactory.prof), null,
                choice("Push through and submit your best work", 4, Map.of("Knowledge", 7, "Happiness", -1), 0),
                choice("Do a solid but not perfect job", 3, Map.of("Knowledge", 4), 0),
                choice("Submit something rushed and move on", 1, Map.of("Knowledge", -2), 0)
        );

        add(events, "main_t07_cca_midsemester_checkin", 7, "attend CCA", "Mid-Semester Check-In",
                "Your CCA senior starts recognising who has really stayed involved.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                npcChoice("Attend and show you are still committed", 3, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Drop by briefly so you are not forgotten", 2, Map.of("Socialise", 1), 0),
                choice("Skip and protect your workload", 1, Map.of("Knowledge", 1), 0)
        );

        add(events, "main_t07_social_quiet_support", 7, "Social", "Quiet Support",
                "Everyone is busier now. Social life becomes less exciting and more about small acts of support.",
                "warm", List.of(NPCFactory.bestFriend), null,
                npcChoice("Check in properly with a friend", 2, Map.of("Socialise", 3, "Happiness", 2), 0,
                        List.of(NPCFactory.bestFriend), NPCAction.VENT),
                choice("Send a message and promise to catch up later", 1, Map.of("Socialise", 1), 0),
                choice("Disappear into your workload", 1, Map.of("Knowledge", 1, "Socialise", -1), 0)
        );

        add(events, "main_t07_family_small_anchor", 7, "Family", "Small Anchor",
                "Home becomes less about conversation and more about giving you somewhere steady to return to.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Take the support and slow down for an evening", 2, Map.of("Happiness", 3), 0),
                choice("Stay nearby but keep studying", 1, Map.of("Knowledge", 1), 0),
                choice("Keep to yourself and power through", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t08_study_project_crack", 8, "Study", "Cracks in the Project",
                "The group project stops feeling smooth. Deadlines are slipping, communication is messy, and someone needs to decide what happens next.",
                "tense", List.of(NPCFactory.grpMate, NPCFactory.prof), null,
                routeChoice("Quietly take on the extra work and keep things moving", 4, Map.of("Knowledge", 5, "Happiness", -2), 0,
                        StoryState.ProjectRoute.CARRY),
                routeNpcChoice("Call for a direct meeting and reset expectations", 3, Map.of("Socialise", 3, "Knowledge", 2), 0,
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT, StoryState.ProjectRoute.REPAIR),
                routeNpcChoice("Document the problem and seek formal guidance", 2, Map.of("Knowledge", 2, "Socialise", -1), 0,
                        List.of(NPCFactory.prof), NPCAction.BOOK_CONSULT, StoryState.ProjectRoute.ESCALATE)
        );

        add(events, "main_t08_cca_busy_week", 8, "attend CCA", "Busy Week Clash",
                "CCA still wants your time, but the project has made your week feel dangerously full.",
                "stress", List.of(NPCFactory.ccaLead), null,
                choice("Attend and try to keep both worlds afloat", 3, Map.of("Socialise", 3, "Happiness", 1, "Knowledge", -1), 0),
                choice("Send a message and sit this one out", 1, Map.of("Knowledge", 1), 0),
                choice("Ignore it and deal with the fallout later", 1, Map.of("Socialise", -1), 0)
        );

        add(events, "main_t08_social_group_chat_tension", 8, "Social", "Group Chat Tension",
                "The group chat starts sounding different. Messages feel shorter, and nobody wants to say the real issue first.",
                "tense", List.of(NPCFactory.grpMate), null,
                choice("Keep the tone calm and constructive", 2, Map.of("Socialise", 2), 0),
                choice("Reply only when needed", 1, Map.of("Happiness", 0), 0),
                choice("Mute the chat and avoid it", 1, Map.of("Happiness", 1, "Socialise", -2), 0)
        );

        add(events, "main_t08_family_busy_signal", 8, "Family", "Busy Signal",
                "At home, people can tell the semester has entered a harder phase.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Be honest that things are tense", 1, Map.of("Happiness", 2), 0),
                choice("Say you are managing and move on", 1, Map.of("Happiness", 0), 0),
                choice("Brush it off and keep working", 1, Map.of("Happiness", -1), 0)
        );

        // Weeks 9-11: project branch pocket
        add(events, "main_t09_study_carry_followup", 9, "Study", "Late-Night Fixes",
                "You quietly keep patching the project on your own. The work is moving, but so is your exhaustion.",
                "stress", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.CARRY,
                choice("Keep going and cover the gaps", 4, Map.of("Knowledge", 6, "Happiness", -3), 0),
                choice("Do the essential fixes only", 3, Map.of("Knowledge", 4, "Happiness", -1), 0),
                choice("Pause and accept it cannot all be perfect", 2, Map.of("Happiness", 1), 0)
        );

        add(events, "main_t09_study_repair_followup", 9, "Study", "Repairing the Group",
                "After a difficult conversation, the project starts moving again. It is not smooth, but it is more honest.",
                "neutral", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.REPAIR,
                choice("Set a tighter shared plan and move forward", 4, Map.of("Knowledge", 5, "Socialise", 2), 0),
                choice("Keep expectations simple and realistic", 3, Map.of("Knowledge", 3, "Happiness", 1), 0),
                choice("Step back and let others prove themselves", 2, Map.of("Happiness", 1), 0)
        );

        add(events, "main_t09_study_escalate_followup", 9, "Study", "Formal Guidance",
                "You choose the professional route. The problem is now visible, but so is the distance it creates.",
                "tense", List.of(NPCFactory.prof), StoryState.ProjectRoute.ESCALATE,
                npcChoice("Use the consult to protect the project properly", 3, Map.of("Knowledge", 5, "Socialise", -1), 0,
                        List.of(NPCFactory.prof), NPCAction.BOOK_CONSULT),
                choice("Keep things factual and low-drama", 2, Map.of("Knowledge", 3), 0),
                choice("Second-guess yourself all week", 1, Map.of("Happiness", -2), 0)
        );

        add(events, "main_t09_cca_still_running", 9, "attend CCA", "Life Keeps Moving",
                "Even while the project eats your brain, other commitments do not disappear.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                choice("Show up and try to stay visible", 3, Map.of("Socialise", 3, "Happiness", 1), 0),
                choice("Sit this week out and focus on recovery", 1, Map.of("Knowledge", 1), 0),
                choice("Do the minimum and leave early", 2, Map.of("Socialise", 1), 0)
        );

        add(events, "main_t09_social_friend_notice", 9, "Social", "A Friend Notices",
                "Someone close to you notices that you have been more tense and distracted lately.",
                "warm", List.of(NPCFactory.bestFriend), null,
                choice("Open up a little", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                choice("Say you are just busy", 1, Map.of("Happiness", 0), 0),
                choice("Brush them off and keep moving", 1, Map.of("Happiness", -1, "Socialise", -1), 0)
        );

        add(events, "main_t09_family_hold_together", 9, "Family", "Holding It Together",
                "Home becomes the place where you either recover properly or pretend you do not need to.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Take a proper meal and short break", 2, Map.of("Happiness", 3), 0),
                choice("Keep working while half-listening", 1, Map.of("Knowledge", 1), 0),
                choice("Avoid everyone and isolate yourself", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t10_social_carry_route", 10, "Social", "Quiet Resentment",
                "Because you kept carrying the load, your conversations start sounding more tired than kind.",
                "stress", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.CARRY,
                choice("Stay polite and keep the peace", 2, Map.of("Socialise", 1), 0),
                choice("Admit you are frustrated", 2, Map.of("Happiness", 1, "Socialise", -1), 0),
                choice("Stop replying for a while", 1, Map.of("Happiness", 0, "Socialise", -2), 0)
        );

        add(events, "main_t10_social_repair_route", 10, "Social", "Hard but Honest",
                "Repairing the group means more difficult conversations, but the air feels less fake now.",
                "neutral", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.REPAIR,
                npcChoice("Hold everyone accountable and keep talking", 3, Map.of("Socialise", 3, "Knowledge", 1), 0,
                        List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Encourage small wins and move forward", 2, Map.of("Happiness", 1, "Socialise", 2), 0),
                choice("Back off for a day to calm things down", 1, Map.of("Happiness", 1), 0)
        );

        add(events, "main_t10_social_escalate_route", 10, "Social", "Professional Distance",
                "The project is safer, but the group dynamic is colder. People speak more carefully around you now.",
                "tense", List.of(NPCFactory.grpMate, NPCFactory.prof), StoryState.ProjectRoute.ESCALATE,
                choice("Stay formal and keep boundaries clear", 2, Map.of("Knowledge", 2), 0),
                choice("Try to soften the atmosphere a little", 2, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Lean fully into distance and efficiency", 1, Map.of("Socialise", -2), 0)
        );

        add(events, "main_t10_study_continuous_assessment", 10, "Study", "Continuous Assessment",
                "The semester does not pause for your project drama. Another assessment is already here.",
                "tense", List.of(NPCFactory.prof), null,
                choice("Lock in and push through it", 4, Map.of("Knowledge", 7, "Happiness", -1), 0),
                choice("Do enough to stay afloat", 3, Map.of("Knowledge", 4), 0),
                choice("Trade quality for survival", 2, Map.of("Knowledge", 1, "Happiness", 1), 0)
        );

        add(events, "main_t10_cca_obligation", 10, "attend CCA", "CCA Obligation",
                "A small duty or attendance expectation still hangs over your week.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                choice("Handle it and move on", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Ask for understanding and skip it", 1, Map.of("Knowledge", 1), 0),
                choice("Half-show up and leave early", 1, Map.of("Socialise", 0), 0)
        );

        add(events, "main_t10_family_exhaustion", 10, "Family", "Visible Exhaustion",
                "By now, your stress is no longer subtle.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Accept help and recover a little", 2, Map.of("Happiness", 3), 0),
                choice("Insist you are okay", 1, Map.of("Happiness", -1), 0),
                choice("Work through the tiredness", 1, Map.of("Knowledge", 1), 0)
        );

        add(events, "main_t11_study_carry_presentation", 11, "Study", "Held Together by Sheer Effort",
                "Presentation week arrives, and you make the project look more stable than it really was.",
                "stress", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.CARRY,
                choice("Give everything you have left", 4, Map.of("Knowledge", 6, "Happiness", -2), 0),
                choice("Aim for competent, not perfect", 3, Map.of("Knowledge", 4), 0),
                choice("Just get through the room alive", 2, Map.of("Happiness", 1), 0)
        );

        add(events, "main_t11_study_repair_presentation", 11, "Study", "A Hard-Earned Presentation",
                "It is not a miracle recovery, but the group holds together well enough to present with some dignity.",
                "neutral", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.REPAIR,
                choice("Lead confidently and trust the prep", 4, Map.of("Knowledge", 6, "Socialise", 1), 0),
                choice("Keep it calm and clean", 3, Map.of("Knowledge", 4, "Happiness", 1), 0),
                choice("Stay low-profile and let the structure carry", 2, Map.of("Knowledge", 2), 0)
        );

        add(events, "main_t11_study_escalate_presentation", 11, "Study", "Professional but Cold",
                "The presentation is orderly, but the room can feel the distance inside the group.",
                "tense", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.ESCALATE,
                choice("Deliver it cleanly and move on", 3, Map.of("Knowledge", 5), 0),
                choice("Try to rebuild some goodwill in the room", 3, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Keep it strictly formal", 2, Map.of("Knowledge", 3, "Socialise", -1), 0)
        );

        add(events, "main_t11_social_after_presentation", 11, "Social", "After the Presentation",
                "Once the main milestone passes, everyone reacts differently: relief, awkwardness, or just exhaustion.",
                "neutral", List.of(NPCFactory.bestFriend, NPCFactory.grpMate), null,
                choice("Take a breather with people you trust", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                choice("Keep things civil and move on", 1, Map.of("Happiness", 1), 0),
                choice("Go straight home and shut down", 1, Map.of("Happiness", 0), 0)
        );

        add(events, "main_t11_cca_still_exists", 11, "attend CCA", "Still Existing Beyond Academics",
                "CCA feels quieter now, but it reminds you there is still a world outside class.",
                "warm", List.of(NPCFactory.ccaLead), null,
                choice("Show up for a lighter session", 2, Map.of("Socialise", 2, "Happiness", 2), 0),
                choice("Skip and rest properly", 1, Map.of("Happiness", 1), 0),
                choice("Drop in only briefly", 1, Map.of("Socialise", 1), 0)
        );

        add(events, "main_t11_family_late_semester_call", 11, "Family", "Late-Semester Call",
                "A family member checks in and hears the difference in your voice immediately.",
                "warm", List.of(NPCFactory.friends), null,
                choice("Tell them the roughest part just passed", 1, Map.of("Happiness", 2), 0),
                choice("Keep it vague and move on", 1, Map.of("Happiness", 0), 0),
                choice("Say you are too tired to talk", 1, Map.of("Happiness", -1), 0)
        );

        // Weeks 12-14: merge into final push
        add(events, "main_t12_study_final_assignment_wave", 12, "Study", "Final Assignment Wave",
                "The project may be behind you, but final assignments start arriving one after another.",
                "tense", List.of(NPCFactory.prof), null,
                choice("Map out everything clearly and start early", 4, Map.of("Knowledge", 7), 0),
                choice("Take them one at a time and stay calm", 3, Map.of("Knowledge", 4, "Happiness", 1), 0),
                choice("Avoid the list because it is too overwhelming", 1, Map.of("Happiness", -1, "Knowledge", -2), 0)
        );

        add(events, "main_t12_cca_winding_down", 12, "attend CCA", "Winding Down",
                "CCA involvement becomes lighter as academics take over campus life.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                choice("Attend one more proper session", 2, Map.of("Socialise", 2, "Happiness", 2), 0),
                choice("Send your apologies and step back for now", 1, Map.of("Knowledge", 1), 0),
                choice("Disappear and hope no one asks", 1, Map.of("Socialise", -1), 0)
        );

        add(events, "main_t12_social_small_recovery", 12, "Social", "Small Recovery",
                "Friends stop asking for big hangouts. The semester becomes about quiet support instead.",
                "warm", List.of(NPCFactory.bestFriend), null,
                choice("Have a short but proper catch-up", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                choice("Exchange a few messages and keep going", 1, Map.of("Socialise", 1), 0),
                choice("Stay silent and focus on work", 1, Map.of("Knowledge", 1), 0)
        );

        add(events, "main_t12_family_practical_support", 12, "Family", "Practical Support",
                "Support at home becomes less emotional and more practical: food, quiet, and space.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Accept the help and work with a clearer head", 2, Map.of("Happiness", 3), 0),
                choice("Take it for granted and keep moving", 1, Map.of("Knowledge", 1), 0),
                choice("Stay in your own bubble", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t13_study_revision_week", 13, "Study", "Revision Week",
                "Teaching slows, but internal pressure rises. The final stretch is less chaotic and more mentally heavy.",
                "stress", List.of(NPCFactory.prof), null,
                choice("Build a disciplined revision plan", 4, Map.of("Knowledge", 8, "Happiness", -1), 0),
                choice("Revise by urgency and stay flexible", 3, Map.of("Knowledge", 5), 0),
                choice("Stare at your notes until guilt becomes a strategy", 1, Map.of("Happiness", -2), 0)
        );

        add(events, "main_t13_cca_pause", 13, "attend CCA", "CCA Pause",
                "Campus activities go quieter as everyone turns toward final prep.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                choice("Take the pause and focus on revision", 1, Map.of("Knowledge", 1), 0),
                choice("Check in briefly so ties stay warm", 1, Map.of("Socialise", 1), 0),
                choice("Pretend the pause means you can relax", 1, Map.of("Happiness", 1, "Knowledge", -1), 0)
        );

        add(events, "main_t13_social_library_bond", 13, "Social", "Library Bonding",
                "Social time now looks like shared silence, snacks, and keeping each other awake in the library.",
                "warm", List.of(NPCFactory.bestFriend), null,
                choice("Study beside a friend and keep each other sane", 3, Map.of("Knowledge", 2, "Socialise", 2, "Happiness", 2), 0),
                choice("Revise alone but stay reachable", 2, Map.of("Knowledge", 3), 0),
                choice("Withdraw completely", 1, Map.of("Knowledge", 1, "Socialise", -1), 0)
        );

        add(events, "main_t13_family_sleep_question", 13, "Family", "Are You Sleeping Enough?",
                "Someone at home asks a very fair question that you do not want to answer honestly.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Sleep earlier and protect your brain", 2, Map.of("Happiness", 3, "Knowledge", 1), 0),
                choice("Say yes even though the answer is no", 1, Map.of("Happiness", -1), 0),
                choice("Trade sleep for one more revision block", 1, Map.of("Knowledge", 1, "Happiness", -2), 0)
        );

        add(events, "main_t14_study_final_preparation", 14, "Study", "Final Preparation",
                "You enter the last pre-exam week knowing perfection is impossible. The goal now is readiness, not fantasy.",
                "stress", List.of(NPCFactory.prof), null,
                choice("Target your weakest areas with focus", 4, Map.of("Knowledge", 8), 0),
                choice("Keep a balanced review schedule", 3, Map.of("Knowledge", 5, "Happiness", 1), 0),
                choice("Bounce between topics and hope momentum appears", 2, Map.of("Knowledge", 2, "Happiness", -1), 0)
        );

        add(events, "main_t14_cca_last_distraction", 14, "attend CCA", "One Last Distraction",
                "A final CCA message appears right when your exam brain is trying to hold itself together.",
                "neutral", List.of(NPCFactory.ccaLead), null,
                choice("Politely decline and protect your prep", 1, Map.of("Knowledge", 1), 0),
                choice("Reply briefly so you do not vanish completely", 1, Map.of("Socialise", 1), 0),
                choice("Overcommit even though you know better", 2, Map.of("Socialise", 2, "Knowledge", -1), 0)
        );

        add(events, "main_t14_social_last_checkin", 14, "Social", "Last Check-In Before Exams",
                "People stop pretending there is plenty of time left. Every conversation feels more sincere.",
                "warm", List.of(NPCFactory.bestFriend), null,
                choice("Check in with someone and wish each other luck", 2, Map.of("Happiness", 2, "Socialise", 2), 0),
                choice("Keep your head down and stay focused", 1, Map.of("Knowledge", 1), 0),
                choice("Spiral together about the exams", 2, Map.of("Socialise", 2, "Happiness", -1), 0)
        );

        add(events, "main_t14_family_hold_the_line", 14, "Family", "Hold the Line",
                "At home, the last quiet push before exams feels strangely serious.",
                "neutral", List.of(NPCFactory.friends), null,
                choice("Let home be calm and protective this week", 2, Map.of("Happiness", 3), 0),
                choice("Stay nearby but remain in revision mode", 1, Map.of("Knowledge", 1), 0),
                choice("Treat the whole week like a pressure cooker", 1, Map.of("Happiness", -1), 0)
        );

        return events;
    }

    private static void add(List<Events> events, String id, int turn, String category, String title,
                            String desc, String mood, List<NPC> npcs,
                            StoryState.ProjectRoute requiredProjectRoute, EventChoice... choices) {
        events.add(new Events(id, "main", turn, Phase.STUDY, category, title, desc, mood, npcs, List.of(choices), requiredProjectRoute));
    }

    private static EventChoice choice(String description, int energy, Map<String, Integer> effects, int money) {
        return new EventChoice(description, energy, effects, money);
    }

    private static EventChoice npcChoice(String description, int energy, Map<String, Integer> effects, int money,
                                         List<NPC> affectedNPCs, NPCAction action) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action);
    }

    private static EventChoice routeChoice(String description, int energy, Map<String, Integer> effects, int money,
                                           StoryState.ProjectRoute route) {
        return new EventChoice(description, energy, effects, money, List.of(), List.of(), null, route);
    }

    private static EventChoice routeNpcChoice(String description, int energy, Map<String, Integer> effects, int money,
                                              List<NPC> affectedNPCs, NPCAction action,
                                              StoryState.ProjectRoute route) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action, route);
    }
}
