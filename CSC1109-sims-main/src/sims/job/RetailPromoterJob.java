package sims.job;

import sims.character.Character;

public class RetailPromoterJob extends Job {
    public RetailPromoterJob(){
        super("Retail Promoter", "You work at MUJI as a retail promoter",
                70, 2, true);
        this.energyCost = 2;
        this.socialEffect = 5;
        this.happinessEffect = 2;
        this.knowledgeEffect = 0;
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
    }
}
