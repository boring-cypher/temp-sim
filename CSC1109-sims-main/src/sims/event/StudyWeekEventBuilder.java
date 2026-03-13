package sims.event;

import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudyWeekEventBuilder {

    public static List<Events> buildStudyEvents() {
        List<Events> events = new ArrayList<>();

        events.add(new Events(
                "main_t01_study_semester_planning",
                "main",
                1,
                Phase.STUDY,
                "Study",
                "Semester Planning",
                "The semester begins. You review your timetable, module outlines, and assessment deadlines.",
                "neutral",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Create a detailed weekly plan", 4, Map.of("Knowledge", 6, "Happiness", 1), 0),
                        new EventChoice("Skim the outlines and move on", 2, Map.of("Knowledge", 2), 0),
                        new EventChoice("Ignore planning for now", 1, Map.of("Happiness", 1, "Knowledge", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t01_cca_interest_group_briefing",
                "main",
                1,
                Phase.STUDY,
                "attend CCA",
                "Interest Group Briefing",
                "You see announcements for student groups and introductory sessions.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Attend an introductory session",
                                3,
                                Map.of("Socialise", 3, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Browse online and decide later", 1, Map.of("Happiness", 1), 0),
                        new EventChoice("Skip CCAs to focus on academics", 2, Map.of("Knowledge", 3), 0)
                )
        ));

        events.add(new Events(
                "main_t01_social_new_faces",
                "main",
                1,
                Phase.STUDY,
                "Social",
                "New Faces",
                "Your classmates suggest getting to know one another after the first day.",
                "positive",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Join a casual meet-up",
                                3,
                                Map.of("Socialise", 5, "Happiness", 3),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Introduce yourself briefly and leave", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                        new EventChoice("Keep to yourself for now", 1, Map.of("Happiness", 0, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t01_family_a_call_from_home",
                "main",
                1,
                Phase.STUDY,
                "Family",
                "A Call From Home",
                "Your parents ask how the first week is going.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Talk properly and share your plans", 2, Map.of("Happiness", 3, "Socialise", 1), 0),
                        new EventChoice("Keep it short and polite", 1, Map.of("Happiness", 1), 0),
                        new EventChoice("Delay the call", 1, Map.of("Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t02_study_first_full_lecture_week",
                "main",
                2,
                Phase.STUDY,
                "Study",
                "First Full Lecture Week",
                "Lecture content becomes more structured and weekly tasks begin to appear.",
                "neutral",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Attend lectures and take notes", 4, Map.of("Knowledge", 6), 0),
                        new EventChoice("Watch recordings at home", 3, Map.of("Knowledge", 4, "Happiness", 1), 0),
                        new EventChoice("Skip and hope it is fine", 1, Map.of("Knowledge", -2, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t02_study_device_upgrade",
                "main",
                2,
                Phase.STUDY,
                "Study",
                "Device Upgrade",
                "Your laptop starts lagging badly during lectures and project work. You consider an upgrade.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Buy a new laptop", 2, Map.of("Knowledge", 1, "Happiness", 1), 1500),
                        new EventChoice("Buy a new phone instead", 1, Map.of("Happiness", 2, "Socialise", 1), 1200),
                        new EventChoice("Delay the purchase and manage for now", 1, Map.of("Knowledge", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t02_cca_recruitment_fair",
                "main",
                2,
                Phase.STUDY,
                "CCA",
                "Recruitment Fair",
                "Student groups run recruitment booths across campus.",
                "social",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Sign up for a CCA",
                                3,
                                Map.of("Socialise", 5, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice(
                                "Volunteer to help a booth",
                                4,
                                Map.of("Socialise", 6, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Skip the fair and study instead", 3, Map.of("Knowledge", 4), 0)
                )
        ));

        events.add(new Events(
                "main_t02_social_lunch_invitation",
                "main",
                2,
                Phase.STUDY,
                "Social",
                "Lunch Invitation",
                "A small group from your tutorial class invites you to lunch.",
                "positive",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Join and get to know them",
                                3,
                                Map.of("Socialise", 5, "Happiness", 2),
                                12,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        ),
                        new EventChoice("Join briefly, then leave to study", 2, Map.of("Socialise", 3, "Happiness", 1), 8),
                        new EventChoice("Decline to focus on work", 1, Map.of("Knowledge", 1, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t02_family_household_request",
                "main",
                2,
                Phase.STUDY,
                "Family",
                "Household Request",
                "Your family asks if you can help with a small errand this weekend.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Help out", 3, Map.of("Happiness", 2, "Socialise", 1), 0),
                        new EventChoice("Explain you are busy and reschedule", 1, Map.of("Happiness", 0), 0),
                        new EventChoice("Refuse bluntly", 1, Map.of("Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t03_study_tutorial_preparation",
                "main",
                3,
                Phase.STUDY,
                "Study",
                "Tutorial Preparation",
                "Your tutorial questions are challenging, and participation matters.",
                "neutral",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Prepare thoroughly", 4, Map.of("Knowledge", 7, "Happiness", 1), 0),
                        new EventChoice("Do the minimum", 2, Map.of("Knowledge", 3), 0),
                        new EventChoice("Go unprepared", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t03_cca_first_training_session",
                "main",
                3,
                Phase.STUDY,
                "CCA",
                "First Training Session",
                "Your CCA schedules its first proper training session of the semester.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Attend training",
                                4,
                                Map.of("Socialise", 4, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Attend but leave early", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                        new EventChoice("Skip to study", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t03_social_study_group_offer",
                "main",
                3,
                Phase.STUDY,
                "Social",
                "Study Group Offer",
                "A classmate suggests forming a regular study group.",
                "positive",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Join the study group",
                                3,
                                Map.of("Knowledge", 3, "Socialise", 3),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        ),
                        new EventChoice("Try it once", 2, Map.of("Knowledge", 2, "Socialise", 1), 0),
                        new EventChoice("Decline and study alone", 2, Map.of("Knowledge", 3), 0)
                )
        ));

        events.add(new Events(
                "main_t03_family_family_dinner",
                "main",
                3,
                Phase.STUDY,
                "Family",
                "Family Dinner",
                "Your family plans a dinner and hopes you can attend.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Attend and be present", 3, Map.of("Happiness", 3, "Socialise", 1), 0),
                        new EventChoice("Attend but leave early", 2, Map.of("Happiness", 2), 0),
                        new EventChoice("Skip due to workload", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t04_study_first_graded_assignment",
                "main",
                4,
                Phase.STUDY,
                "Study",
                "First Graded Assignment",
                "Your first graded assignment is released and the requirements are strict.",
                "stress",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Start early and outline your approach", 5, Map.of("Knowledge", 8, "Happiness", 1), 0),
                        new EventChoice("Wait a few days", 1, Map.of("Happiness", 1, "Knowledge", -1), 0),
                        new EventChoice(
                                "Ask questions during consultation",
                                4,
                                Map.of("Knowledge", 6, "Happiness", 1),
                                0,
                                List.of(),
                                List.of(NPCFactory.prof),
                                NPCAction.BOOK_CONSULT
                        )
                )
        ));

        events.add(new Events(
                "main_t04_cca_planning_meeting",
                "main",
                4,
                Phase.STUDY,
                "CCA",
                "Planning Meeting",
                "Your CCA committee plans upcoming activities and needs volunteers.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Volunteer for a role",
                                4,
                                Map.of("Socialise", 4, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Attend but do not commit", 2, Map.of("Socialise", 2), 0),
                        new EventChoice("Skip the meeting", 1, Map.of("Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t04_social_movie_night_invite",
                "main",
                4,
                Phase.STUDY,
                "Social",
                "Movie Night Invite",
                "Friends invite you for a movie night to unwind.",
                "positive",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Go for movie night",
                                4,
                                Map.of("Happiness", 6, "Socialise", 4),
                                18,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Join briefly, then leave", 2, Map.of("Happiness", 3, "Socialise", 2), 10),
                        new EventChoice("Decline and work on the assignment", 3, Map.of("Knowledge", 4, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t04_family_a_practical_reminder",
                "main",
                4,
                Phase.STUDY,
                "Family",
                "A Practical Reminder",
                "Your parents remind you to manage your budget and responsibilities.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Listen and reflect", 1, Map.of("Happiness", 1), 0),
                        new EventChoice("Brush it off", 1, Map.of("Happiness", -1), 0),
                        new EventChoice("Discuss your semester plan", 2, Map.of("Happiness", 2, "Knowledge", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t05_study_group_project_assigned",
                "main",
                5,
                Phase.STUDY,
                "Study",
                "Group Project Assigned",
                "You are assigned a group project with classmates you barely know.",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Set up a clear plan and roles",
                                4,
                                Map.of("Knowledge", 5, "Socialise", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        ),
                        new EventChoice("Wait for someone else to lead", 1, Map.of("Happiness", 1, "Knowledge", -1), 0),
                        new EventChoice(
                                "Propose a meeting and agenda",
                                3,
                                Map.of("Knowledge", 3, "Socialise", 3),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        )
                )
        ));

        events.add(new Events(
                "main_t05_cca_skills_workshop",
                "main",
                5,
                Phase.STUDY,
                "CCA",
                "Skills Workshop",
                "Your CCA offers a workshop to improve skills relevant to upcoming activities.",
                "positive",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Attend the workshop",
                                3,
                                Map.of("Happiness", 2, "Socialise", 3),
                                5,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.ASK_TRAINING_TIPS
                        ),
                        new EventChoice(
                                "Attend and network actively",
                                4,
                                Map.of("Happiness", 2, "Socialise", 5),
                                5,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.ASK_TRAINING_TIPS
                        ),
                        new EventChoice("Skip and focus on the project", 3, Map.of("Knowledge", 4), 0)
                )
        ));

        events.add(new Events(
                "main_t05_social_partner_check_in",
                "main",
                5,
                Phase.STUDY,
                "Social",
                "Partner Check-In",
                "Your partner asks if you can spend some time together this week.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Plan a simple date", 4, Map.of("Happiness", 6, "Socialise", 3), 40),
                        new EventChoice("Suggest a short meet-up", 2, Map.of("Happiness", 3, "Socialise", 2), 40),
                        new EventChoice("Postpone due to workload", 1, Map.of("Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t05_family_home_responsibilities",
                "main",
                5,
                Phase.STUDY,
                "Family",
                "Home Responsibilities",
                "A small household responsibility comes up during the week.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Take care of it", 3, Map.of("Happiness", 2), 0),
                        new EventChoice("Ask for time and do it later", 1, Map.of("Happiness", 0), 0),
                        new EventChoice("Avoid it", 1, Map.of("Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t06_study_quiz_announcement",
                "main",
                6,
                Phase.STUDY,
                "Study",
                "Quiz Announcement",
                "A quiz is announced and it will test content from the last few weeks.",
                "stress",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Review consistently each day", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Cram the night before", 3, Map.of("Knowledge", 5, "Happiness", -1), 0),
                        new EventChoice("Hope for the best", 1, Map.of("Knowledge", -3, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t06_cca_training_intensity_increases",
                "main",
                6,
                Phase.STUDY,
                "CCA",
                "Training Intensity Increases",
                "Training becomes more intense as your CCA prepares for upcoming activities.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Attend training and commit",
                                5,
                                Map.of("Socialise", 5, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Attend lightly", 3, Map.of("Socialise", 3, "Happiness", 1), 0),
                        new EventChoice("Skip to protect your study time", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t06_social_friends_need_support",
                "main",
                6,
                Phase.STUDY,
                "Social",
                "Friends Need Support",
                "A friend is stressed and asks if you can talk.",
                "neutral",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Make time and support them",
                                3,
                                Map.of("Socialise", 4, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.VENT
                        ),
                        new EventChoice("Send a thoughtful message", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                        new EventChoice("Decline due to workload", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t06_family_family_expectations",
                "main",
                6,
                Phase.STUDY,
                "Family",
                "Family Expectations",
                "Your parents ask about your progress and grades so far.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Share your study plan", 2, Map.of("Happiness", 2), 0),
                        new EventChoice("Give a short update", 1, Map.of("Happiness", 1), 40),
                        new EventChoice("Avoid the conversation", 1, Map.of("Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t07_study_recess_week_catch_up",
                "main",
                7,
                Phase.STUDY,
                "Study",
                "Recess Week Catch-Up",
                "There are no scheduled classes, but deadlines remain and revisions can pay off.",
                "neutral",
                List.of(),
                List.of(
                        new EventChoice("Catch up on all modules", 5, Map.of("Knowledge", 9, "Happiness", -1), 0),
                        new EventChoice("Catch up on the most difficult module", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Do only light revision", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t07_cca_cca_planning_during_recess",
                "main",
                7,
                Phase.STUDY,
                "CCA",
                "CCA Planning During Recess",
                "Your CCA asks for help planning upcoming events during recess week.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Help with planning",
                                3,
                                Map.of("Socialise", 4, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Help briefly", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                        new EventChoice("Decline and focus on revision", 3, Map.of("Knowledge", 4), 0)
                )
        ));

        events.add(new Events(
                "main_t07_social_a_calm_afternoon",
                "main",
                7,
                Phase.STUDY,
                "Social",
                "A Calm Afternoon",
                "Friends suggest a simple outing during recess week to recharge.",
                "positive",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Go out and fully disconnect",
                                4,
                                Map.of("Happiness", 6, "Socialise", 3),
                                30,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Join briefly, then return to work", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                        new EventChoice("Stay in and revise", 3, Map.of("Knowledge", 4, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t07_family_weekend_at_home",
                "main",
                7,
                Phase.STUDY,
                "Family",
                "Weekend at Home",
                "You spend more time at home during recess week.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Spend time with family", 3, Map.of("Happiness", 4, "Socialise", 1), 0),
                        new EventChoice("Keep to yourself and study", 3, Map.of("Knowledge", 4), 0),
                        new EventChoice("Try to balance both", 4, Map.of("Knowledge", 2, "Happiness", 2), 0)
                )
        ));

        events.add(new Events(
                "main_t08_study_second_half_begins",
                "main",
                8,
                Phase.STUDY,
                "Study",
                "Second Half Begins",
                "Classes resume and the pace increases after recess week.",
                "neutral",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Stay consistent and revise weekly", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Focus only on urgent tasks", 3, Map.of("Knowledge", 5), 0),
                        new EventChoice("Struggle to restart your routine", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t08_cca_event_preparation",
                "main",
                8,
                Phase.STUDY,
                "CCA",
                "Event Preparation",
                "Your CCA begins preparing for an event and needs volunteers.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Volunteer and contribute",
                                4,
                                Map.of("Socialise", 4, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Help only with small tasks", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                        new EventChoice("Skip to protect your study time", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t08_social_group_project_meeting",
                "main",
                8,
                Phase.STUDY,
                "Social",
                "Group Project Meeting",
                "Your group schedules a meeting to align progress and responsibilities.",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Attend and contribute actively",
                                3,
                                Map.of("Knowledge", 2, "Socialise", 3),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        ),
                        new EventChoice("Attend but stay quiet", 2, Map.of("Socialise", 1), 0),
                        new EventChoice("Skip and send updates later", 1, Map.of("Socialise", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t08_family_unexpected_errand",
                "main",
                8,
                Phase.STUDY,
                "Family",
                "Unexpected Errand",
                "A family matter comes up and you are asked to help.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Help immediately", 3, Map.of("Happiness", 2), 0),
                        new EventChoice("Help later after finishing tasks", 2, Map.of("Happiness", 1, "Knowledge", 1), 0),
                        new EventChoice("Refuse due to stress", 1, Map.of("Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t09_study_continuous_assessments",
                "main",
                9,
                Phase.STUDY,
                "Study",
                "Continuous Assessments",
                "Quizzes, labs, and submissions appear across multiple modules this week.",
                "stress",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Work steadily across all modules", 5, Map.of("Knowledge", 9, "Happiness", -1), 0),
                        new EventChoice("Prioritise the most difficult module", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Do the minimum required", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t09_cca_committee_deadline",
                "main",
                9,
                Phase.STUDY,
                "CCA",
                "Committee Deadline",
                "Your CCA committee has a planning deadline and needs last-minute help.",
                "stress",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Help and meet the deadline",
                                4,
                                Map.of("Socialise", 4, "Happiness", 1),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Help briefly and leave", 2, Map.of("Socialise", 2), 0),
                        new EventChoice("Decline to focus on assessments", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t09_social_tension_in_the_group",
                "main",
                9,
                Phase.STUDY,
                "Social",
                "Tension in the Group",
                "A groupmate complains about uneven workload distribution.",
                "neutral",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Facilitate a calm discussion",
                                3,
                                Map.of("Socialise", 3, "Happiness", 1),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.DISCUSS_TASK_SPLIT
                        ),
                        new EventChoice(
                                "Take on extra work to keep peace",
                                4,
                                Map.of("Knowledge", 3, "Happiness", -1),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.WORK_SPRINT
                        ),
                        new EventChoice("Avoid the conflict", 1, Map.of("Socialise", -2, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t09_family_supportive_message",
                "main",
                9,
                Phase.STUDY,
                "Family",
                "Supportive Message",
                "A family member sends an encouraging message about staying consistent.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Reply with appreciation", 1, Map.of("Happiness", 2), 0),
                        new EventChoice("Read and continue working", 1, Map.of("Happiness", 1), 0),
                        new EventChoice("Ignore it due to stress", 1, Map.of("Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t10_study_project_crunch",
                "main",
                10,
                Phase.STUDY,
                "Study",
                "Project Crunch",
                "Your project deadline is approaching and the workload spikes.",
                "stress",
                List.of(NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Organise tasks and execute quickly",
                                5,
                                Map.of("Knowledge", 8, "Socialise", 1),
                                0,
                                List.of(),
                                List.of(NPCFactory.grpMate),
                                NPCAction.WORK_SPRINT
                        ),
                        new EventChoice("Work on your portion only", 4, Map.of("Knowledge", 6), 0),
                        new EventChoice("Delay and hope for a miracle", 1, Map.of("Knowledge", -3, "Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t10_cca_competition_preparation",
                "main",
                10,
                Phase.STUDY,
                "CCA",
                "Competition Preparation",
                "Your CCA prepares for a performance or competition and needs full attendance.",
                "stress",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Commit fully",
                                5,
                                Map.of("Socialise", 5, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Help briefly", 3, Map.of("Socialise", 3, "Happiness", 1), 0),
                        new EventChoice("Skip to protect your project time", 4, Map.of("Knowledge", 5, "Socialise", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t10_social_a_quick_meal_with_friends",
                "main",
                10,
                Phase.STUDY,
                "Social",
                "A Quick Meal With Friends",
                "Friends suggest a quick meal to stay connected during a stressful week.",
                "neutral",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Join and relax briefly",
                                2,
                                Map.of("Socialise", 3, "Happiness", 2),
                                8,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Join but discuss project progress", 2, Map.of("Socialise", 2, "Knowledge", 1), 12),
                        new EventChoice("Decline and keep working", 1, Map.of("Knowledge", 1, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t10_family_check_in_during_stress",
                "main",
                10,
                Phase.STUDY,
                "Family",
                "Check-In During Stress",
                "Your parents notice you sound stressed and ask if you are coping well.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Talk openly for a while", 2, Map.of("Happiness", 3), 0),
                        new EventChoice("Reassure them briefly", 1, Map.of("Happiness", 1), 0),
                        new EventChoice("Avoid the conversation", 1, Map.of("Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t11_study_presentation_week",
                "main",
                11,
                Phase.STUDY,
                "Study",
                "Presentation Week",
                "Your group must present your project and answer questions confidently.",
                "stress",
                List.of(NPCFactory.prof, NPCFactory.grpMate),
                List.of(
                        new EventChoice(
                                "Rehearse thoroughly",
                                4,
                                Map.of("Knowledge", 6, "Happiness", 1),
                                0,
                                List.of(),
                                List.of(NPCFactory.prof, NPCFactory.grpMate),
                                NPCAction.WORK_SPRINT
                        ),
                        new EventChoice("Prepare key points only", 3, Map.of("Knowledge", 4), 0),
                        new EventChoice("Hope it goes well", 1, Map.of("Knowledge", -2, "Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t11_cca_cca_event_day",
                "main",
                11,
                Phase.STUDY,
                "CCA",
                "CCA Event Day",
                "Your CCA runs an event and needs members to support operations.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Support the event",
                                4,
                                Map.of("Socialise", 4, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Support briefly", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                        new EventChoice("Skip due to presentation prep", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t11_social_celebration_suggestion",
                "main",
                11,
                Phase.STUDY,
                "Social",
                "Celebration Suggestion",
                "After the presentation, friends suggest a small celebration.",
                "positive",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Join and celebrate",
                                3,
                                Map.of("Happiness", 5, "Socialise", 3),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Join briefly, then leave", 2, Map.of("Happiness", 3, "Socialise", 2), 0),
                        new EventChoice("Skip and rest", 1, Map.of("Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t11_family_sharing_progress",
                "main",
                11,
                Phase.STUDY,
                "Family",
                "Sharing Progress",
                "Your family asks about your project and how things are going.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Share your progress and challenges", 2, Map.of("Happiness", 2), 0),
                        new EventChoice("Give a short update", 1, Map.of("Happiness", 1), 40),
                        new EventChoice("Avoid talking about school", 1, Map.of("Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t12_study_final_assignments_begin",
                "main",
                12,
                Phase.STUDY,
                "Study",
                "Final Assignments Begin",
                "Multiple modules announce final submissions and remaining assessments.",
                "stress",
                List.of(NPCFactory.prof),
                List.of(
                        new EventChoice("Work systematically and submit early", 5, Map.of("Knowledge", 9), 0),
                        new EventChoice("Focus on one submission at a time", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Delay until the deadline feels close", 1, Map.of("Knowledge", -3, "Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t12_cca_reduced_cca_activity",
                "main",
                12,
                Phase.STUDY,
                "CCA",
                "Reduced CCA Activity",
                "CCA attendance drops as finals approach. Leaders ask members to remain engaged.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Attend one final session",
                                3,
                                Map.of("Socialise", 3, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.JOIN_EVENT
                        ),
                        new EventChoice("Help briefly and leave", 2, Map.of("Socialise", 2, "Happiness", 1), 0),
                        new EventChoice("Skip to focus on assignments", 3, Map.of("Knowledge", 4, "Socialise", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t12_social_partner_wants_time",
                "main",
                12,
                Phase.STUDY,
                "Social",
                "Partner Wants Time",
                "Your partner asks whether you can spend time together before finals begin.",
                "neutral",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Plan a short date", 3, Map.of("Happiness", 5, "Socialise", 2), 40),
                        new EventChoice("Suggest a brief meet-up", 2, Map.of("Happiness", 3, "Socialise", 1), 40),
                        new EventChoice("Postpone due to workload", 1, Map.of("Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t12_family_practical_support",
                "main",
                12,
                Phase.STUDY,
                "Family",
                "Practical Support",
                "Your family offers practical support such as meals or quiet space to study.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Accept and feel supported", 1, Map.of("Happiness", 3), 0),
                        new EventChoice("Thank them and continue working", 1, Map.of("Happiness", 2, "Knowledge", 1), 0),
                        new EventChoice("Decline and keep things as usual", 1, Map.of("Happiness", 0), 0)
                )
        ));

        events.add(new Events(
                "main_t13_study_revision_week",
                "main",
                13,
                Phase.STUDY,
                "Study",
                "Revision Week",
                "Teaching ends and revision becomes the main focus. You choose how structured to be.",
                "neutral",
                List.of(),
                List.of(
                        new EventChoice("Follow a structured revision plan", 5, Map.of("Knowledge", 10, "Happiness", -1), 0),
                        new EventChoice("Revise selectively based on weaknesses", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Do light revision only", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t13_cca_cca_winds_down",
                "main",
                13,
                Phase.STUDY,
                "CCA",
                "CCA Winds Down",
                "Most CCAs reduce activities during revision week, but some members still meet casually.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice(
                                "Attend a short casual meet-up",
                                2,
                                Map.of("Socialise", 2, "Happiness", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.ccaLead),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Skip and focus on revision", 3, Map.of("Knowledge", 4), 0),
                        new EventChoice("Send support messages instead", 1, Map.of("Socialise", 1, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t13_social_quiet_support_from_friends",
                "main",
                13,
                Phase.STUDY,
                "Social",
                "Quiet Support From Friends",
                "Friends suggest studying in the same space for quiet mutual support.",
                "positive",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Join a quiet study session",
                                3,
                                Map.of("Knowledge", 2, "Socialise", 2, "Happiness", 1),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Meet briefly for encouragement", 2, Map.of("Happiness", 2, "Socialise", 2), 0),
                        new EventChoice("Decline and revise alone", 2, Map.of("Knowledge", 3), 0)
                )
        ));

        events.add(new Events(
                "main_t13_family_household_calm",
                "main",
                13,
                Phase.STUDY,
                "Family",
                "Household Calm",
                "Your family tries to keep the home environment quiet to help you concentrate.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Express appreciation", 1, Map.of("Happiness", 2), 0),
                        new EventChoice("Use the quiet time to revise", 2, Map.of("Knowledge", 2, "Happiness", 1), 0),
                        new EventChoice("Feel pressured by expectations", 1, Map.of("Happiness", -2), 0)
                )
        ));

        events.add(new Events(
                "main_t14_study_final_preparation",
                "main",
                14,
                Phase.STUDY,
                "Study",
                "Final Preparation",
                "Exams are about to begin. You choose how to spend your final preparation time.",
                "climax",
                List.of(),
                List.of(
                        new EventChoice("Do a full timed practice under exam conditions", 5, Map.of("Knowledge", 9, "Happiness", -1), 0),
                        new EventChoice("Review summaries and key concepts", 4, Map.of("Knowledge", 7), 0),
                        new EventChoice("Do minimal review to stay calm", 2, Map.of("Knowledge", 3, "Happiness", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t14_cca_cca_pause",
                "main",
                14,
                Phase.STUDY,
                "CCA",
                "CCA Pause",
                "Your CCA leadership encourages everyone to focus on exams and pause commitments.",
                "neutral",
                List.of(NPCFactory.ccaLead),
                List.of(
                        new EventChoice("Acknowledge and step back responsibly", 1, Map.of("Happiness", 1), 0),
                        new EventChoice("Send encouraging messages to members", 1, Map.of("Socialise", 1, "Happiness", 1), 0),
                        new EventChoice("Feel guilty about reduced involvement", 1, Map.of("Happiness", -1), 0)
                )
        ));

        events.add(new Events(
                "main_t14_social_last_minute_invitation",
                "main",
                14,
                Phase.STUDY,
                "Social",
                "Last-Minute Invitation",
                "Friends invite you for a short break to clear your mind before exams.",
                "neutral",
                List.of(NPCFactory.bestFriend),
                List.of(
                        new EventChoice(
                                "Take a short break with friends",
                                2,
                                Map.of("Happiness", 3, "Socialise", 2),
                                0,
                                List.of(),
                                List.of(NPCFactory.bestFriend),
                                NPCAction.HANG_OUT
                        ),
                        new EventChoice("Decline and keep reviewing", 1, Map.of("Knowledge", 1, "Happiness", -1), 0),
                        new EventChoice("Meet briefly, then return to revise", 2, Map.of("Happiness", 2, "Knowledge", 1), 0)
                )
        ));

        events.add(new Events(
                "main_t14_family_final_encouragement",
                "main",
                14,
                Phase.STUDY,
                "Family",
                "Final Encouragement",
                "Your family wishes you well and reminds you to take care of yourself during exams.",
                "positive",
                List.of(NPCFactory.friends),
                List.of(
                        new EventChoice("Thank them and feel supported", 1, Map.of("Happiness", 3), 0),
                        new EventChoice("Keep it short and return to work", 1, Map.of("Happiness", 1, "Knowledge", 1), 0),
                        new EventChoice("Feel stressed by expectations", 1, Map.of("Happiness", -2), 0)
                )
        ));

        return events;
    }
}