import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.npc.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NPCTest {

    // 2 helper methods for sims.character.Character constructor, one with low knowledge and one with high knowledge to book consultation with prof
    private sims.character.Character createLowKnowledgePlayer() {
        sims.character.Character player = new sims.character.Character("Sam", "Male", 80, 10, 40);
        return player;
    }

    private sims.character.Character createHighKnowledgePlayer() {
        sims.character.Character player = new Character("Sam", "Male",  80, 10, 80);
        return player;
    }

    // helper method to test unsupported actions
    private void assertUnsupportedAction(NPC npc, NPCAction action, sims.character.Character player) {
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

    // ccaSenior tests
    @Test
    public void testCcaSenior_AskTrainingTips() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 0, 2, 1);

        senior.interact(NPCAction.ASK_TRAINING_TIPS, createLowKnowledgePlayer());
        Assert.assertEquals(6, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_JoinEvent() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 1, 2, 3);

        senior.interact(NPCAction.JOIN_EVENT, createLowKnowledgePlayer());
        Assert.assertEquals(7, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_AskRecommendation_Success() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 3, 5, 5);

        senior.interact(NPCAction.ASK_RECOMMENDATION, createLowKnowledgePlayer());
        Assert.assertEquals(7, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_AskRecommendation_Fail() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 2, 4, 4);

        senior.interact(NPCAction.ASK_RECOMMENDATION, createLowKnowledgePlayer());
        Assert.assertEquals(3, senior.getHappiness());
    }

    @Test
    public void testCcaSenior_UnsupportedAction() {
        CcaSenior senior = new CcaSenior("Alex", "Male", "Senior", 5, 0, 2, 1);

        assertUnsupportedAction(senior, NPCAction.VENT, createLowKnowledgePlayer());
    }

    // friend tests
    @Test
    public void testFriend_Vent_Listener() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, true, "LISTENER", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(8, f.getHappiness());
        Assert.assertTrue(f.isFreeToday());
        Assert.assertEquals("LISTENER", f.getSupportStyle());
        Assert.assertEquals(3, f.getGossipNetwork());
    }

    @Test
    public void testFriend_Vent_Cheerleader() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, true, "CHEERLEADER", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(7, f.getHappiness());
    }

    @Test
    public void testFriend_Vent_Comedian() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, true, "COMEDIAN", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(7, f.getHappiness());
    }

    @Test
    public void testFriend_Vent_OtherSupportStyle() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, true, "ADVISOR", 3);

        f.interact(NPCAction.VENT, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(6, f.getHappiness());
    }

    @Test
    public void testFriend_HangOut_WhenFree() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, true, "LISTENER", 3);

        f.interact(NPCAction.HANG_OUT, createLowKnowledgePlayer());

        Assert.assertEquals(3, f.getLoyalty());
        Assert.assertEquals(7, f.getHappiness());
    }

    @Test
    public void testFriend_HangOut_WhenNotFree() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, false, "LISTENER", 3);

        f.interact(NPCAction.HANG_OUT, createLowKnowledgePlayer());

        Assert.assertEquals(2, f.getLoyalty());
        Assert.assertEquals(4, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_WhenLoyaltyEnough() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 5, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(4, f.getLoyalty());
        Assert.assertEquals(6, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_WhenLoyaltyLow() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 3, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(2, f.getLoyalty());
        Assert.assertEquals(3, f.getHappiness());
    }

    @Test
    public void testFriend_BorrowNotes_LoyaltyDoesNotGoBelowZero() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 0, true, "LISTENER", 3);

        f.interact(NPCAction.BORROW_NOTES, createLowKnowledgePlayer());

        Assert.assertEquals(0, f.getLoyalty());
        Assert.assertEquals(3, f.getHappiness());
    }

    @Test
    public void testFriend_UnsupportedAction() {
        Friend f = new Friend("Ben", "Male", "sims.npc.Friend", 5, 2, true, "LISTENER", 3);

        assertUnsupportedAction(f, NPCAction.ASK_TRAINING_TIPS, createLowKnowledgePlayer());
    }

    // professor tests
    @Test
    public void testProfessor_AskFeedback_HighTolerance() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 5, 7);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());
        Assert.assertEquals(12, p.getHappiness());
    }

    @Test
    public void testProfessor_AskFeedback_MediumTolerance() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 3, 7);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());
        Assert.assertEquals(11, p.getHappiness());
    }

    @Test
    public void testProfessor_AskFeedback_LowTolerance() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 1, 7);

        p.interact(NPCAction.ASK_FEEDBACK, createLowKnowledgePlayer());
        Assert.assertEquals(8, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_FirstTimeHighHappiness() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 50, 5, 7);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(51, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_FirstTimeLowHappiness() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 40, 5, 7);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(39, p.getHappiness());
    }

    @Test
    public void testProfessor_RequestExtension_SecondTime() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 50, 5, 7);

        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());
        p.interact(NPCAction.REQUEST_EXTENSION, createLowKnowledgePlayer());

        Assert.assertTrue(p.isExtensionUsed());
        Assert.assertEquals(48, p.getHappiness()); // 50 -> 51 -> 48
    }

    @Test
    public void testProfessor_BookConsult_LowKnowledge_FirstConsult() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 5, 7);

        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());

        Assert.assertEquals(1, p.getConsultsUsedThisWeek());
        Assert.assertEquals(12, p.getHappiness());
    }

    @Test
    public void testProfessor_BookConsult_HighKnowledge_FirstConsult() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 5, 7);

        p.interact(NPCAction.BOOK_CONSULT, createHighKnowledgePlayer());

        Assert.assertEquals(1, p.getConsultsUsedThisWeek());
        Assert.assertEquals(11, p.getHappiness());
    }

    @Test
    public void testProfessor_BookConsult_ThirdConsultFails() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 5, 7);

        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());
        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());
        p.interact(NPCAction.BOOK_CONSULT, createLowKnowledgePlayer());

        Assert.assertEquals(2, p.getConsultsUsedThisWeek());
        Assert.assertEquals(12, p.getHappiness()); // 10 -> 12 -> 14 -> 12
    }

    @Test
    public void testProfessor_UnsupportedAction() {
        Professor p = new Professor("Dr Lim", "Male", "sims.npc.Professor", 10, 5, 7);

        assertUnsupportedAction(p, NPCAction.JOIN_EVENT, createLowKnowledgePlayer());
    }

    // projectMate tests
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