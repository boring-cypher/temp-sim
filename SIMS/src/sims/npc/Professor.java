package sims.npc;

import sims.character.Character;

public class Professor extends NPC {

    private int tolerance;
    private int consultsUsedThisWeek;
    private boolean extensionUsed;

    public Professor(String name, String gender, String relationship, int happiness,
                     int tolerance, int gradingStrictness) {
        super(name, gender, relationship, happiness);
        this.tolerance = tolerance;
        this.consultsUsedThisWeek = 0;
        this.extensionUsed = false;
    }

    @Override
    public void interact(NPCAction action, sims.character.Character player) {
        switch (action) {
            case ASK_FEEDBACK -> askForFeedback(player);
            case REQUEST_EXTENSION -> requestExtension(player);
            case BOOK_CONSULT -> bookConsultation(player);
            default -> unsupportedAction(action);
        }
    }

    private void askForFeedback(Character player) {
        tolerance--;

        if (tolerance >= 3) {
            setHappiness(getHappiness() + 2);
        } else if (tolerance >= 1) {
            setHappiness(getHappiness() + 1);
        } else {
            setHappiness(getHappiness() - 2);
        }

        statCap();
    }

    private void requestExtension(sims.character.Character player) {
        if (!extensionUsed) {
            extensionUsed = true;

            if (getHappiness() >= 50) {
                setHappiness(getHappiness() + 1);
            } else {
                setHappiness(getHappiness() - 1);
            }
        } else {
            setHappiness(getHappiness() - 3);
        }

        statCap();
    }

    private void bookConsultation(sims.character.Character player) {
        if (consultsUsedThisWeek < 2) {
            consultsUsedThisWeek++;

            if (player.getKnowledge() < 50) {
                setHappiness(getHappiness() + 2);
            } else {
                setHappiness(getHappiness() + 1);
            }
        } else {
            setHappiness(getHappiness() - 2);
        }

        statCap();
    }

    public int gettolerance() {
        return tolerance;
    }

    public int getConsultsUsedThisWeek() {
        return consultsUsedThisWeek;
    }

    public boolean isExtensionUsed() {
        return extensionUsed;
    }
}