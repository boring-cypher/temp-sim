import org.junit.Assert;
import org.junit.Test;
import sims.character.Character;
import sims.item.Item;
import sims.shop.Shop;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

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

            shop.open(character, 1);

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

            shop.open(character, 1);

            // No item should be added when funds are insufficient.
            Assert.assertEquals(0, character.getInventory().size());
            Assert.assertEquals(0.0, character.getBalance(), 0.0001);
        } finally {
            // Restore System.in so other tests are not affected.
            System.setIn(originalIn);
        }
    }

    @Test
    public void testCancelPurchase() {
        InputStream originalIn = System.in;
        try {
            Character character = new Character("Sam", "Male", 80, 10, 5);
            double startingBalance = character.getBalance();

            Shop shop = new Shop();
            int cancelChoice = shop.getItems().size() + 1;
            String input = cancelChoice + "\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            shop.open(character, 1);

            Assert.assertEquals(0, character.getInventory().size());
            Assert.assertEquals(startingBalance, character.getBalance(), 0.0001);
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testMultiplePurchases() {
        InputStream originalIn = System.in;
        try {
            Character character = new Character("Sam", "Male", 80, 10, 5);
            double startingBalance = character.getBalance();

            Shop shop = new Shop();
            int itemPrice = shop.getItems().get(0).getPrice();

            System.setIn(new ByteArrayInputStream("1\n".getBytes()));
            shop.open(character, 1);

            System.setIn(new ByteArrayInputStream("1\n".getBytes()));
            shop.open(character, 1);

            Assert.assertEquals(2, character.getInventory().size());
            Assert.assertEquals(startingBalance - (itemPrice * 2), character.getBalance(), 0.0001);
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testEnergyItemStockLimit() {
        InputStream originalIn = System.in;
        try {
            Character character = new Character("Sam", "Male", 80, 10, 5);
            Shop shop = new Shop();

            // Find an item that increases energy so its stock should be limited to 1.
            int energyIndex = findEnergyBoostingIndex(shop.getItems());
            Assert.assertTrue("Expected at least one energy-boosting item.", energyIndex >= 0);

            String input = (energyIndex + 1) + "\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            shop.open(character, 1);

            // Second purchase in same stock cycle should be blocked.
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            shop.open(character, 1);

            Assert.assertEquals(1, character.getInventory().size());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testNonEnergyItemStockLimitAndRefresh() {
        InputStream originalIn = System.in;
        try {
            Character character = new Character("Sam", "Male", 80, 10, 5);
            Shop shop = new Shop();

            // Find a non-energy-boosting item so its stock should be limited to 2.
            int nonEnergyIndex = findNonEnergyBoostingIndex(shop.getItems());
            Assert.assertTrue("Expected at least one non-energy-boosting item.", nonEnergyIndex >= 0);

            String input = (nonEnergyIndex + 1) + "\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            shop.open(character, 1);

            System.setIn(new ByteArrayInputStream(input.getBytes()));
            shop.open(character, 1);

            // Third purchase should be blocked in the same stock cycle.
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            shop.open(character, 1);

            Assert.assertEquals(2, character.getInventory().size());

            // Refresh stock after 2 turns and buy again.
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            shop.open(character, 3);

            Assert.assertEquals(3, character.getInventory().size());
        } finally {
            System.setIn(originalIn);
        }
    }

    // Helper to find an item that boosts energy.
    private int findEnergyBoostingIndex(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getEnergyEffect() > 0) {
                return i;
            }
        }
        return -1;
    }

    // Helper to find any item that does not boost energy.
    private int findNonEnergyBoostingIndex(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getEnergyEffect() <= 0) {
                return i;
            }
        }
        return -1;
    }
}
