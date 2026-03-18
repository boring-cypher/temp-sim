package sims.job;

import sims.character.Character;
import sims.item.Item;
import sims.item.ItemBuilder;

public class CafeAssistantJob extends Job {
    public CafeAssistantJob(){
        super("Cafe Assistant", "You work at Cloud Cafe as a cafe assistant",
                80, 2, true);
        this.energyCost = 2;
        this.socialEffect = 3;
        this.happinessEffect = 2;
        this.knowledgeEffect = 0;
    }

    @Override
    protected void promoteToLevel2(){
        super.promoteToLevel2();
        this.socialEffect += 3;
        this.freeItemPerkUnlocked = true;
        System.out.println("""
                You got promoted to Senior Cafe Assistant!\
                
                Your experience behind the counter shows.\
                
                Customer trust you more and you social stats increase more\
                
                Salary increased by $20, Social gained increased by 3""");
    }

    @Override
    protected void promoteToLevel3(){
        super.promoteToLevel3();
        this.happinessEffect += 3;
        this.discountPerkUnlocked = true;
        setDiscountAmount(3);

        System.out.println("""
                You got promoted to Cafe Shift Lead!\
                
                You handle busy shifts with ease and help manage the cafe floor.\
                
                Your leadership earns you better pay and happier customers\
                
                Salary increased by $30, Happiness gained increased by 3""");
    }

    @Override
    protected Item getFreeRewardItem(){
        if (!freeItemPerkUnlocked) return null;

        int roll = (int)(Math.random() * 3);
        if (roll == 0) return ItemBuilder.coffee();
        if (roll == 1) return ItemBuilder.energyDrink();
        return ItemBuilder.dessertBox();
    }

    @Override
    public boolean canDiscountItem(Item item){
        String name = item.getName();
        return discountPerkUnlocked &&
                (name.equalsIgnoreCase("Dessert Box") ||
                name.equalsIgnoreCase("Coffee") ||
                name.equalsIgnoreCase("Energy Drink"));
    }

    @Override
    public String getPerkDescription(){
        if (getLevel() == 1){
            return "No perks yet";
        }
        if (getLevel() == 2){
            return "Chance to receive free drinks or desserts.";
        }
        return "Free items and discount on Coffee, Energy Drink and Dessert box.";
    }

    @Override
    public void workShift(Character character) {
        super.workShift(character);

        Item reward = getFreeRewardItem();
        if (reward != null && Math.random() < 0.3) {
            character.addItem(reward);
            System.out.println("Job perk! You received a free " + reward.getName() + "!");
        }

        //Tip system
        int tips = (int)(Math.random()*16) + 5;
        character.getAccount().deposit(tips);
        System.out.println("You received $" + tips + " in tips from customers!");

        //Rush Hour
        if (Math.random() < 0.3){
            character.getAccount().deposit(30);
            System.out.println("Rush hour! Extra tips but exhausting work. +$30");
        }
    }
}
