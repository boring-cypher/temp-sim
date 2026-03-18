import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.job.Job;
import sims.job.TutorJob;

public class JobTest {

    /**
     * Simple deterministic test job so we can test base Job logic
     * without random effects from CafeAssistantJob / RetailPromoterJob.
     */
    private static class FixedJob extends Job {
        public FixedJob() {
            super("Fixed Job", "Test job", 50, 2, false);
            this.energyCost = 2;
            this.happinessEffect = 4;
            this.socialEffect = 3;
            this.knowledgeEffect = 1;
        }

        @Override
        public void workShift(Character character) {
            super.workShift(character);
        }
    }

    @Test
    public void testCharacterCanTakeJob() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        Job job = new FixedJob();

        c.setCurrentJob(job);

        Assert.assertTrue(c.hasJob());
        Assert.assertEquals(job, c.getCurrentJob());
    }

    @Test
    public void testWorkShiftUpdatesProgress() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        c.setCurrentJob(job);
        job.workShift(c);

        Assert.assertEquals(1, job.getTotalShiftsWorked());
        Assert.assertEquals(1, job.getWorkStreak());
    }

    @Test
    public void testWorkShiftUpdatesMoneyAndStats() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        double startingBalance = c.getBalance();

        job.workShift(c);

        Assert.assertEquals(startingBalance + 50, c.getBalance(), 0.0001);
        Assert.assertEquals(54, c.getHappiness());
        Assert.assertEquals(43, c.getSocial());
        Assert.assertEquals(31, c.getKnowledge());
    }

    @Test
    public void testShiftEnergyCostIncreasesWithWorkStreak() {
        FixedJob job = new FixedJob();

        Assert.assertEquals(2, job.getShiftEnergyCost());

        job.incrementWorkStreak();
        Assert.assertEquals(3, job.getShiftEnergyCost());

        job.incrementWorkStreak();
        Assert.assertEquals(4, job.getShiftEnergyCost());
    }

    @Test
    public void testResetWorkStreak() {
        FixedJob job = new FixedJob();

        job.incrementWorkStreak();
        job.incrementWorkStreak();
        Assert.assertEquals(2, job.getWorkStreak());

        job.resetWorkStreak();
        Assert.assertEquals(0, job.getWorkStreak());
        Assert.assertEquals(2, job.getShiftEnergyCost()); // back to base energyCost
    }

    @Test
    public void testCannotQuitBeforeMinimumShifts() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        c.setCurrentJob(job);

        // only 1 shift, but min required is 2
        job.workShift(c);

        boolean result = c.quitJob();

        Assert.assertFalse(result);
        Assert.assertTrue(c.hasJob());
        Assert.assertEquals(job, c.getCurrentJob());
    }

    @Test
    public void testCanQuitAfterMinimumShifts() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        c.setCurrentJob(job);

        job.workShift(c);
        job.workShift(c); // now totalShiftsWorked = 2

        boolean result = c.quitJob();

        Assert.assertTrue(result);
        Assert.assertFalse(c.hasJob());
        Assert.assertNull(c.getCurrentJob());
    }

    @Test
    public void testQuitJobResetsProgress() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        c.setCurrentJob(job);

        job.workShift(c);
        job.workShift(c);

        Assert.assertEquals(2, job.getTotalShiftsWorked());
        Assert.assertEquals(2, job.getWorkStreak());

        c.quitJob();

        Assert.assertEquals(0, job.getTotalShiftsWorked());
        Assert.assertEquals(0, job.getWorkStreak());
    }

    @Test
    public void testQuitJobWhenNoJobReturnsFalse() {
        Character c = new Character("Sam", "Male", 50, 40, 30);

        boolean result = c.quitJob();

        Assert.assertFalse(result);
        Assert.assertFalse(c.hasJob());
    }

    @Test
    public void testTutorJobBonusAfterThreeSessions() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        TutorJob job = new TutorJob();

        double startingBalance = c.getBalance();

        job.workShift(c);
        job.workShift(c);
        job.workShift(c);

        // 3 shifts x $100 + $50 bonus = $350 total guaranteed
        Assert.assertEquals(startingBalance + 350, c.getBalance(), 0.0001);
        Assert.assertEquals(0, job.getTutoringSessions());
        Assert.assertEquals(3, job.getTotalShiftsWorked());
    }

    @Test
    public void testJobPromotesToLevel2after3shifts() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        for (int i =0; i < 3; i++){
            job.workShift(c);
        }

        Assert.assertEquals(2, job.getLevel());
    }

    @Test
    public void testJobPromotesToLevel3after6shifts() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        for (int i =0; i < 6; i++){
            job.workShift(c);
        }

        Assert.assertEquals(3, job.getLevel());
    }

    @Test
    public void testPromotionIncreasesSalary(){
        Character c = new Character("Sam", "Male", 50, 40, 30);
        FixedJob job = new FixedJob();

        Assert.assertEquals(50, job.getSalary());

        for (int i =0; i < 3; i++){
            job.workShift(c);
        }

        Assert.assertEquals(2, job.getLevel());
        Assert.assertEquals(70, job.getSalary());

        for (int i =0; i < 3; i++){
            job.workShift(c);
        }

        Assert.assertEquals(3, job.getLevel());
        Assert.assertEquals(100, job.getSalary());
    }

    @Test
    public void testQuitJobResetsTutorProgressionState() {
        Character c = new Character("Sam", "Male", 50, 40, 30);
        TutorJob job = new TutorJob();
        c.setCurrentJob(job);

        for (int i = 0; i < 3; i++) {
            job.workShift(c);
        }

        Assert.assertEquals(2, job.getLevel());
        Assert.assertTrue(job.hasKnowledgeReinforcementPerk());

        // work more so quitting is allowed
        while (job.getTotalShiftsWorked() < job.getMinTurnsRequired()) {
            job.workShift(c);
        }

        boolean result = c.quitJob();

        Assert.assertTrue(result);
        Assert.assertEquals(1, job.getLevel());
        Assert.assertFalse(job.hasKnowledgeReinforcementPerk());
        Assert.assertEquals(0, job.getTutoringSessions());
    }
}