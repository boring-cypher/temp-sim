package sims.util;
import sims.character.Character;

public class CharacterFormatter {

    public static String formatStats(Character character) {
        return "Name: " + character.getName() +
                "\nHappiness: " + character.getHappiness();
    }
}