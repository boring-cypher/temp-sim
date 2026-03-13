package sims.job;

import sims.character.Character;

public class CafeAssistantJob extends Job {
    public CafeAssistantJob(){
        super("Cafe Assistant", "You work at cloud cafe as a cafe assistant",
                80, 2, true);
        this.energyCost = 2;
        this.socialEffect = 3;
        this.happinessEffect = 2;
        this.knowledgeEffect = 0;
    }

    @Override
    public void workShift(Character character) {
        super.workShift(character);

        //Tip system
        int tips = (int)(Math.random()*16) + 5;
        character.getAccount().deposit(tips);
        System.out.println("You received $" + tips + " in tips from customers!");

        //Free Food/Drink Perk
        if (Math.random() < 0.4){
            System.out.println("You grabbed a free drink after your shift!");
        }

        //Rush Hour
        if (Math.random() < 0.3){
            character.getAccount().deposit(30);
            System.out.println("Rush hour! Extra tips but exhausting work. +$30");
        }

        //TODO: Receive item rewards (Drinks/Food not physical products)
        //Brand Perks: If you work at a cafe long enough, you receive free food/drinks

        //TODO: At a perk system
        // Working at locations lets character buy items cheaper later
        //character.unlockPerk("CafeDiscount")

    }
}
