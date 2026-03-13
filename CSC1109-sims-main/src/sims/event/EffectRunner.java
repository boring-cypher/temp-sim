package sims.event;

import sims.character.Character;
import sims.npc.NPC;

import java.util.List;
import java.util.Map;

public class EffectRunner {

    protected void executeChoice(EventChoice choice, Character character) {
        if (choice.getEffects() != null && !choice.getEffects().isEmpty()) { // every Event choice will have a effect attribute which belongs to the sims.npc.NPC
            System.out.println("Effects:");
            choice.getEffects().forEach((stat, amount) ->
                    System.out.println(stat + " " + (amount >= 0 ? "+" : "") + amount) // print effect of the character
            );
            applyEffects(character, choice.getEffects()); // apply effects on the character
        }
        applyNpcInteractions(choice, character);

        List<Branch> branches = choice.getBranches(); // retrieve branches
        applyBranch(branches, character);

        character.statCap();
    }

    private static void applyNpcInteractions(EventChoice choice, Character character){
        if (choice.hasNPCInteraction()) { // if sims.npc.NPC interaction is involved
            for (NPC npc : choice.getAffectedNPCs()) { // loop through each sims.npc.NPC that is involved in the event
                System.out.println("Before interaction -> " + npc.getName() +
                        " | Happiness: " + npc.getHappiness());
                npc.interact(choice.getAction(), character); // call the interact function in sims.npc.NPC and send the action being performed by the sims.npc.NPC
                System.out.println("After interact  -> " + npc.getName() +
                        " | Happiness: " + npc.getHappiness());
            }
        }
    }

    private void applyBranch(List<Branch> branches, Character character){
        if (branches != null && !branches.isEmpty()) { // check if branch exist
            Branch branch = branches.get((int) (Math.random() * branches.size()));

            System.out.println("\nBranch outcome:");
            if (branch.effects() != null && !branch.effects().isEmpty()) {
                branch.effects().forEach((stat, amount) ->
                        System.out.println(stat + " " + (amount >= 0 ? "+" : "") + amount)
                );
                applyEffects(character, branch.effects());
            }
        }
    }

    private static void applyEffects(Character character, Map<String, Integer> effects) {
        if (effects == null || effects.isEmpty()) {
            return;
        }

        effects.forEach((stat, amount) -> {
            try {
                switch(stat.toLowerCase()){
                    case "happiness":
                        character.changeHappiness(amount);
                        break;
                    case "socialise", "social":
                        character.changeSocial(amount);
                        break;
                    case "knowledge":
                        character.changeKnowledge(amount);
                        break;
                    case "balance", "money":
                        double balance = character.getAccount().getBalance();
                        double toWithdraw = Math.min(-amount, balance);

                        if (toWithdraw > 0) {
                            character.getAccount().withdraw(toWithdraw);
                        }

                        if (-amount > balance) {
                            System.out.println("Balance cannot go below $0. Spent $" + toWithdraw + " instead.");
                        }
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Effect skipped: " + e.getMessage());
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
        });
        character.statCap();
    }
}
