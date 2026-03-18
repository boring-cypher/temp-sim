package sims.event;

import sims.character.Character;
import sims.job.CafeAssistantJob;
import sims.job.Job;
import sims.job.RetailPromoterJob;
import sims.job.TutorJob;
import sims.shop.Shop;
import sims.util.PromptReader;
import sims.util.UI;

import java.util.ArrayList;
import java.util.List;

public class TurnProcessor {
    private final EffectRunner effectRunner = new EffectRunner();
    // Reuse the same shop so stock persists across turns.
    private final Shop shop = new Shop();

    protected void playTurn(
            Character character,
            int turnNo,
            int maxEnergy,
            List<Events> eventsThisTurn,
            Phase phase,
            List<Events> randomEventList,
            StoryState storyState)
    {
        TurnState turnState = new TurnState(maxEnergy);

        if (phase == Phase.RECESS) {
            TurnProcessor.triggerJobSelectionEvent(character);
        }

        // reset energy item usage for the new turn
        character.resetEnergyItemUsage();

        UI.printTurnHeader(turnNo, turnState.getEnergyLeft());
        UI.printStatus(
                turnState.getEnergyLeft(),
                maxEnergy,
                character.getHappiness(),
                character.getSocial(),
                character.getKnowledge(),
                character.getBalance()
        );
        UI.printBlankLines(1);

        while (turnState.getEnergyLeft() > 0) {
            System.out.println("\nEnergy left: " + turnState.getEnergyLeft());

            List<String> categories = getCategories(eventsThisTurn, phase, character);
            if (categories.isEmpty()) {
                System.out.println("No categories available this turn.");
                break;
            }

            int catChoice = chooseCategory(categories, character, turnState.getEnergyLeft(), maxEnergy);
            UI.printBlankLines(1);

            if (catChoice == -1) {
                break;
            }

            if (catChoice == -2) {
                continue;
            }

            String chosenCategory = categories.get(catChoice);

            if (chosenCategory.equalsIgnoreCase("Shop")) {
                shop.open(character, turnNo);
                UI.printBlankLines(1);
                continue;
            }

            if (chosenCategory.equalsIgnoreCase("Use Item")) {
                handleItemUsage(character, turnState);
                UI.printBlankLines(1);
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
                UI.printBlankLines(1);

                continue;
            }

            if (chosenCategory.equalsIgnoreCase("Quit Job")) {
                if (character.quitJob()){
                    System.out.println("You are now unemployed.");
                }
                UI.printBlankLines(1);
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

            applyChoice(chosenChoice, character, turnState, maxEnergy, storyState);
            triggerRandomEvent(turnState, phase, randomEventList, character, maxEnergy, storyState);
            UI.printBlankLines(1);
            selectedEvent.choices().remove(chosenChoice);
        }

        System.out.println("\nTurn " + turnNo + " ended.");
        UI.printBlankLines(5);
    }

    protected void applyChoice(
            EventChoice chosen,
            Character character,
            TurnState turnState,
            int maxEnergy,
            StoryState storyState
    ) {
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
            if (!paid) {
                System.out.println("Not enough balance for that choice.");
                turnState.gainEnergy(chosen.getEnergyCost());
                return;
            }
        }

        if (chosen.getSetProjectRoute() != null) {
            storyState.setProjectRoute(chosen.getSetProjectRoute());
        }

        effectRunner.executeChoice(chosen, character);

    }

    protected static void triggerJobSelectionEvent(Character character) {
        if (character.hasJob()) {
            System.out.println("\nYou already have a job: " + character.getCurrentJob().getName());
            return;
        }

        System.out.println("\n=== Special Event: Holiday Job Hunt ===");
        System.out.println("Recess week has started, and you decide to look for a part-time job.");

        List<Job> availableJobs = List.of(
                new CafeAssistantJob(),
                new RetailPromoterJob(),
                new TutorJob()
        );

        for (int i = 0; i < availableJobs.size(); i++) {
            Job job = availableJobs.get(i);
            System.out.println((i + 1) + ")" + job.getName());
            System.out.println(" " + job.getDesc());
            System.out.println(" Salary: $" + job.getSalary()
                    + " | Shift Energy: " + job.getShiftEnergyCost()
                    + " | Minimum shifts before quitting: " + job.getMinTurnsRequired());
        }

        System.out.println((availableJobs.size() + 1) + ") Do not take a job");
        int choice = TurnProcessor.getUserChoice(availableJobs.size() + 1);

        if (choice == availableJobs.size()) {
            System.out.println("You decided not to take a job this recess.");
            return;
        }

        Job selectedJob = availableJobs.get(choice);
        character.setCurrentJob(selectedJob);
        System.out.println("You get hired as a " + selectedJob.getName() + "!");
    }

    private void handleItemUsage(Character character, TurnState turnState) {
        if (character.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
            UI.printBlankLines(1);
            return;
        }

        System.out.println("\nChoose an item to use:");
        character.displayInventory();
        System.out.println((character.getInventory().size() + 1) + ") Cancel");

        int choice = getUserChoice(character.getInventory().size() + 1);
        if (choice == character.getInventory().size()) {
            System.out.println("Cancelled item usage.");
            UI.printBlankLines(1);
            return;
        }

        character.useItem(choice, turnState);
        UI.printBlankLines(1);
    }

    private EventChoice selectChoice(Events selectedEvent, TurnState turnState, Character character) {
        List<EventChoice> choices = selectedEvent.choices();

        if (choices == null || choices.isEmpty()) {
            System.out.println("No choices available for this event.");
            UI.printBlankLines(1);
            return null;
        }

        System.out.println("\nEnergy left: " + turnState.getEnergyLeft());
        System.out.println("\nChoices:");

        for (int i = 0; i < choices.size(); i++) {
            EventChoice c = choices.get(i);
            String costDisplay = c.getMoneyCost() > 0 ? ", Cost: $" + c.getMoneyCost() : "";
            String energyDisplay = c.getEnergyCost() < 0 ? "+" + (-c.getEnergyCost()) : String.valueOf(c.getEnergyCost());
            System.out.println((i + 1) + ") " + c.getDescription() + " [Energy: " + energyDisplay + costDisplay + "]");
        }


        int choiceIndex = getUserChoice(choices.size());
        EventChoice chosen = choices.get(choiceIndex);
        UI.printBlankLines(1);

        if (!turnState.hasEnoughEnergy(chosen.getEnergyCost())) {
            System.out.println("Not enough energy for that choice.\nPick another option.");
            UI.printBlankLines(1);
            return null;
        }

        if (chosen.getMoneyCost() > 0 && character.getAccount().getBalance() < chosen.getMoneyCost()) {
            System.out.println("Not enough balance for that choice.\nPick another option.");
            UI.printBlankLines(1);
            return null;
        }

        return chosen;
    }

    private Events selectEvent(List<Events> categoryEvents, String chosenCategory) {
        if (categoryEvents.isEmpty()) {
            System.out.println("No events available for category: " + chosenCategory);
            UI.printBlankLines(1);
            return null;
        }

        if (categoryEvents.size() == 1) {
            Events selectedEvent = categoryEvents.get(0);
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
        UI.printBlankLines(1);

        System.out.println("\n=== " + selectedEvent.title() + " ===");
        System.out.println(selectedEvent.desc());

        return selectedEvent;
    }

    private int chooseCategory(List<String> categories, Character character, int energyLeft, int maxEnergy) {
        System.out.println("\nChoose a category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ") " + categories.get(i));
        }

        System.out.println((categories.size() + 1) + ") View stats");
        System.out.println((categories.size() + 2) + ") End turn early");

        int catChoice = getUserChoice(categories.size() + 2);
        UI.printBlankLines(1);

        if (catChoice == categories.size()) {
            UI.printStatus(
                    energyLeft,
                    maxEnergy,
                    character.getHappiness(),
                    character.getSocial(),
                    character.getKnowledge(),
                    character.getBalance()
            );
            if (character.getCurrentJob() != null) {
                System.out.println("\n=== Job Status ===");
                character.getCurrentJob().printJobStatus();
            }
            UI.printBlankLines(1);
            return -2;
        }

        if (catChoice == categories.size() + 1) {
            System.out.println("You ended the turn early.");
            UI.printBlankLines(1);
            return -1;
        }

        return catChoice;
    }

    private static List<Events> getCategoryEvents(List<Events> eventsThisTurn, String chosenCategory) {
        return eventsThisTurn.stream()
                .filter(e -> e.actionCategory() != null && e.actionCategory().equalsIgnoreCase(chosenCategory))
                .toList();
    }

    private static List<String> getCategories(List<Events> eventsThisTurn, Phase currentPhase, Character character) {
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

    private static int getUserChoice(int max) {
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

    private void triggerRandomEvent(
            TurnState turnState,
            Phase phase,
            List<Events> randomEventList,
            Character character,
            int maxEnergy,
            StoryState storyState
    ) {
        if (Math.random() >= 0.3 || turnState.getEnergyLeft() <= 0) {
            return;
        }

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

        EventChoice choice = null;
        while (choice == null) {
            choice = selectChoice(randomEvent, turnState, character);
        }

        applyChoice(choice, character, turnState, maxEnergy, storyState);
    }
}
