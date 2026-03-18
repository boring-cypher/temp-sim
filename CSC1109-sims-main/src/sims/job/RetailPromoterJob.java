package sims.job;

import sims.character.Character;
import sims.item.Item;
import sims.item.ItemBuilder;

public class RetailPromoterJob extends Job {
    public RetailPromoterJob(){
        super("Retail Promoter", "You work at NUJI as a retail promoter",
                70, 2, true);
        this.energyCost = 2;
        this.socialEffect = 5;
        this.happinessEffect = 2;
        this.knowledgeEffect = 0;
    }

    @Override
    protected void promoteToLevel2(){
        super.promoteToLevel2();
        this.socialEffect += 3;
        this.freeItemPerkUnlocked = true;

        System.out.println("""
            You got promoted to Senior Promoter!

            Your sales confidence is growing.

            Customers trust your recommendations and you close more sales.

            Salary increased by $20, Social gained increased by 3
            """);
    }

    @Override
    protected void promoteToLevel3(){
        super.promoteToLevel3();
        this.happinessEffect += 2;
        this.discountPerkUnlocked = true;
        setDiscountAmount(3);

        System.out.println("""
            You got promoted to Brand Ambassador!

            You represent the brand with confidence and strong product knowledge.

            Your excellent sales performance earns you higher commissions.

            Salary increased by $30, Happiness gained increased by 2
            """);
    }

    @Override
    protected Item getFreeRewardItem(){
        if (!freeItemPerkUnlocked) return null;

        if (Math.random() < 0.5) {
            return ItemBuilder.studyGuide();
        }
        return ItemBuilder.conversationStarterCards();
    }

    @Override
    public boolean canDiscountItem(Item item){
        String name = item.getName();
        return discountPerkUnlocked &&
                (name.equalsIgnoreCase("Conversation Starter Cards") ||
                name.equalsIgnoreCase("Comfort Snack"));
    }

    @Override
    public String getPerkDescription(){
        if (getLevel() == 1){
            return "No perks yet";
        }
        if (getLevel() == 2){
            return "Chance to receive free Study Guides or Conversation Starter Cards.";
        }
        return "Free items and discount on Conversation Cards & Comfort Snacks.";
    }

    @Override
    public void workShift(Character character) {
        super.workShift(character);

        //Commission System (Random Bonus)
        int commission = (int)(Math.random()*16) + 5;
        character.getAccount().deposit(commission);
        System.out.println("You made a successful sale and earned commission! +" + commission);

        //Sales Target Bonus
        int salesToday = (int)(Math.random()*21);
        if (salesToday > 17) {
            character.getAccount().deposit(50);
            System.out.println("You managed to exceed target for today! +50");
        }

        //TODO: Receive item rewards (Physical Product not drinks)
        //Brand Perks: If you promote a produce long enough, you receive free products
        Item reward = getFreeRewardItem();
        if (reward != null && Math.random() < 0.3) {
            character.addItem(reward);
            System.out.println("Job perk! You received a free " + reward.getName() + "!");
        }
    }
}
