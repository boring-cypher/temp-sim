import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.npc.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NPCTest {

    // helper methods for Character constructor
    private Character createLowKnowledgePlayer() {
        return new Character("Sam", "Male", 80, 10, 40);
    }

    private Character createHighKnowledgePlayer() {
        return new Character("Sam", "Male", 80, 10, 80);
    }

    // helper method to test unsupported actions
    private void assertUnsupportedAction(NPC npc, NPCAction action, Character player) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            npc.interact(action, player);

            String result = output.toString().trim();
            Assert.assertEquals(npc.getName() + " does not support action: " + action, result);
        } finally {
            System.setOut(originalOut);
        }
    }

    // =========================
    // CcaSenior tests
    // =========================

    @Test
    public void testCcaSenior_AskTrainingTips_IncreasesRespect() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 1);

        senior.interact(NPCAction.ASK_TRAINING_TIPS, createLowKnowledgePlayer());

        Assert.assertEquals(2, senior.getRespect());
        Assert.assertEquals(6, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_JoinEvent_IncreasesRespect() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 3);

        senior.interact(NPCAction.JOIN_EVENT, createLowKnowledgePlayer());

        Assert.assertEquals(5, senior.getRespect());
        Assert.assertEquals(7, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_AskRecommendation_DoesNotChangeRespect_WhenSuccessful() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 5);

        senior.interact(NPCAction.ASK_RECOMMENDATION, createLowKnowledgePlayer());

        Assert.assertEquals(5, senior.getRespect());
        Assert.assertEquals(7, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_AskRecommendation_DoesNotChangeRespect_WhenFail() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 4);

        senior.interact(NPCAction.ASK_RECOMMENDATION, createLowKnowledgePlayer());

        Assert.assertEquals(4, senior.getRespect());
        Assert.assertEquals(3, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_HappinessCappedAt100() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 99, 5);

        senior.interact(NPCAction.JOIN_EVENT, createLowKnowledgePlayer());

        Assert.assertEquals(100, senior.getHappiness());
        Assert.assertEquals(7, senior.getRespect()); // 5 + 2
    }

    @Test
    public void testCcaSenior_UnsupportedAction() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 1);

        assertUnsupportedAction(senior, NPCAction.VENT, createLowKnowledgePlayer());
    }

    // =========================
    // Friend tests
    // =========================
    @Test
    public void testFriend_HappinessCappedAt100_VentListener() {
        Friend f = new Friend("Ben", "Male", "Friend", 99, 2, true, "LISTENER", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());   // 2 -> 4
        Assert.assertEquals(100, f.getHappiness()); // 99 + 3 -> capped at 100
    }

    @Test
    public void testFriend_HappinessCappedAt100_HangOut() {
        Friend f = new Friend("Ben", "Male", "Friend", 99, 2, true, "LISTENER", 3);

        f.interact(NPCAction.HANG_OUT, createLowKnowledgePlayer());

        Assert.assertEquals(3, f.getLoyalty());   // 2 -> 3
        Assert.assertEquals(100, f.getHappiness()); // 99 + 2 -> capped at 100
    }

    @Test
    public void testFriend_HangOut_WhenNotFree_DoesNotIncreaseLoyalty() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, false, "LISTENER", 3);

        f.interact(NPCAction.HANG_OUT, createLowKnowledgePlayer());

        Assert.assertEquals(2, f.getLoyalty()); // unchanged
        Assert.assertEquals(4, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_BoundaryLoyaltyFive() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 5, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty()); // reduced by 1 after borrowing
        Assert.assertEquals(6, f.getHappiness()); // success path
    }

    @Test
    public void testFriend_BorrowNotes_BoundaryLoyaltyFour() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 4, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(3, f.getLoyalty()); // reduced by 1
        Assert.assertEquals(3, f.getHappiness()); // fail path
    }

    @Test
    public void testFriend_Vent_Listener() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, true, "LISTENER", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(8, f.getHappiness());
        Assert.assertTrue(f.isFreeToday());
        Assert.assertEquals("LISTENER", f.getSupportStyle());
        Assert.assertEquals(3, f.getGossipNetwork());
    }

    @Test
    public void testFriend_Vent_Cheerleader() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, true, "CHEERLEADER", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(7, f.getHappiness());
    }

    @Test
    public void testFriend_Vent_Comedian() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, true, "COMEDIAN", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(7, f.getHappiness());
    }

    @Test
    public void testFriend_Vent_OtherSupportStyle() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, true, "ADVISOR", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(6, f.getHappiness());
    }

    @Test
    public void testFriend_HangOut_WhenFree() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, true, "LISTENER", 3);

        f.interact(NPCAction.HANG_OUT, createLowKnowledgePlayer());

        Assert.assertEquals(3, f.getLoyalty());
        Assert.assertEquals(7, f.getHappiness());
    }

    @Test
    public void testFriend_HangOut_WhenNotFree() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, false, "LISTENER", 3);

        f.interact(NPCAction.HANG_OUT, createLowKnowledgePlayer());

        Assert.assertEquals(2, f.getLoyalty());
        Assert.assertEquals(4, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_WhenLoyaltyEnough() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 5, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(6, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_WhenLoyaltyLow() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 3, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(2, f.getLoyalty());
        Assert.assertEquals(3, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_LoyaltyDoesNotGoBelowZero() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 0, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(0, f.getLoyalty());
        Assert.assertEquals(3, f.getHappiness());
    }

    @Test
    public void testFriend_UnsupportedAction() {
        Friend f = new Friend("Ben", "Male", "Friend", 5, 2, true, "LISTENER", 3);

        assertUnsupportedAction(f, NPCAction.ASK_TRAINING_TIPS, createLowKnowledgePlayer());
    }

    // =========================
    // Professor tests
    // =========================
    @Test
    public void testProfessor_AskFeedback_BoundaryToleranceFour() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 4);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());

        Assert.assertEquals(12, p.getHappiness()); // 4 -> 3, still high branch
    }

    @Test
    public void testProfessor_AskFeedback_BoundaryToleranceTwo() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 2);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());

        Assert.assertEquals(11, p.getHappiness()); // 2 -> 1, medium branch
    }

    @Test
    public void testProfessor_AskFeedback_BoundaryToleranceZero() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 0);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());

        Assert.assertEquals(8, p.getHappiness()); // 0 -> -1, low branch
    }

    @Test
    public void testProfessor_HappinessCappedAt100_AskFeedback() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 99, 5);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());

        Assert.assertEquals(100, p.getHappiness()); // 99 + 2 -> capped
    }

    @Test
    public void testProfessor_HappinessCappedAt100_BookConsultLowKnowledge() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 99, 5);

        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());

        Assert.assertEquals(1, p.getConsultsUsedThisWeek());
        Assert.assertEquals(100, p.getHappiness()); // 99 + 2 -> capped
    }

    @Test
    public void testProfessor_BookConsult_SecondConsultStillAllowed() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 5);

        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());
        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());

        Assert.assertEquals(2, p.getConsultsUsedThisWeek());
        Assert.assertEquals(14, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_FirstTimeAtBoundary50() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 50, 5);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(51, p.getHappiness()); // >= 50 branch
    }

    @Test
    public void testProfessor_RequestExtension_SecondTimeAlwaysPenalized() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 40, 5);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer()); // 40 -> 39
        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer()); // 39 -> 36

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(36, p.getHappiness());
    }

    @Test
    public void testProfessor_AskFeedback_HighTolerance() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 5);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());
        Assert.assertEquals(12, p.getHappiness());
    }

    @Test
    public void testProfessor_AskFeedback_MediumTolerance() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 3);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());
        Assert.assertEquals(11, p.getHappiness());
    }

    @Test
    public void testProfessor_AskFeedback_LowTolerance() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 1);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());
        Assert.assertEquals(8, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_FirstTimeHighHappiness() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 50, 5);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(51, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_FirstTimeLowHappiness() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 40, 5);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(39, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_SecondTime() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 50, 5);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());
        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(48, p.getHappiness()); // 50 -> 51 -> 48
    }

    @Test
    public void testProfessor_BookConsult_LowKnowledge_FirstConsult() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 5);

        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());

        Assert.assertEquals(1, p.getConsultsUsedThisWeek());
        Assert.assertEquals(12, p.getHappiness());
    }

    @Test
    public void testProfessor_BookConsult_HighKnowledge_FirstConsult() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 5);

        p.interact(NPCAction.BOOK_CONSULT, createHighKnowledgePlayer());

        Assert.assertEquals(1, p.getConsultsUsedThisWeek());
        Assert.assertEquals(11, p.getHappiness());
    }

    @Test
    public void testProfessor_BookConsult_ThirdConsultFails() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 5);

        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());
        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());
        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());

        Assert.assertEquals(2, p.getConsultsUsedThisWeek());
        Assert.assertEquals(12, p.getHappiness()); // 10 -> 12 -> 14 -> 12
    }

    @Test
    public void testProfessor_UnsupportedAction() {
        Professor p = new Professor("Dr Lim", "Male", "Professor", 10, 5);

        assertUnsupportedAction(p, NPCAction.JOIN_EVENT, createLowKnowledgePlayer());
    }

    // =========================
    // ProjectMate tests
    // =========================

    @Test
    public void testProjectMate_DiscussTaskSplit_Boundary40() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 40);

        mate.interact(NPCAction.DISCUSS_TASK_SPLIT, createLowKnowledgePlayer());

        Assert.assertEquals(4, mate.getHappiness());
        Assert.assertEquals(40, mate.getTension());
    }

    @Test
    public void testProjectMate_DiscussTaskSplit_Boundary70() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 70);

        mate.interact(NPCAction.DISCUSS_TASK_SPLIT, createLowKnowledgePlayer());

        Assert.assertEquals(2, mate.getHappiness());
        Assert.assertEquals(70, mate.getTension());
    }

    @Test
    public void testProjectMate_WorkSprint_Boundary60() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 60);

        mate.interact(NPCAction.WORK_SPRINT, createLowKnowledgePlayer());

        Assert.assertEquals(7, mate.getHappiness());
        Assert.assertEquals(60, mate.getTension());
    }

    @Test
    public void testProjectMate_DiscussTaskSplit_LowTension() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 30);

        mate.interact(NPCAction.DISCUSS_TASK_SPLIT, createLowKnowledgePlayer());

        Assert.assertEquals(7, mate.getHappiness());
        Assert.assertEquals(30, mate.getTension());
    }

    @Test
    public void testProjectMate_DiscussTaskSplit_MediumTension() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 50);

        mate.interact(NPCAction.DISCUSS_TASK_SPLIT, createLowKnowledgePlayer());

        Assert.assertEquals(4, mate.getHappiness());
        Assert.assertEquals(50, mate.getTension());
    }

    @Test
    public void testProjectMate_DiscussTaskSplit_HighTension() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 80);

        mate.interact(NPCAction.DISCUSS_TASK_SPLIT, createLowKnowledgePlayer());

        Assert.assertEquals(2, mate.getHappiness());
        Assert.assertEquals(80, mate.getTension());
    }

    @Test
    public void testProjectMate_Apologize() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 40);

        mate.interact(NPCAction.APOLOGIZE, createLowKnowledgePlayer());

        Assert.assertEquals(25, mate.getTension());
        Assert.assertEquals(8, mate.getHappiness());
    }

    @Test
    public void testProjectMate_Apologize_TensionDoesNotGoBelowZero() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 10);

        mate.interact(NPCAction.APOLOGIZE, createLowKnowledgePlayer());

        Assert.assertEquals(0, mate.getTension());
        Assert.assertEquals(8, mate.getHappiness());
    }

    @Test
    public void testProjectMate_WorkSprint_LowTension() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 40);

        mate.interact(NPCAction.WORK_SPRINT, createLowKnowledgePlayer());

        Assert.assertEquals(7, mate.getHappiness());
        Assert.assertEquals(40, mate.getTension());
    }

    @Test
    public void testProjectMate_WorkSprint_HighTension() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 70);

        mate.interact(NPCAction.WORK_SPRINT, createLowKnowledgePlayer());

        Assert.assertEquals(3, mate.getHappiness());
        Assert.assertEquals(70, mate.getTension());
    }

    @Test
    public void testProjectMate_IncreaseMissedDeadlines() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 1, 20);

        mate.increaseMissedDeadlines();

        Assert.assertEquals(2, mate.getMissedDeadlinesByPlayer());
        Assert.assertEquals(30, mate.getTension());
    }

    @Test
    public void testProjectMate_ReduceTension() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 1, 20);

        mate.reduceTension(5);

        Assert.assertEquals(15, mate.getTension());
    }

    @Test
    public void testProjectMate_ReduceTension_NotBelowZero() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 1, 3);

        mate.reduceTension(10);

        Assert.assertEquals(0, mate.getTension());
    }

    @Test
    public void testProjectMate_UnsupportedAction() {
        ProjectMate mate = new ProjectMate("Sam", "Male", "Teammate", 5, 0, 30);

        assertUnsupportedAction(mate, NPCAction.BORROW_NOTES, createLowKnowledgePlayer());
    }
}