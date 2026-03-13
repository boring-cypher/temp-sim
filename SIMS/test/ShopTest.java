import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.shop.Shop;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ShopTest {

    @Test
    public void testBuyItemAdds() {
        // Prepare fake input to choose the first item.
        InputStream originalIn = System.in;
        try {
            String input = "1\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            Character character = new Character("Sam", "Male", 80, 10, 5);
            double startingBalance = character.getBalance();

            Shop shop = new Shop();
            int itemPrice = shop.getItems().get(0).getPrice();

            shop.open(character);

            // Verify the item is added and money is deducted.
            Assert.assertEquals(1, character.getInventory().size());
            Assert.assertEquals(startingBalance - itemPrice, character.getBalance(), 0.0001);
        } finally {
            // Restore System.in so other tests are not affected.
            System.setIn(originalIn);
        }
    }

    @Test
    public void testBuyItemFails() {
        // Prepare fake input to choose the first item.
        InputStream originalIn = System.in;
        try {
            String input = "1\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            Character character = new Character("Sam", "Male", 80, 10, 5);
            // Drain the balance so any purchase fails.
            boolean drained = character.getAccount().withdraw(character.getBalance());
            Assert.assertTrue(drained);

            Shop shop = new Shop();

            shop.open(character);

            // No item should be added when funds are insufficient.
            Assert.assertEquals(0, character.getInventory().size());
            Assert.assertEquals(0.0, character.getBalance(), 0.0001);
        } finally {
            // Restore System.in so other tests are not affected.
            System.setIn(originalIn);
        }
    }
}
