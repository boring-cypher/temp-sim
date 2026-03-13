package sims.npc;

import sims.character.Character;
import sims.character.Person;

public abstract class NPC extends Person {
    private final String relationship;
    private int happiness;

    public NPC(String name, String gender, String relationship, int happiness) {
        super(name, gender);
        this.relationship = relationship;
        this.happiness = happiness;
    }

    public void statCap() {
        if (this.happiness > 100) {
            this.happiness = 100;
        }
    }

    public String getRelationship() {
        return relationship;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int charisma) {
        this.happiness = charisma;
    }

    public abstract void interact(NPCAction action, Character player);

    protected void unsupportedAction(NPCAction action) {
        System.out.println(getName() + " does not support action: " + action);
    }
}
