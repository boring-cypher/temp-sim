package sims.job;

import sims.character.Character;

public abstract class Job {
    protected String name;
    protected String desc;
    protected int salary;

    protected int energyCost;
    protected int happinessEffect;
    protected int socialEffect;
    protected int knowledgeEffect;
    private int minTurnsRequired;
    private boolean holidayOnly;
    private int workStreak = 0;
    private int totalShiftsWorked = 0;

    public Job(String name, String desc, int salary, int minTurnsRequired, boolean holidayOnly) {
        this.name = name;
        this.desc = desc;
        this.salary = salary;

        this.energyCost = 0;
        this.happinessEffect = 0;
        this.socialEffect = 0;
        this.knowledgeEffect = 0;

        this.minTurnsRequired = minTurnsRequired;
        this.holidayOnly = holidayOnly;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getSalary() {
        return salary;
    }

    public void increaseSalary(int amount) {
        this.salary += amount;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getShiftEnergyCost(){
        return energyCost + workStreak;
    }

    public int getHappinessEffect() {
        return happinessEffect;
    }

    public int getSocialEffect() {
        return socialEffect;
    }

    public int getKnowledgeEffect() {
        return knowledgeEffect;
    }

    public int getMinTurnsRequired() {
        return minTurnsRequired;
    }

    public boolean isHolidayOnly() {
        return holidayOnly;
    }

    public int getTotalShiftsWorked() {
        return totalShiftsWorked;
    }

    public void incrementTotalShiftsWorked() {
        totalShiftsWorked ++;
    }

    public void resetJobProgress(){
        workStreak = 0;
        totalShiftsWorked = 0;
    }

    public int getWorkStreak() {
        return workStreak;
    }

    public void incrementWorkStreak() {
        workStreak ++;
    }

    public void resetWorkStreak() {
        workStreak = 0;
    }

    public void workShift(Character character){
        incrementWorkStreak();
        incrementTotalShiftsWorked();

        character.getAccount().deposit(this.salary);
        character.changeHappiness(happinessEffect);
        character.changeSocial(socialEffect);
        character.changeKnowledge(knowledgeEffect);
    };
}