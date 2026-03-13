package sims.npc;

import sims.character.Character;

public class Friend extends NPC {

    private int loyalty;
    private final boolean isFreeToday;
    private final String supportStyle;
    private final int gossipNetwork;

    public Friend(String name, String gender, String relationship, int happiness,
                  int loyalty, boolean isFreeToday, String supportStyle, int gossipNetwork) {
        super(name, gender, relationship, happiness);
        this.loyalty = loyalty;
        this.isFreeToday = isFreeToday;
        this.supportStyle = supportStyle;
        this.gossipNetwork = gossipNetwork;
    }

    @Override
    public void interact(NPCAction action, Character player) {
        switch (action) {
            case VENT -> vent(player);
            case HANG_OUT -> hangOut(player);
            case BORROW_NOTES -> borrowNotes(player);
            default -> unsupportedAction(action);
        }
    }

    private void vent(sims.character.Character player) {
        loyalty += 2;

        if ("LISTENER".equalsIgnoreCase(supportStyle)) {
            setHappiness(getHappiness() + 3);
        } else if ("CHEERLEADER".equalsIgnoreCase(supportStyle)) {
            setHappiness(getHappiness() + 2);
        } else if ("COMEDIAN".equalsIgnoreCase(supportStyle)) {
            setHappiness(getHappiness() + 2);
        } else {
            setHappiness(getHappiness() + 1);
        }

        statCap();
    }

    private void hangOut(sims.character.Character player) {
        if (!isFreeToday) {
            setHappiness(getHappiness() - 1);
            statCap();
            return;
        }

        loyalty += 1;
        setHappiness(getHappiness() + 2);
        statCap();
    }

    private void borrowNotes(sims.character.Character player) {
        if (loyalty >= 5) {
            setHappiness(getHappiness() + 1);
        } else {
            setHappiness(getHappiness() - 2);
        }

        loyalty = Math.max(0, loyalty - 1);
        statCap();
    }

    public int getLoyalty() {
        return loyalty;
    }

    public boolean isFreeToday() {
        return isFreeToday;
    }

    public String getSupportStyle() {
        return supportStyle;
    }

    public int getGossipNetwork() {
        return gossipNetwork;
    }

}