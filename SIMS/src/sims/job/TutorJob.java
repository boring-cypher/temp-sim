package sims.job;

import sims.character.Character;

public class TutorJob extends Job {
    private int tutoringSessions = 0;

    public TutorJob() {
        super("Tuition teacher", "You work as a relief teacher at ABC Tuition Center",
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
        if (character.getKnowledge() >= 60 && Math.random() < 0.5) {
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
