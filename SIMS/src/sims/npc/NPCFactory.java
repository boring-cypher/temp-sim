package sims.npc;

public class NPCFactory {

    public static final NPC prof = new Professor(
            "Dr. Cao",
            "Male",
            "Teacher",
            50,
            5,   // toleranceForSpam
            3    // gradingStrictness
    );

    public static final NPC bestFriend = new Friend(
            "Jun Hao",
            "Male",
            "Best sims.npc.Friend",
            50,
            7,           // loyalty
            true,        // isFreeToday
            "LISTENER",  // supportStyle
            4            // gossipNetwork
    );

    public static final NPC ccaLead = new CcaSenior(
            "Mervyn",
            "Male",
            "sims.character.CCA Leader",
            50,
            0,   // attendanceCount
            3,   // respect
            3    // trust
    );

    public static final NPC grpMate = new ProjectMate(
            "Amos",
            "Male",
            "Group Mate",
            50,
            0,   // missedDeadlinesByPlayer
            20   // tension
    );

    public static final NPC friends = new Friend(
            "Wei Zhi",
            "Unknown",
            "Friends",
            50,
            5,             // loyalty
            true,          // isFreeToday
            "CHEERLEADER", // supportStyle
            6              // gossipNetwork
    );

    private NPCFactory() {
        // Prevent instantiation
    }
}