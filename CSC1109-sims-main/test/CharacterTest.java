import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.job.Job;
import sims.job.TutorJob;

public class CharacterTest {

    @Test
    public void testSetFields() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  10, 5, 3);
        Assert.assertEquals("Sam", c.getName());
        Assert.assertEquals("Male", c.getGender());
        Assert.assertEquals(10, c.getHappiness());
        Assert.assertEquals(5, c.getSocial());
        Assert.assertEquals(3, c.getKnowledge());
    }

    @Test (expected = IllegalArgumentException.class)
    public void SetNegativeKnowledge() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  0, 10, 5);
        c.setKnowledge(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void SetOverKnowledge() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  0, 10, 5);
        c.setKnowledge(101);
    }

    @Test (expected = IllegalArgumentException.class)
    public void SetNegativeSocial() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  0, 10, 5);
        c.setSocial(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void SetOverSocial() {
        sims.character.Character c = new Character("Sam", "Male",  0, 10, 5);
        c.setSocial(101);
    }

    @Test
    public void testSocialise() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  10, 10, 5);
        c.socialise();
        Assert.assertEquals(30, c.getHappiness());
    }

    @Test
    public void testStatCapWithChangeMethods() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  50, 10, 5);
        c.changeHappiness(-50);
        c.changeSocial(150);
        c.changeKnowledge(-100);
        Assert.assertEquals(0, c.getHappiness());
        Assert.assertEquals(100, c.getSocial());
        Assert.assertEquals(0, c.getKnowledge());
    }

    @Test
    public void testSetCurrentJob() {
        sims.character.Character c = new sims.character.Character("Sam", "Male",  80, 10, 5);
        Job job = new TutorJob();
        c.setCurrentJob(job);
        Assert.assertEquals(job, c.getCurrentJob());
    }
}
