package sims.job;

import sims.character.Character;
import sims.item.Item;

public class TutorJob extends Job {
    private int tutoringSessions = 0;

    public TutorJob() {
        super("Tuition teacher", "You work as a tutor at SIT Tuition Center",
                100,  2, false);
        this.energyCost = 2;
        this.socialEffect = 2;
        this.happinessEffect = 0;
        this.knowledgeEffect = 5;
    }

    public int getTutoringSessions() {
        return tutoringSessions;
    }

    public void resetTutoringSessions() {
        tutoringSessions = 0;
    }

    @Override
    protected void promoteToLevel2(){
        super.promoteToLevel2();
        this.knowledgeEffect += 3;
        this.knowledgeReinforcementUnlocked = true;

        System.out.println("""
            You got promoted to Senior Tutor!

            Your teaching experience is paying off.

            Students understand your explanations better and your reputation grows.

            Salary increased by $20, Knowledge gained increased by 3
            """);
    }

    @Override
    protected void promoteToLevel3() {
        super.promoteToLevel3();
        this.socialEffect += 2;
        this.discountPerkUnlocked = true;
        setDiscountAmount(4);

        System.out.println("""
                You got promoted to Lead Tutor!
                
                Students actively seek you out before exams.
                
                Your expertise and reputation allow you to command higher tutoring rates.
                
                Salary increased by $30, Social gained increased by 2
                """);
    }

    @Override
    public boolean canDiscountItem(Item item){
        String name = item.getName();
        return discountPerkUnlocked &&
                (name.equalsIgnoreCase("Study Guide") ||
                name.equalsIgnoreCase("Exam Cramp Notes"));
    }

    @Override
    public String getPerkDescription(){
        if (getLevel() == 1){
            return "No perks yet";
        }
        if (getLevel() == 2){
            return "Knowledge reinforcement when tutoring.";
        }
        return "Knowledge reinforcement and cheaper Study Guides & Exam Cramp Notes.";
    }

    @Override
    public void resetAllProgress(){
        super.resetAllProgress();
        resetTutoringSessions();
    }

    @Override
    public void workShift(Character character) {
        super.workShift(character);

        tutoringSessions++;

        //Student Performance Bonus (Delayed reward)
        if (tutoringSessions == 3) {
            character.getAccount().deposit(50);
            System.out.println("Your student improved and their parents gave you a bonus! +$50");
            tutoringSessions = 0;
        }

        //Knowledge Reinforcement
        if (knowledgeReinforcementUnlocked && Math.random() < 0.5) {
            character.changeKnowledge(2);
            System.out.println("You understood the subject better by teaching it! +2 Knowledge");
            }

        //Teaching Stress Risk
        if (Math.random() < 0.2) {
            character.changeHappiness(-3);
            System.out.println("Your student was difficult today. -3 Happiness");
        }
    }
}
