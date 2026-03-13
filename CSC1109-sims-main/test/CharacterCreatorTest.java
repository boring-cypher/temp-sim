import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.character.CharacterCreator;

import java.io.ByteArrayInputStream; //to fake user input
import java.io.InputStream;

public class CharacterCreatorTest {

    @Test
    public void testCreateCharacter() {
        InputStream originalIn = System.in;  //stores the original input stream
        try {
            String input = ""
                    + "Alex\n"  // name
                    + "20\n"    // age
                    + "2\n"  ;   // gender: Female
            System.setIn(new ByteArrayInputStream(input.getBytes()));  //program reads the fake input string

            Character c = CharacterCreator.createCharacter();

            Assert.assertEquals("Alex", c.getName());
            Assert.assertEquals("Female", c.getGender());
        } finally {
            System.setIn(originalIn);  //restores the original console input
        }
    }
}
