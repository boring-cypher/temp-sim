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

        // ── Turn 1 ──────────────────────────────────────────────────────────
        add(events, "main_t01_study_semester_planning", 1, "Study", "Semester Planning",
                "The semester begins. You review your timetable, module outlines, and assessment deadlines.",
                "neutral", List.of(NPCFactory.prof),
                choice("Create a detailed weekly plan", 4, Map.of("Knowledge", 6, "Happiness", 1), 0),
                choice("Skim the outlines and move on", 2, Map.of("Knowledge", 2), 0),
                choice("Ignore planning for now", 1, Map.of("Happiness", 1, "Knowledge", -1), 0)
        );

        add(events, "main_t01_cca_interest_group_briefing", 1, "attend CCA", "Interest Group Briefing",
                "You see announcements for student groups and introductory sessions.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Attend an introductory session", 3, Map.of("Socialise", 3, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Browse online and decide later", 1, Map.of("Happiness", 1), 0),
                choice("Skip CCAs to focus on academics", 2, Map.of("Knowledge", 3), 0)
        );

        add(events, "main_t01_social_new_faces", 1, "Social", "New Faces",
                "Your classmates suggest getting to know one another after the first day.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Join a casual meet-up", 3, Map.of("Socialise", 5, "Happiness", 3), 0, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Introduce yourself briefly and leave", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Keep to yourself for now", 1, Map.of("Happiness", 0, "Socialise", -1), 0)
        );

        add(events, "main_t01_family_a_call_from_home", 1, "Family", "A Call From Home",
                "Your parents ask how the first week is going.",
                "neutral", List.of(NPCFactory.friends),
                choice("Talk properly and share your plans", 2, Map.of("Happiness", 3, "Socialise", 1), 0),
                choice("Keep it short and polite", 1, Map.of("Happiness", 1), 0),
                choice("Delay the call", 1, Map.of("Happiness", -1), 0)
        );

        // ── Turn 2 ──────────────────────────────────────────────────────────
        add(events, "main_t02_study_first_full_lecture_week", 2, "Study", "First Full Lecture Week",
                "Lecture content becomes more structured and weekly tasks begin to appear.",
                "neutral", List.of(NPCFactory.prof),
                choice("Attend lectures and take notes", 4, Map.of("Knowledge", 6), 0),
                choice("Watch recordings at home", 3, Map.of("Knowledge", 4, "Happiness", 1), 0),
                choice("Skip and hope it is fine", 1, Map.of("Knowledge", -2, "Happiness", 1), 0)
        );

        add(events, "main_t02_study_device_upgrade", 2, "Study", "Device Upgrade",
                "Your laptop starts lagging badly during lectures and project work. You consider an upgrade.",
                "neutral", List.of(NPCFactory.friends),
                choice("Buy a new laptop", 2, Map.of("Knowledge", 1, "Happiness", 1), 1500),
                choice("Buy a new phone instead", 1, Map.of("Happiness", 2, "Socialise", 1), 1200),
                choice("Delay the purchase and manage for now", 1, Map.of("Knowledge", -1), 0)
        );

        add(events, "main_t02_cca_recruitment_fair", 2, "CCA", "Recruitment Fair",
                "Student groups run recruitment booths across campus.",
                "social", List.of(NPCFactory.ccaLead),
                npcChoice("Sign up for a CCA", 3, Map.of("Socialise", 5, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                npcChoice("Volunteer to help a booth", 4, Map.of("Socialise", 6, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Skip the fair and study instead", 3, Map.of("Knowledge", 4), 0)
        );

        add(events, "main_t02_social_lunch_invitation", 2, "Social", "Lunch Invitation",
                "A small group from your tutorial class invites you to lunch.",
                "positive", List.of(NPCFactory.grpMate),
                npcChoice("Join and get to know them", 3, Map.of("Socialise", 5, "Happiness", 2), 12, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Join briefly, then leave to study", 2, Map.of("Socialise", 3, "Happiness", 1), 8),
                choice("Decline to focus on work", 1, Map.of("Knowledge", 1, "Socialise", -1), 0)
        );

        add(events, "main_t02_family_household_request", 2, "Family", "Household Request",
                "Your family asks if you can help with a small errand this weekend.",
                "neutral", List.of(NPCFactory.friends),
                choice("Help out", 3, Map.of("Happiness", 2, "Socialise", 1), 0),
                choice("Explain you are busy and reschedule", 1, Map.of("Happiness", 0), 0),
                choice("Refuse bluntly", 1, Map.of("Happiness", -2), 0)
        );

        // ── Turn 3 ──────────────────────────────────────────────────────────
        add(events, "main_t03_study_tutorial_preparation", 3, "Study", "Tutorial Preparation",
                "Your tutorial questions are challenging, and participation matters.",
                "neutral", List.of(NPCFactory.prof),
                choice("Prepare thoroughly", 4, Map.of("Knowledge", 7, "Happiness", 1), 0),
                choice("Do the minimum", 2, Map.of("Knowledge", 3), 0),
                choice("Go unprepared", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
        );

        add(events, "main_t03_cca_first_training_session", 3, "CCA", "First Training Session",
                "Your CCA schedules its first proper training session of the semester.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Attend training", 4, Map.of("Socialise", 4, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Attend but leave early", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip to study", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
        );

        add(events, "main_t03_social_study_group_offer", 3, "Social", "Study Group Offer",
                "A classmate suggests forming a regular study group.",
                "positive", List.of(NPCFactory.grpMate),
                npcChoice("Join the study group", 3, Map.of("Knowledge", 3, "Socialise", 3), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Try it once", 2, Map.of("Knowledge", 2, "Socialise", 1), 0),
                choice("Decline and study alone", 2, Map.of("Knowledge", 3), 0)
        );

        add(events, "main_t03_family_family_dinner", 3, "Family", "Family Dinner",
                "Your family plans a dinner and hopes you can attend.",
                "positive", List.of(NPCFactory.friends),
                choice("Attend and be present", 3, Map.of("Happiness", 3, "Socialise", 1), 0),
                choice("Attend but leave early", 2, Map.of("Happiness", 2), 0),
                choice("Skip due to workload", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
        );

        // ── Turn 4 ──────────────────────────────────────────────────────────
        add(events, "main_t04_study_first_graded_assignment", 4, "Study", "First Graded Assignment",
                "Your first graded assignment is released and the requirements are strict.",
                "stress", List.of(NPCFactory.prof),
                choice("Start early and outline your approach", 5, Map.of("Knowledge", 8, "Happiness", 1), 0),
                choice("Wait a few days", 1, Map.of("Happiness", 1, "Knowledge", -1), 0),
                npcChoice("Ask questions during consultation", 4, Map.of("Knowledge", 6, "Happiness", 1), 0, List.of(NPCFactory.prof), NPCAction.BOOK_CONSULT)
        );

        add(events, "main_t04_cca_planning_meeting", 4, "CCA", "Planning Meeting",
                "Your CCA committee plans upcoming activities and needs volunteers.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Volunteer for a role", 4, Map.of("Socialise", 4, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Attend but do not commit", 2, Map.of("Socialise", 2), 0),
                choice("Skip the meeting", 1, Map.of("Socialise", -1), 0)
        );

        add(events, "main_t04_social_movie_night_invite", 4, "Social", "Movie Night Invite",
                "Friends invite you for a movie night to unwind.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Go for movie night", 4, Map.of("Happiness", 6, "Socialise", 4), 18, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Join briefly, then leave", 2, Map.of("Happiness", 3, "Socialise", 2), 10),
                choice("Decline and work on the assignment", 3, Map.of("Knowledge", 4, "Happiness", -1), 0)
        );

        add(events, "main_t04_family_a_practical_reminder", 4, "Family", "A Practical Reminder",
                "Your parents remind you to manage your budget and responsibilities.",
                "neutral", List.of(NPCFactory.friends),
                choice("Listen and reflect", 1, Map.of("Happiness", 1), 0),
                choice("Brush it off", 1, Map.of("Happiness", -1), 0),
                choice("Discuss your semester plan", 2, Map.of("Happiness", 2, "Knowledge", 1), 0)
        );

        // ── Turn 5 ──────────────────────────────────────────────────────────
        add(events, "main_t05_study_group_project_assigned", 5, "Study", "Group Project Assigned",
                "You are assigned a group project with classmates you barely know.",
                "neutral", List.of(NPCFactory.grpMate),
                npcChoice("Set up a clear plan and roles", 4, Map.of("Knowledge", 5, "Socialise", 2), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Wait for someone else to lead", 1, Map.of("Happiness", 1, "Knowledge", -1), 0),
                npcChoice("Propose a meeting and agenda", 3, Map.of("Knowledge", 3, "Socialise", 3), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT)
        );

        add(events, "main_t05_cca_skills_workshop", 5, "CCA", "Skills Workshop",
                "Your CCA offers a workshop to improve skills relevant to upcoming activities.",
                "positive", List.of(NPCFactory.ccaLead),
                npcChoice("Attend the workshop", 3, Map.of("Happiness", 2, "Socialise", 3), 5, List.of(NPCFactory.ccaLead), NPCAction.ASK_TRAINING_TIPS),
                npcChoice("Attend and network actively", 4, Map.of("Happiness", 2, "Socialise", 5), 5, List.of(NPCFactory.ccaLead), NPCAction.ASK_TRAINING_TIPS),
                choice("Skip and focus on the project", 3, Map.of("Knowledge", 4), 0)
        );

        add(events, "main_t05_social_partner_check_in", 5, "Social", "Partner Check-In",
                "Your partner asks if you can spend some time together this week.",
                "neutral", List.of(NPCFactory.friends),
                choice("Plan a simple date", 4, Map.of("Happiness", 6, "Socialise", 3), 40),
                choice("Suggest a short meet-up", 2, Map.of("Happiness", 3, "Socialise", 2), 40),
                choice("Postpone due to workload", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t05_family_home_responsibilities", 5, "Family", "Home Responsibilities",
                "A small household responsibility comes up during the week.",
                "neutral", List.of(NPCFactory.friends),
                choice("Take care of it", 3, Map.of("Happiness", 2), 0),
                choice("Ask for time and do it later", 1, Map.of("Happiness", 0), 0),
                choice("Avoid it", 1, Map.of("Happiness", -2), 0)
        );

        // ── Turn 6 ──────────────────────────────────────────────────────────
        add(events, "main_t06_study_quiz_announcement", 6, "Study", "Quiz Announcement",
                "A quiz is announced and it will test content from the last few weeks.",
                "stress", List.of(NPCFactory.prof),
                choice("Review consistently each day", 4, Map.of("Knowledge", 7), 0),
                choice("Cram the night before", 3, Map.of("Knowledge", 5, "Happiness", -1), 0),
                choice("Hope for the best", 1, Map.of("Knowledge", -3, "Happiness", 1), 0)
        );

        add(events, "main_t06_cca_training_intensity_increases", 6, "CCA", "Training Intensity Increases",
                "Training becomes more intense as your CCA prepares for upcoming activities.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Attend training and commit", 5, Map.of("Socialise", 5, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Attend lightly", 3, Map.of("Socialise", 3, "Happiness", 1), 0),
                choice("Skip to protect your study time", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
        );

        add(events, "main_t06_social_friends_need_support", 6, "Social", "Friends Need Support",
                "A friend is stressed and asks if you can talk.",
                "neutral", List.of(NPCFactory.bestFriend),
                npcChoice("Make time and support them", 3, Map.of("Socialise", 4, "Happiness", 2), 0, List.of(NPCFactory.bestFriend), NPCAction.VENT),
                choice("Send a thoughtful message", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Decline due to workload", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
        );

        add(events, "main_t06_family_family_expectations", 6, "Family", "Family Expectations",
                "Your parents ask about your progress and grades so far.",
                "neutral", List.of(NPCFactory.friends),
                choice("Share your study plan", 2, Map.of("Happiness", 2), 0),
                choice("Give a short update", 1, Map.of("Happiness", 1), 40),
                choice("Avoid the conversation", 1, Map.of("Happiness", -2), 0)
        );

        // ── Turn 7 ──────────────────────────────────────────────────────────
        add(events, "main_t07_study_recess_week_catch_up", 7, "Study", "Recess Week Catch-Up",
                "There are no scheduled classes, but deadlines remain and revisions can pay off.",
                "neutral", List.of(),
                choice("Catch up on all modules", 5, Map.of("Knowledge", 9, "Happiness", -1), 0),
                choice("Catch up on the most difficult module", 4, Map.of("Knowledge", 7), 0),
                choice("Do only light revision", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
        );

        add(events, "main_t07_cca_cca_planning_during_recess", 7, "CCA", "CCA Planning During Recess",
                "Your CCA asks for help planning upcoming events during recess week.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Help with planning", 3, Map.of("Socialise", 4, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Help briefly", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Decline and focus on revision", 3, Map.of("Knowledge", 4), 0)
        );

        add(events, "main_t07_social_a_calm_afternoon", 7, "Social", "A Calm Afternoon",
                "Friends suggest a simple outing during recess week to recharge.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Go out and fully disconnect", 4, Map.of("Happiness", 6, "Socialise", 3), 30, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Join briefly, then return to work", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                choice("Stay in and revise", 3, Map.of("Knowledge", 4, "Happiness", -1), 0)
        );

        add(events, "main_t07_family_weekend_at_home", 7, "Family", "Weekend at Home",
                "You spend more time at home during recess week.",
                "positive", List.of(NPCFactory.friends),
                choice("Spend time with family", 3, Map.of("Happiness", 4, "Socialise", 1), 0),
                choice("Keep to yourself and study", 3, Map.of("Knowledge", 4), 0),
                choice("Try to balance both", 4, Map.of("Knowledge", 2, "Happiness", 2), 0)
        );

        // ── Turn 8 ──────────────────────────────────────────────────────────
        add(events, "main_t08_study_second_half_begins", 8, "Study", "Second Half Begins",
                "Classes resume and the pace increases after recess week.",
                "neutral", List.of(NPCFactory.prof),
                choice("Stay consistent and revise weekly", 4, Map.of("Knowledge", 7), 0),
                choice("Focus only on urgent tasks", 3, Map.of("Knowledge", 5), 0),
                choice("Struggle to restart your routine", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
        );

        add(events, "main_t08_cca_event_preparation", 8, "CCA", "Event Preparation",
                "Your CCA begins preparing for an event and needs volunteers.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Volunteer and contribute", 4, Map.of("Socialise", 4, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Help only with small tasks", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip to protect your study time", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
        );

        add(events, "main_t08_social_group_project_meeting", 8, "Social", "Group Project Meeting",
                "Your group schedules a meeting to align progress and responsibilities.",
                "neutral", List.of(NPCFactory.grpMate),
                npcChoice("Attend and contribute actively", 3, Map.of("Knowledge", 2, "Socialise", 3), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Attend but stay quiet", 2, Map.of("Socialise", 1), 0),
                choice("Skip and send updates later", 1, Map.of("Socialise", -2), 0)
        );

        add(events, "main_t08_family_unexpected_errand", 8, "Family", "Unexpected Errand",
                "A family matter comes up and you are asked to help.",
                "neutral", List.of(NPCFactory.friends),
                choice("Help immediately", 3, Map.of("Happiness", 2), 0),
                choice("Help later after finishing tasks", 2, Map.of("Happiness", 1, "Knowledge", 1), 0),
                choice("Refuse due to stress", 1, Map.of("Happiness", -2), 0)
        );

        // ── Turn 9 ──────────────────────────────────────────────────────────
        add(events, "main_t09_study_continuous_assessments", 9, "Study", "Continuous Assessments",
                "Quizzes, labs, and submissions appear across multiple modules this week.",
                "stress", List.of(NPCFactory.prof),
                choice("Work steadily across all modules", 5, Map.of("Knowledge", 9, "Happiness", -1), 0),
                choice("Prioritise the most difficult module", 4, Map.of("Knowledge", 7), 0),
                choice("Do the minimum required", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
        );

        add(events, "main_t09_cca_committee_deadline", 9, "CCA", "Committee Deadline",
                "Your CCA committee has a planning deadline and needs last-minute help.",
                "stress", List.of(NPCFactory.ccaLead),
                npcChoice("Help and meet the deadline", 4, Map.of("Socialise", 4, "Happiness", 1), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Help briefly and leave", 2, Map.of("Socialise", 2), 0),
                choice("Decline to focus on assessments", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
        );

        add(events, "main_t09_social_tension_in_the_group", 9, "Social", "Project Breaking Point",
                "A groupmate complains that the project is slipping and asks how the team should move forward.",
                "stress", List.of(NPCFactory.grpMate),
                routeChoice("Carry the extra work quietly and keep the project alive", 4, Map.of("Knowledge", 3, "Happiness", -2), 0, List.of(NPCFactory.grpMate), NPCAction.WORK_SPRINT, StoryState.ProjectRoute.CARRY),
                routeChoice("Call for a serious regroup and repair the workflow", 3, Map.of("Socialise", 3, "Happiness", 1), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT, StoryState.ProjectRoute.REPAIR),
                routeChoice("Document the issue and escalate it professionally", 2, Map.of("Knowledge", 1, "Socialise", -2), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT, StoryState.ProjectRoute.ESCALATE)
        );

        add(events, "main_t09_family_supportive_message", 9, "Family", "Supportive Message",
                "A family member sends an encouraging message about staying consistent.",
                "positive", List.of(NPCFactory.friends),
                choice("Reply with appreciation", 1, Map.of("Happiness", 2), 0),
                choice("Read and continue working", 1, Map.of("Happiness", 1), 0),
                choice("Ignore it due to stress", 1, Map.of("Happiness", -1), 0)
        );

        // ── Turn 10 ─────────────────────────────────────────────────────────
        addWithRoute(events, "main_t10_study_project_crunch", 10, "Study", "Project Crunch",
                "Your project deadline is approaching and the workload spikes.",
                "stress", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.UNSET,
                npcChoice("Organise tasks and execute quickly", 5, Map.of("Knowledge", 8, "Socialise", 1), 0, List.of(NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Work on your portion only", 4, Map.of("Knowledge", 6), 0),
                choice("Delay and hope for a miracle", 1, Map.of("Knowledge", -3, "Happiness", -2), 0)
        );

        addWithRoute(events, "main_t10_study_project_crunch_carry", 10, "Study", "Late-Night Fixes",
                "You quietly take on extra tasks and patch weak sections of the project so the group can keep moving.",
                "stress", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.CARRY,
                npcChoice("Keep carrying the weak parts and refine the slides", 5, Map.of("Knowledge", 9, "Happiness", -3), 0, List.of(NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Cover only the most urgent gaps", 4, Map.of("Knowledge", 6, "Happiness", -1), 0),
                choice("Admit you cannot keep carrying everything", 2, Map.of("Happiness", 1, "Socialise", -1), 0)
        );

        addWithRoute(events, "main_t10_study_project_crunch_repair", 10, "Study", "Resetting the Team",
                "After the difficult conversation, the group meets again to redistribute tasks and repair the workflow.",
                "neutral", List.of(NPCFactory.grpMate), StoryState.ProjectRoute.REPAIR,
                npcChoice("Lead the reset and clarify every task", 4, Map.of("Knowledge", 7, "Socialise", 2), 0, List.of(NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Focus only on your own task after the regroup", 3, Map.of("Knowledge", 5), 0),
                choice("Let the meeting drift and hope people self-correct", 1, Map.of("Socialise", -2, "Knowledge", -1), 0)
        );

        addWithRoute(events, "main_t10_study_project_crunch_escalate", 10, "Study", "Formal Escalation",
                "You bring the project concerns up properly so expectations, accountability, and deadlines are made explicit.",
                "tense", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.ESCALATE,
                npcChoice("Present the issue clearly and propose a recovery plan", 4, Map.of("Knowledge", 7, "Socialise", -1), 0, List.of(NPCFactory.prof, NPCFactory.grpMate), NPCAction.DISCUSS_TASK_SPLIT),
                choice("Keep the escalation brief and professional", 3, Map.of("Knowledge", 5), 0),
                choice("Back off and avoid making it official", 2, Map.of("Happiness", -1, "Socialise", 1), 0)
        );

        add(events, "main_t10_cca_competition_preparation", 10, "CCA", "Competition Preparation",
                "Your CCA prepares for a performance or competition and needs full attendance.",
                "stress", List.of(NPCFactory.ccaLead),
                npcChoice("Commit fully", 5, Map.of("Socialise", 5, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Help briefly", 3, Map.of("Socialise", 3, "Happiness", 1), 0),
                choice("Skip to protect your project time", 4, Map.of("Knowledge", 5, "Socialise", -2), 0)
        );

        add(events, "main_t10_social_a_quick_meal_with_friends", 10, "Social", "A Quick Meal With Friends",
                "Friends suggest a quick meal to stay connected during a stressful week.",
                "neutral", List.of(NPCFactory.bestFriend),
                npcChoice("Join and relax briefly", 2, Map.of("Socialise", 3, "Happiness", 2), 8, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Join but discuss project progress", 2, Map.of("Socialise", 2, "Knowledge", 1), 12),
                choice("Decline and keep working", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
        );

        add(events, "main_t10_family_check_in_during_stress", 10, "Family", "Check-In During Stress",
                "Your parents notice you sound stressed and ask if you are coping well.",
                "neutral", List.of(NPCFactory.friends),
                choice("Talk openly for a while", 2, Map.of("Happiness", 3), 0),
                choice("Reassure them briefly", 1, Map.of("Happiness", 1), 0),
                choice("Avoid the conversation", 1, Map.of("Happiness", -2), 0)
        );

        // ── Turn 11 ─────────────────────────────────────────────────────────
        addWithRoute(events, "main_t11_study_presentation_week", 11, "Study", "Presentation Week",
                "Your group must present your project and answer questions confidently.",
                "stress", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.UNSET,
                npcChoice("Rehearse thoroughly", 4, Map.of("Knowledge", 6, "Happiness", 1), 0, List.of(NPCFactory.prof, NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Prepare key points only", 3, Map.of("Knowledge", 4), 0),
                choice("Hope it goes well", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
        );

        addWithRoute(events, "main_t11_study_presentation_week_carry", 11, "Study", "Holding It Together",
                "Because you have been quietly carrying the project, presentation week feels like one long attempt to keep everything from falling apart.",
                "stress", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.CARRY,
                npcChoice("Do one final full polish before presenting", 4, Map.of("Knowledge", 7, "Happiness", -2), 0, List.of(NPCFactory.prof, NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Simplify the presentation so it is safe and clean", 3, Map.of("Knowledge", 5), 0),
                choice("Let others take more responsibility now", 2, Map.of("Happiness", 1, "Socialise", -1), 0)
        );

        addWithRoute(events, "main_t11_study_presentation_week_repair", 11, "Study", "A Repaired Team",
                "The regroup has made the team steadier, and presentation week becomes a test of whether that recovery will hold.",
                "neutral", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.REPAIR,
                npcChoice("Run one focused rehearsal and trust the process", 4, Map.of("Knowledge", 6, "Socialise", 2), 0, List.of(NPCFactory.prof, NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Tighten only the weak sections", 3, Map.of("Knowledge", 5), 0),
                choice("Over-discuss and waste time on tiny issues", 2, Map.of("Knowledge", -1, "Happiness", -1), 0)
        );

        addWithRoute(events, "main_t11_study_presentation_week_escalate", 11, "Study", "Professional Distance",
                "After escalating the issue, the team is more formal and tense, but expectations are finally clear going into the presentation.",
                "tense", List.of(NPCFactory.prof, NPCFactory.grpMate), StoryState.ProjectRoute.ESCALATE,
                npcChoice("Present confidently and keep things professional", 4, Map.of("Knowledge", 7), 0, List.of(NPCFactory.prof, NPCFactory.grpMate), NPCAction.WORK_SPRINT),
                choice("Stick only to your assigned speaking points", 3, Map.of("Knowledge", 5, "Socialise", -1), 0),
                choice("Second-guess the decision and lose focus", 2, Map.of("Happiness", -2), 0)
        );

        add(events, "main_t11_cca_cca_event_day", 11, "CCA", "CCA Event Day",
                "Your CCA runs an event and needs members to support operations.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Support the event", 4, Map.of("Socialise", 4, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Support briefly", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip due to presentation prep", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
        );

        add(events, "main_t11_social_celebration_suggestion", 11, "Social", "Celebration Suggestion",
                "After the presentation, friends suggest a small celebration.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Join and celebrate", 3, Map.of("Happiness", 5, "Socialise", 3), 0, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Join briefly, then leave", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                choice("Skip and rest", 1, Map.of("Happiness", 1), 0)
        );

        add(events, "main_t11_family_sharing_progress", 11, "Family", "Sharing Progress",
                "Your family asks about your project and how things are going.",
                "positive", List.of(NPCFactory.friends),
                choice("Share your progress and challenges", 2, Map.of("Happiness", 2), 0),
                choice("Give a short update", 1, Map.of("Happiness", 1), 40),
                choice("Avoid talking about school", 1, Map.of("Happiness", -1), 0)
        );

        // ── Turn 12 ─────────────────────────────────────────────────────────
        add(events, "main_t12_study_final_assignments_begin", 12, "Study", "Final Assignments Begin",
                "With the project phase behind you, multiple modules now announce final submissions and remaining assessments.",
                "stress", List.of(NPCFactory.prof),
                choice("Work systematically and submit early", 5, Map.of("Knowledge", 9), 0),
                choice("Focus on one submission at a time", 4, Map.of("Knowledge", 7), 0),
                choice("Delay until the deadline feels close", 1, Map.of("Knowledge", -3, "Happiness", -2), 0)
        );

        add(events, "main_t12_cca_reduced_cca_activity", 12, "CCA", "Reduced CCA Activity",
                "CCA attendance drops as finals approach. Leaders ask members to remain engaged.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Attend one final session", 3, Map.of("Socialise", 3, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.JOIN_EVENT),
                choice("Help briefly and leave", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                choice("Skip to focus on assignments", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
        );

        add(events, "main_t12_social_partner_wants_time", 12, "Social", "Partner Wants Time",
                "Your partner asks whether you can spend time together before finals begin.",
                "neutral", List.of(NPCFactory.friends),
                choice("Plan a short date", 3, Map.of("Happiness", 5, "Socialise", 2), 40),
                choice("Suggest a brief meet-up", 2, Map.of("Happiness", 3, "Socialise", 1), 40),
                choice("Postpone due to workload", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t12_family_practical_support", 12, "Family", "Practical Support",
                "Your family offers practical support such as meals or quiet space to study.",
                "positive", List.of(NPCFactory.friends),
                choice("Accept and feel supported", 1, Map.of("Happiness", 3), 0),
                choice("Thank them and continue working", 1, Map.of("Happiness", 2, "Knowledge", 1), 0),
                choice("Decline and keep things as usual", 1, Map.of("Happiness", 0), 0)
        );

        // ── Turn 13 ─────────────────────────────────────────────────────────
        add(events, "main_t13_study_revision_week", 13, "Study", "Revision Week",
                "Teaching ends and revision becomes the main focus. You choose how structured to be.",
                "neutral", List.of(),
                choice("Follow a structured revision plan", 5, Map.of("Knowledge", 10, "Happiness", -1), 0),
                choice("Revise selectively based on weaknesses", 4, Map.of("Knowledge", 7), 0),
                choice("Do light revision only", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
        );

        add(events, "main_t13_cca_cca_winds_down", 13, "CCA", "CCA Winds Down",
                "Most CCAs reduce activities during revision week, but some members still meet casually.",
                "neutral", List.of(NPCFactory.ccaLead),
                npcChoice("Attend a short casual meet-up", 2, Map.of("Socialise", 2, "Happiness", 2), 0, List.of(NPCFactory.ccaLead), NPCAction.HANG_OUT),
                choice("Skip and focus on revision", 3, Map.of("Knowledge", 4), 0),
                choice("Send support messages instead", 1, Map.of("Socialise", 1, "Happiness", 1), 0)
        );

        add(events, "main_t13_social_quiet_support_from_friends", 13, "Social", "Quiet Support From Friends",
                "Friends suggest studying in the same space for quiet mutual support.",
                "positive", List.of(NPCFactory.bestFriend),
                npcChoice("Join a quiet study session", 3, Map.of("Knowledge", 2, "Socialise", 2, "Happiness", 1), 0, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Meet briefly for encouragement", 2, Map.of("Happiness", 2, "Socialise", 2), 0),
                choice("Decline and revise alone", 2, Map.of("Knowledge", 3), 0)
        );

        add(events, "main_t13_family_household_calm", 13, "Family", "Household Calm",
                "Your family tries to keep the home environment quiet to help you concentrate.",
                "positive", List.of(NPCFactory.friends),
                choice("Express appreciation", 1, Map.of("Happiness", 2), 0),
                choice("Use the quiet time to revise", 2, Map.of("Knowledge", 2, "Happiness", 1), 0),
                choice("Feel pressured by expectations", 1, Map.of("Happiness", -2), 0)
        );

        // ── Turn 14 ─────────────────────────────────────────────────────────
        add(events, "main_t14_study_final_preparation", 14, "Study", "Final Preparation",
                "Exams are about to begin. You choose how to spend your final preparation time.",
                "climax", List.of(),
                choice("Do a full timed practice under exam conditions", 5, Map.of("Knowledge", 9, "Happiness", -1), 0),
                choice("Review summaries and key concepts", 4, Map.of("Knowledge", 7), 0),
                choice("Do minimal review to stay calm", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
        );

        add(events, "main_t14_cca_cca_pause", 14, "CCA", "CCA Pause",
                "Your CCA leadership encourages everyone to focus on exams and pause commitments.",
                "neutral", List.of(NPCFactory.ccaLead),
                choice("Acknowledge and step back responsibly", 1, Map.of("Happiness", 1), 0),
                choice("Send encouraging messages to members", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                choice("Feel guilty about reduced involvement", 1, Map.of("Happiness", -1), 0)
        );

        add(events, "main_t14_social_last_minute_invitation", 14, "Social", "Last-Minute Invitation",
                "Friends invite you for a short break to clear your mind before exams.",
                "neutral", List.of(NPCFactory.bestFriend),
                npcChoice("Take a short break with friends", 2, Map.of("Happiness", 3, "Socialise", 2), 0, List.of(NPCFactory.bestFriend), NPCAction.HANG_OUT),
                choice("Decline and keep reviewing", 1, Map.of("Knowledge", 1, "Happiness", -1), 0),
                choice("Meet briefly, then return to revise", 2, Map.of("Happiness", 2, "Knowledge", 1), 0)
        );

        add(events, "main_t14_family_final_encouragement", 14, "Family", "Final Encouragement",
                "Your family wishes you well and reminds you to take care of yourself during exams.",
                "positive", List.of(NPCFactory.friends),
                choice("Thank them and feel supported", 1, Map.of("Happiness", 3), 0),
                choice("Keep it short and return to work", 1, Map.of("Happiness", 1, "Knowledge", 1), 0),
                choice("Feel stressed by expectations", 1, Map.of("Happiness", -2), 0)
        );

        return events;
    }

    private static void add(List<Events> events, String id, int turn, String category,
                            String title, String desc, String mood, List<NPC> npcs, EventChoice... choices) {
        events.add(new Events(id, "main", turn, Phase.STUDY, category, title, desc, mood, npcs,
                new ArrayList<>(List.of(choices)), null));
    }

    private static void addWithRoute(List<Events> events, String id, int turn, String category,
                                     String title, String desc, String mood, List<NPC> npcs,
                                     StoryState.ProjectRoute route, EventChoice... choices) {
        events.add(new Events(id, "main", turn, Phase.STUDY, category, title, desc, mood, npcs,
                new ArrayList<>(List.of(choices)), route));
    }

    private static EventChoice choice(String description, int energy, Map<String, Integer> effects, int money) {
        return new EventChoice(description, energy, effects, money);
    }

    private static EventChoice npcChoice(String description, int energy, Map<String, Integer> effects, int money,
                                         List<NPC> affectedNPCs, NPCAction action) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action);
    }

    private static EventChoice routeChoice(String description, int energy, Map<String, Integer> effects, int money,
                                           List<NPC> affectedNPCs, NPCAction action, StoryState.ProjectRoute route) {
        return new EventChoice(description, energy, effects, money, List.of(), affectedNPCs, action, route);
    }
}