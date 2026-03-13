package sims.event;

import sims.npc.NPCAction;
import sims.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HolidayEventBuilder {

    public static List<Events> buildHolidayEvents() {
        List<Events> holidayevents = new ArrayList<>();

        holidayevents.add(
                new Events(
                        "holiday_study_01",
                        "main",
                        15,
                        Phase.RECESS,
                        "Study",
                        "Online Certification Course",
                        "You find a short online certification course that can be completed during the holidays.",
                        "neutral",
                        List.of(NPCFactory.ccaLead),
                        List.of(
                                new EventChoice(
                                        "Complete the course seriously",
                                        5,
                                        Map.of("Knowledge", 7, "Happiness", -1),
                                        0
                                ),
                                new EventChoice(
                                        "Watch a few videos only",
                                        2,
                                        Map.of("Knowledge", 3),
                                        0
                                ),
                                new EventChoice(
                                        "Ignore it",
                                        0,
                                        Map.of("Happiness", 1),
                                        0
                                )
                        )
                )
        );

        holidayevents.add(
                new Events(
                        "holiday_study_02",
                        "main",
                        15,
                        Phase.RECESS,
                        "Study",
                        "Revision Catch Up",
                        "You consider revising past modules to prepare for the next semester.",
                        "neutral",
                        List.of(NPCFactory.prof),
                        List.of(
                                new EventChoice(
                                        "Create a full revision plan",
                                        4,
                                        Map.of("Knowledge", 6, "Stress", 1),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.prof),
                                        NPCAction.ASK_FEEDBACK
                                ),
                                new EventChoice(
                                        "Light revision only",
                                        2,
                                        Map.of("Knowledge", 3),
                                        0
                                ),
                                new EventChoice(
                                        "Skip revision",
                                        0,
                                        Map.of("Happiness", 1),
                                        0
                                )
                        )
                )
        );

        holidayevents.add(
                new Events(
                        "holiday_cca_01",
                        "main",
                        15,
                        Phase.RECESS,
                        "Co-curriculum Activity",
                        "CCA Training Camp",
                        "Your CCA organizes an optional holiday training camp.",
                        "positive",
                        List.of(NPCFactory.ccaLead),
                        List.of(
                                new EventChoice(
                                        "Attend the full camp",
                                        5,
                                        Map.of("Social", 4, "Happiness", 1),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.JOIN_EVENT
                                ),
                                new EventChoice(
                                        "Attend partially",
                                        3,
                                        Map.of("Social", 2),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.JOIN_EVENT
                                ),
                                new EventChoice(
                                        "Do not attend",
                                        0,
                                        Map.of("Happiness", 1),
                                        0
                                )
                        )
                )
        );

        holidayevents.add(
                new Events(
                        "holiday_cca_02",
                        "main",
                        15,
                        Phase.RECESS,
                        "Co-curriculum Activity",
                        "CCA Planning Meeting",
                        "You are invited to help plan activities for the next semester.",
                        "neutral",
                        List.of(NPCFactory.ccaLead),
                        List.of(
                                new EventChoice(
                                        "Take a leadership role",
                                        4,
                                        Map.of("Social", 3, "Stress", 1),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.JOIN_EVENT
                                ),
                                new EventChoice(
                                        "Contribute ideas only",
                                        2,
                                        Map.of("Social", 2),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.ASK_TRAINING_TIPS
                                ),
                                new EventChoice(
                                        "Skip the meeting",
                                        0,
                                        Map.of("Happiness", 1),
                                        0
                                )
                        )
                )
        );

        holidayevents.add(
                new Events(
                        "holiday_social_01",
                        "main",
                        15,
                        Phase.RECESS,
                        "Social",
                        "Friends Gathering",
                        "Your friends plan a casual get together during the holidays.",
                        "positive",
                        List.of(NPCFactory.friends),
                        List.of(
                                new EventChoice(
                                        "Attend and stay late",
                                        3,
                                        Map.of("Happiness", 4, "Social", 2),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.friends),
                                        NPCAction.HANG_OUT
                                ),
                                new EventChoice(
                                        "Drop by briefly",
                                        1,
                                        Map.of("Happiness", 2),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.friends),
                                        NPCAction.HANG_OUT
                                ),
                                new EventChoice(
                                        "Decline",
                                        0,
                                        Map.of(),
                                        0
                                )
                        )
                )
        );

        holidayevents.add(
                new Events(
                        "holiday_social_02",
                        "main",
                        15,
                        Phase.RECESS,
                        "Social",
                        "Networking Invite",
                        "A senior invites you to a small networking session.",
                        "neutral",
                        List.of(NPCFactory.ccaLead),
                        List.of(
                                new EventChoice(
                                        "Attend and interact actively",
                                        4,
                                        Map.of("Social", 5, "Stress", 1),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.ASK_RECOMMENDATION
                                ),
                                new EventChoice(
                                        "Attend quietly",
                                        2,
                                        Map.of("Social", 2),
                                        0,
                                        List.of(),
                                        List.of(NPCFactory.ccaLead),
                                        NPCAction.ASK_TRAINING_TIPS
                                ),
                                new EventChoice(
                                        "Do not attend",
                                        0,
                                        Map.of("Happiness", 1),
                                        0
                                )
                        )
                )
        );

        return holidayevents;
    }
}