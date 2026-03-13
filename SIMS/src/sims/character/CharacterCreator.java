package sims.character;

import sims.util.PromptReader;

public class CharacterCreator {

    public static sims.character.Character createCharacter() {
        String name = getValidName();
        String gender = selectGender();

        return new Character(
                name,
                gender,
                80,
                0,
                0
        );
    }

    private static String selectGender() {
        System.out.println("Select your character's gender:");
        System.out.println("1) Male");
        System.out.println("2) Female");

        int choice = getMenuChoice(2);
        return (choice == 0) ? "Male" : "Female";
    }

    private static int getMenuChoice(int max) {
        while (true) {
            String input = PromptReader.readWithPrompt("Enter your choice: ");
            try {
                int idx = Integer.parseInt(input) - 1;
                if (idx >= 0 && idx < max) {
                    return idx;
                }
                System.out.println("Invalid choice, try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    private static String getValidName() {
        while (true) {
            String name = PromptReader.readWithPrompt("Enter your character's name: ");
            if (name != null && !name.trim().isEmpty()) {
                return name.trim();
            }
            System.out.println("Name cannot be empty!");
        }
    }
}
