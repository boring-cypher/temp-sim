package sims.npc;

import sims.character.Character;

public class ProjectMate extends NPC {

    private int missedDeadlinesByPlayer;
    private int tension;

    public ProjectMate(String name, String gender, String relationship, int happiness,
                       int missedDeadlinesByPlayer, int tension) {
        super(name, gender, relationship, happiness);
        this.missedDeadlinesByPlayer = missedDeadlinesByPlayer;
        this.tension = tension;
    }

    @Override
    public void interact(NPCAction action, sims.character.Character player) {
        switch (action) {
            case DISCUSS_TASK_SPLIT -> discussTaskSplit(player);
            case APOLOGIZE -> apologize(player);
            case WORK_SPRINT -> workSprint(player);
            default -> unsupportedAction(action);
        }
    }

    private void discussTaskSplit(sims.character.Character player) {
        if (tension < 40) {
            setHappiness(getHappiness() + 2);
        } else if (tension < 70) {
            setHappiness(getHappiness() - 1);
        } else {
            setHappiness(getHappiness() - 3);
        }

        statCap();
    }

    private void apologize(Character player) {
        tension = Math.max(0, tension - 15);
        setHappiness(getHappiness() + 3);
        statCap();
    }

    private void workSprint(sims.character.Character player) {
        if (tension > 60) {
            setHappiness(getHappiness() - 2);
        } else {
            setHappiness(getHappiness() + 2);
        }

        statCap();
    }

    public int getMissedDeadlinesByPlayer() {
        return missedDeadlinesByPlayer;
    }

    public int getTension() {
        return tension;
    }

    public void increaseMissedDeadlines() {
        missedDeadlinesByPlayer++;
        tension += 10;
    }

    public void reduceTension(int amount) {
        tension = Math.max(0, tension - amount);
    }
}