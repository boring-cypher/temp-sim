package sims.item;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    public static List<Item> buildItems(){
        List<Item> items = new ArrayList<>();

        // Knowledge items
        items.add(studyGuide());
        items.add(examCrampNotes());

        // Happiness items
        items.add(comfortSnack());
        items.add(dessertBox());

        // Social items
        items.add(conversationStarterCards());
        items.add(partyAccessPass());

        // Energy items
        items.add(coffee());
        items.add(energyDrink());

        return items;
    }

    // KNOWLEDGE
    public static Item studyGuide() {
        return new Item(
                "Study Guide",
                "A concise revision guide that improves understanding.",
                15,
                5,   // knowledge
                0,   // happiness
                0,   // social
                0    // energy
        );
    }

    public static Item examCrampNotes() {
        return new Item(
                "Exam Cramp Notes",
                "Intensive summary notes that boost learning but increase stress.",
                20,
                10,
                -4,
                0,
                0
        );
    }

    // HAPPINESS
    public static Item comfortSnack() {
        return new Item(
                "Comfort Snack",
                "A small treat that lifts your mood.",
                10,
                0,
                4,
                0,
                0
        );
    }

    public static Item dessertBox() {
        return new Item(
                "Dessert Box",
                "A rich dessert set that feels great at first, but leaves you sluggish.",
                16,
                0,
                9,
                0,
                -2
        );
    }

    // SOCIAL
    public static Item conversationStarterCards() {
        return new Item(
                "Conversation Starter Cards",
                "A deck of prompts that helps you connect with others.",
                14,
                0,
                0,
                5,
                0
        );
    }

    public static Item partyAccessPass() {
        return new Item(
                "Party Access Pass",
                "Gets you into a lively social gathering, but it is exhausting.",
                22,
                0,
                0,
                10,
                -3
        );
    }

    // ENERGY
    public static Item coffee() {
        return new Item(
                "Coffee",
                "A quick caffeine boost to help you push on.",
                8,
                0,
                0,
                0,
                3
        );
    }

    public static Item energyDrink() {
        return new Item(
                "Energy Drink",
                "A strong burst of energy followed by a rough mood crash.",
                15,
                0,
                -3,
                0,
                6
        );
    }
}