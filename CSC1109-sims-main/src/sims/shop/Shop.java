package sims.shop;

import sims.character.Character;
import sims.item.Item;
import sims.item.ItemBuilder;
import sims.item.ItemType;
import sims.util.PromptReader;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private final ArrayList<Item> items;
    // Per-item stock counts aligned with items list.
    private final ArrayList<Integer> stock;
    // Turn number when stock was last refreshed.
    private int lastRefreshTurn;

    public Shop() {
        // Build a default item list from the ItemBuilder.
        List<Item> built = ItemBuilder.buildItems();
        this.items = new ArrayList<>(built);
        this.stock = new ArrayList<>(items.size());
        // Initialize stock on first construction.
        refreshStock();
        this.lastRefreshTurn = -1;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void displayItems(Character character) {
        // Print all items with numbering for user selection.
        if (items.isEmpty()) {
            System.out.println("The shop is out of stock.");
            return;
        }


        System.out.println("=================================================");
        System.out.println("             SHOP - ITEMS FOR SALE");
        System.out.println("=================================================\n");

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            int displayPrice = item.getPrice();
            if (character.getCurrentJob() != null) {
                displayPrice = character.getCurrentJob().getDiscountedPrice(item);
            }

            System.out.println((i + 1) + ")");
            System.out.println("Name: " + item.getName());
            if (displayPrice < item.getPrice()) {
                System.out.println("Price: " + displayPrice + " (Discounted from $" + item.getPrice() + ")");
            } else {
                System.out.println("Price: $" + item.getPrice());
            }
            System.out.println("Description: " + item.getDesc());
            int currentStock = getStock(i);
            if (currentStock <= 0) {
                System.out.println("Stock: 0 (Out of stock)");
            } else {
                System.out.println("Stock: " + currentStock);
            }

            System.out.print("Stat Changes: ");

            boolean hasEffect = false;

            if (item.getKnowledgeEffect() != 0) {
                System.out.print((item.getKnowledgeEffect() > 0 ? "+" : "")
                        + item.getKnowledgeEffect() + " Knowledge ");
                hasEffect = true;
            }

            if (item.getHappinessEffect() != 0) {
                System.out.print((item.getHappinessEffect() > 0 ? "+" : "")
                        + item.getHappinessEffect() + " Happiness ");
                hasEffect = true;
            }

            if (item.getSocialEffect() != 0) {
                System.out.print((item.getSocialEffect() > 0 ? "+" : "")
                        + item.getSocialEffect() + " Social ");
                hasEffect = true;
            }

            if (item.getEnergyEffect() != 0) {
                System.out.print((item.getEnergyEffect() > 0 ? "+" : "")
                        + item.getEnergyEffect() + " Energy ");
                hasEffect = true;
            }

            if (!hasEffect) {
                System.out.print("None");
            }

            System.out.println();
            System.out.println("-------------------------------------------------\n");
        }
    }

    public void open(Character character, int currentTurn) {
        // Main shop flow: show items, validate selection, process purchase.
        if (character == null) {
            System.out.println("No character available to shop.");
            return;
        }

        // Refresh stock every 2 turns.
        refreshIfNeeded(currentTurn);

        if (!hasAnyStock()) {
            System.out.println("The shop is out of stock.");
            return;
        }

        if (items.isEmpty()) {
            System.out.println("The shop is out of stock.");
            return;
        }

        displayItems(character);
        System.out.println((items.size() + 1) + ") Cancel");

        // Get a valid menu choice.
        int choice = getUserChoice(items.size() + 1);
        if (choice == items.size()) {
            System.out.println("Cancelled purchase.");
            return;
        }

        Item selected = items.get(choice);
        if (selected == null) {
            System.out.println("Invalid item.");
            return;
        }

        if (getStock(choice) <= 0) {
            System.out.println(selected.getName() + " is out of stock.");
            return;
        }

        int finalPrice = selected.getPrice();
        if (character.getCurrentJob() != null) {
            finalPrice =  character.getCurrentJob().getDiscountedPrice(selected);
        }


        if (character.getAccount().getBalance() < finalPrice) {
            System.out.println("Not enough balance to buy " + selected.getName() + ".");
            return;
        }

        // Deduct money and add item to inventory if paid successfully.
        boolean paid = character.getAccount().withdraw(finalPrice);
        if (!paid) {
            System.out.println("Not enough balance to buy " + selected.getName() + ".");
            return;
        }

        character.addItem(selected);
        decrementStock(choice);
        System.out.println("Purchased: " + selected.getName());
    }

    // Treat energy-boosting items as limited to 1 stock.
    private boolean isEnergyBoost(Item item) {
        return item.getEnergyEffect() > 0 || item.getItemType() == ItemType.ENERGY;
    }

    // Max stock per item type.
    private int maxStockFor(Item item) {
        return isEnergyBoost(item) ? 1 : 2;
    }

    // Reset stock for all items to their max counts.
    private void refreshStock() {
        stock.clear();
        for (Item item : items) {
            stock.add(maxStockFor(item));
        }
    }

    // Refresh stock only when enough turns have passed.
    public void refreshIfNeeded(int currentTurn) {
        if (currentTurn < 1) {
            return;
        }

        // First call only sets the baseline; no refresh.
        if (lastRefreshTurn < 0) {
            lastRefreshTurn = currentTurn;
            return;
        }

        // Refresh every 2 turns.
        if (currentTurn - lastRefreshTurn >= 2) {
            refreshStock();
            lastRefreshTurn = currentTurn;
        }
    }

    // True when at least one item is still in stock.
    private boolean hasAnyStock() {
        for (int count : stock) {
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    // Safe stock lookup.
    private int getStock(int index) {
        if (index < 0 || index >= stock.size()) {
            return 0;
        }
        return stock.get(index);
    }

    // Decrement stock for a purchased item.
    private void decrementStock(int index) {
        if (index < 0 || index >= stock.size()) {
            return;
        }
        int current = stock.get(index);
        if (current > 0) {
            stock.set(index, current - 1);
        }
    }

    private int getUserChoice(int max) {
        // Read input until the user enters a number
        int choiceIndex = -1;
        boolean valid = false;

        while (!valid) {
            String input = PromptReader.readWithPrompt("Enter your choice: ");
            try {
                choiceIndex = Integer.parseInt(input) - 1;
                if (choiceIndex >= 0 && choiceIndex < max) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }

        return choiceIndex;
    }
}
