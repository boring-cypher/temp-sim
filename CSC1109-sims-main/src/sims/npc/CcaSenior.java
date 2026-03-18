package sims.npc;

import sims.character.Character;

public class CcaSenior extends NPC {
    private int respect;

    public int getRespect() {
        return respect;
    }

    public CcaSenior(String name, String gender, String relationship, int happiness, int respect) {
        super(name, gender, relationship, happiness);
        this.respect = respect;
    }

    @Override
    public void interact(NPCAction action, Character player) {
        switch (action) {
            case NPCAction.ASK_TRAINING_TIPS -> askForTrainingTips(player);
            case NPCAction.JOIN_EVENT -> joinUpcomingEvent(player);
            case NPCAction.ASK_RECOMMENDATION -> askForRecommendation(player);
            default -> unsupportedAction(action);
        }
    }

    private void askForTrainingTips(sims.character.Character player) {
        respect += 1;
        setHappiness(getHappiness() + 1);
        statCap();
    }

    private void joinUpcomingEvent(sims.character.Character player) {
        respect += 2;
        setHappiness(getHappiness() + 2);
        statCap();
    }

    private void askForRecommendation(sims.character.Character player) {
        if (respect >= 5) {
            setHappiness(getHappiness() + 2);
        } else {
            setHappiness(getHappiness() - 2);
        }

        statCap();
    }
}