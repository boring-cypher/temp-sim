package sims.shop;

import sims.character.Character;
import sims.item.Item;
import sims.item.ItemBuilder;
import sims.util.PromptReader;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private final ArrayList<Item> items;

    public Shop() {
        // Build a default item list from the ItemBuilder.
        List<Item> built = ItemBuilder.buildItems();
        this.items = new ArrayList<>(built);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void displayItems() {
        // Print all items with numbering for user selection.
        if (items.isEmpty()) {
            System.out.println("The shop is out of stock.");
            return;
        }

        System.out.println("\nItems for sale:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ") " + item);
        }
    }

    public void open(Character character) {
        // Main shop flow: show items, validate selection, process purchase.
        if (character == null) {
            System.out.println("No character available to shop.");
            return;
        }

        if (items.isEmpty()) {
            System.out.println("The shop is out of stock.");
            return;
        }

        System.out.println("\n=== Shop ===");
        displayItems();
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

        if (character.getAccount().getBalance() < selected.getPrice()) {
            System.out.println("Not enough balance to buy " + selected.getName() + ".");
            return;
        }

        // Deduct money and add item to inventory if paid successfully.
        boolean paid = character.getAccount().withdraw(selected.getPrice());
        if (!paid) {
            System.out.println("Not enough balance to buy " + selected.getName() + ".");
            return;
        }

        character.addItem(selected);
        System.out.println("Purchased: " + selected.getName());
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
