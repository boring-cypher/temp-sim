package sims;

import sims.character.Character;
import sims.character.CharacterCreator;
import sims.event.EventManager;
import sims.util.CharacterFormatter;
import sims.util.UI;
import sims.Summary.SummaryPage;

public class Main {

    public static void main(String[] args) {
        //Print welcome message
        UI.printWelcome();

        Character character = CharacterCreator.createCharacter();

        //sims.npc.NPCFactory.partner = sims.npc.NPCFactory.createPartner(character);1
        EventManager eventManager = new EventManager();
        System.out.println(CharacterFormatter.formatStats(character));

        // Start main study-phase game flow
        eventManager.mainGameFlow(character);

        System.out.println("\n=== Game Ended ===");
        SummaryPage.printSummary(character, eventManager.getStoryState());
        System.out.println("Thank you for playing.");
    }
}
