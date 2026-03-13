package sims.item;

import sims.character.Character;
import sims.event.TurnState;

public interface Usable {
    void use(Character character, TurnState turnState);
}
