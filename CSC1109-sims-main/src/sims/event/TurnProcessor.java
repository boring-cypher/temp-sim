package sims.event;

import sims.character.Character;
import sims.job.Job;
import sims.shop.Shop;
import sims.util.PromptReader;
import sims.util.UI;

import java.util.ArrayList;
import java.util.List;

public class TurnProcessor {
    private final EffectRunner effectRunner = new EffectRunner(); // to call executeChoice

    protected void playTurn(Character character, int turnNo, int maxEnergy, List<Events> eventsThisTurn, Phase phase, List<Events> randomEventList) {
        TurnState turnState = new TurnState(maxEnergy);

        UI.printTurnHeader(turnNo, turnState.getEnergyLeft());

        while (turnState.getEnergyLeft() > 0) {
            System.out.println("\nEnergy left: " + turnState.getEnergyLeft());

            List<String> categories = getCategories(eventsThisTurn, phase, character);

            if (categories.isEmpty()) {
                System.out.println("No categories available this turn.");
                break;
            }

            int catChoice = chooseCategory(categories, character, turnState.getEnergyLeft(), maxEnergy);

            if (catChoice == -1) {
                break; // end turn early
            }

            if (catChoice == -2) {
                continue; // viewed stats
            }

            String chosenCategory = categories.get(catChoice);

            if (chosenCategory.equalsIgnoreCase("Shop")) {
                Shop shop = new Shop();
                shop.open(character);
                continue;
            }

            if (chosenCategory.equalsIgnoreCase("Use Item")) {
                handleItemUsage(character, turnState);
                continue;
            }

            if (chosenCategory.equalsIgnoreCase("Work")) {
                Job currentJob = character.getCurrentJob();

                if (currentJob == null) {
                    System.out.println("You do not currently have a job.");
                    continue;
                }

                int shiftCost = currentJob.getShiftEnergyCost();

                if (!turnState.hasEnoughEnergy(shiftCost)) {
                    System.out.println("Not enough energy to work this shift.");
                    continue;
                }

                System.out.println("\n=== Work Shift: " + currentJob.getName() + " ===");
                System.out.println(currentJob.getDesc());
                System.out.println("This shift cost " + shiftCost + " energy.");

                turnState.spendEnergy(shiftCost);
                currentJob.workShift(character);

                UI.printStatus(turnState.getEnergyLeft(), maxEnergy, character.getHappiness(), character.getSocial(), character.getKnowledge(), character.getBalance());

                continue;
            }

            if (chosenCategory.equalsIgnoreCase("Quit Job")) {
                character.quitJob();
                continue;
            }

            List<Events> categoryEvents = getCategoryEvents(eventsThisTurn, chosenCategory);

            Events selectedEvent = selectEvent(categoryEvents, chosenCategory);
            if (selectedEvent == null) {
                continue;
            }

            EventChoice chosenChoice = selectChoice(selectedEvent, turnState, character);
            if (chosenChoice == null) {
                continue;
            }

            applyChoice(chosenChoice, character, turnState, maxEnergy);

            //random event randomly happens after a user applies a main event choice
            triggerRandomEvent(turnState, phase, randomEventList, character, maxEnergy);
        }

        System.out.println("\nTurn " + turnNo + " ended.");
    }

    protected void applyChoice(EventChoice chosen, Character character, TurnState turnState, int maxEnergy) {
        boolean spent = turnState.spendEnergy(chosen.getEnergyCost());
        if (!spent) {
            System.out.println("Not enough energy for that choice.");
            return;
        }

        System.out.println("\nYou picked: " + chosen.getDescription());

        if (chosen.getMoneyCost() > 0) {
            System.out.println("You spent: $" + chosen.getMoneyCost());
        }

        if (chosen.getMoneyCost() > 0) {
            boolean paid = character.getAccount().withdraw(chosen.getMoneyCost());
            if (!paid){
                System.out.println("Not enough balance for that choice.");
                turnState.gainEnergy(chosen.getEnergyCost());
                return;
            }
        }

        effectRunner.executeChoice(chosen, character);

        UI.printStatus(
                turnState.getEnergyLeft(),
                maxEnergy,
                character.getHappiness(),
                character.getSocial(),
                character.getKnowledge(),
                character.getBalance()
        );
    }

    protected static void handleItemUsage(Character character, TurnState turnState) {

        if (character.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

        System.out.println("\nChoose an item to use:");
        character.displayInventory();
        System.out.println((character.getInventory().size() + 1) + ") Cancel");

        int choice = getUserChoice(character.getInventory().size() + 1);

        if (choice == character.getInventory().size()) {
            System.out.println("Cancelled item usage.");
            return;
        }

        character.useItem(choice, turnState);
    }

    protected static EventChoice selectChoice(Events selectedEvent, TurnState turnState, Character character) {
        List<EventChoice> choices = selectedEvent.choices();

        if (choices == null || choices.isEmpty()) {
            System.out.println("No choices available for this event.");
            return null;
        }

        System.out.println("\nEnergy left: " + turnState.getEnergyLeft());
        System.out.println("\nChoices:");
        for (int i = 0; i < choices.size(); i++) {
            EventChoice c = choices.get(i);
            String costDisplay = c.getMoneyCost() > 0 ? ", Cost: $" + c.getMoneyCost() : "";
            System.out.println((i + 1) + ") " + c.getDescription()
                    + " [Energy: " + c.getEnergyCost()
                    + costDisplay + "]");
        }

        int choiceIndex = getUserChoice(choices.size());
        EventChoice chosen = choices.get(choiceIndex);

        if (!turnState.hasEnoughEnergy(chosen.getEnergyCost())) {
            System.out.println("Not enough energy for that choice. Pick another option.");
            return null;
        }

        if (chosen.getMoneyCost() > 0 && character.getAccount().getBalance() < chosen.getMoneyCost()) {
            System.out.println("Not enough balance for that choice. Pick another option.");
            return null;
        }

        return chosen;
    }

    protected static Events selectEvent(List<Events> categoryEvents, String chosenCategory) {
        if (categoryEvents.isEmpty()) {
            System.out.println("No events available for category: " + chosenCategory);
            return null;
        }

        if (categoryEvents.size() == 1) {
            Events selectedEvent = categoryEvents.getFirst();
            System.out.println("\n=== " + selectedEvent.title() + " ===");
            System.out.println(selectedEvent.desc());
            return selectedEvent;
        }

        System.out.println("\nChoose an event (" + chosenCategory + "):");
        for (int i = 0; i < categoryEvents.size(); i++) {
            Events ev = categoryEvents.get(i);
            System.out.println((i + 1) + ") " + ev.title() + " - " + ev.desc());
        }

        int eventIndex = getUserChoice(categoryEvents.size());
        Events selectedEvent = categoryEvents.get(eventIndex);

        System.out.println("\n=== " + selectedEvent.title() + " ===");
        System.out.println(selectedEvent.desc());

        return selectedEvent;
    }

    protected static int chooseCategory(List<String> categories, sims.character.Character character, int energyLeft, int maxEnergy) {
        System.out.println("\nChoose a category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ") " + categories.get(i));
        }
        System.out.println((categories.size() + 1) + ") View stats");
        System.out.println((categories.size() + 2) + ") End turn early");

        int catChoice = getUserChoice(categories.size() + 2);

        if (catChoice == categories.size()) {
            UI.printStatus(
                    energyLeft,
                    maxEnergy,
                    character.getHappiness(),
                    character.getSocial(),
                    character.getKnowledge(),
                    character.getBalance()
            );
            return -2;
        }

        if (catChoice == categories.size() + 1) {
            System.out.println("You ended the turn early.");
            return -1;
        }

        return catChoice;
    }

    private void triggerRandomEvent(TurnState turnState, Phase phase, List<Events> randomEventList, Character character, int maxEnergy) {
        //30% chance to trigger, only if energy left
        if (Math.random() >= 0.3 || turnState.getEnergyLeft() <= 0) return;

        //pick a random event for this phase
        List<Events> phaseRandomEvents = randomEventList.stream()
                .filter(e -> e.phase() == phase)
                .toList();

        if (phaseRandomEvents.isEmpty()) {
            System.out.println("[No random events for " + phase.getDisplayName() + "]");
            return;
        }

        Events randomEvent = phaseRandomEvents.get((int) (Math.random() * phaseRandomEvents.size()));

        System.out.println("\n[Random Event Triggered]");
        System.out.println("\n=== " + randomEvent.title() + " ===");
        System.out.println(randomEvent.desc());

        //let player pick a valid choice
        EventChoice choice = null;
        while (choice == null) {
            choice = selectChoice(randomEvent, turnState, character);
        }

        //apply choice
        applyChoice(choice, character, turnState, maxEnergy);
    }

    protected static List<Events> getCategoryEvents(List<Events> eventsThisTurn, String chosenCategory) {
        return eventsThisTurn.stream()
                .filter(e -> e.actionCategory() != null
                        && e.actionCategory().equalsIgnoreCase(chosenCategory))
                .toList();
    }

    protected static List<String> getCategories(List<Events> eventsThisTurn, Phase currentPhase, Character character) {
        List<String> categories = new ArrayList<>(
                eventsThisTurn.stream()
                        .map(Events::actionCategory)
                        .filter(c -> c != null && !c.isBlank())
                        .distinct()
                        .toList()
        );

        categories.add("Shop");

        if (!character.getInventory().isEmpty()) {
            categories.add("Use Item");
        }

        if (currentPhase == Phase.RECESS && character.hasJob()) {
            categories.add("Work");
            categories.add("Quit Job");
        }

        return categories;
    }

    protected static int getUserChoice(int max) {
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
