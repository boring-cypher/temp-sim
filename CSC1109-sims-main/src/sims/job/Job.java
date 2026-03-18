package sims.job;

import sims.character.Character;
import sims.item.Item;

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

    private int level;
    private int maxLevel;

    protected boolean freeItemPerkUnlocked = false;
    protected boolean discountPerkUnlocked = false;
    protected boolean knowledgeReinforcementUnlocked = false;
    protected int discountAmount = 0;

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

        this.level = 1;
        this.maxLevel = 3;
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

        level = 1;
        freeItemPerkUnlocked = false;
        discountPerkUnlocked = false;
        knowledgeReinforcementUnlocked = false;
    }

    public void resetAllProgress(){
        resetJobProgress();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    protected void promoteToLevel2() {
        level = 2;
        salary += 20;
        System.out.println("Promotion! Your salary increased to $" + salary + ".");
    }

    protected void promoteToLevel3() {
        level = 3;
        salary += 30;
        System.out.println("Promotion! Your salary increased to $" + salary + ".");
    }

    protected void checkProgression(){
        if (level == 1 && totalShiftsWorked >= 3){
            promoteToLevel2();
        } else if (level == 2 && totalShiftsWorked >= 6){
            promoteToLevel3();
        }
    }

    public boolean hasFreeItemPerk(){
        return freeItemPerkUnlocked;
    }

    public boolean hasDiscountPerk(){
        return discountPerkUnlocked;
    }

    public boolean hasKnowledgeReinforcementPerk(){
        return knowledgeReinforcementUnlocked;
    }

    public boolean canDiscountItem(Item item){
        return false;
    }

    public int getDiscountedPrice(Item item){
        if (canDiscountItem(item)){
            return item.getPrice() - discountAmount;
        }
        return item.getPrice();
    }

    protected Item getFreeRewardItem(){
        return null;
    }

    protected void setDiscountAmount(int amount){
        this.discountAmount = amount;
    }

    public String getPerkDescription(){
        return "No perks unlocked yet";
    }

    public void workShift(Character character){
        incrementWorkStreak();
        incrementTotalShiftsWorked();

        character.getAccount().deposit(this.salary);
        character.changeHappiness(happinessEffect);
        character.changeSocial(socialEffect);
        character.changeKnowledge(knowledgeEffect);

        checkProgression();
    }

    public void printJobStatus() {
        System.out.println("Job: " + name);
        System.out.println("Level: " + level + "/" + maxLevel);
        System.out.println("Salary per shift: $" + salary);
        System.out.println("Shifts worked: " + totalShiftsWorked);

        System.out.println("Perks: " + getPerkDescription());

        if (level == 1) {
            System.out.println("Next promotion in: " + (3 - totalShiftsWorked) + " shifts");
        }
        else if (level == 2) {
            System.out.println("Next promotion in: " + (6 - totalShiftsWorked) + " shifts");
        }
        else {
            System.out.println("Max level reached");
        }
    }
}