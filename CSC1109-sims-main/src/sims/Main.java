package sims;

import sims.character.Character;
import sims.character.CharacterCreator;
import sims.event.EventManager;

public class Main {

    public static void main(String[] args) {
        Character character = CharacterCreator.createCharacter();

        //sims.npc.NPCFactory.partner = sims.npc.NPCFactory.createPartner(character);1
        EventManager eventManager = new EventManager();

        System.out.println("\n=== Welcome to Uni Life Simulator ===");
        character.printStats();

        // Start main study-phase game flow
        eventManager.mainGameFlow(character);

        System.out.println("\n=== Game Ended ===");
        character.printStats();
        System.out.println("Thank you for playing.");
    }
}