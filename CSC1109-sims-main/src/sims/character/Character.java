package sims.character;

import sims.event.TurnState;
import sims.item.Item;
import sims.item.ItemType;
import sims.job.Job;

import java.util.ArrayList;

public class Character extends Person {
    private final Account account;
    private int social;
    private int knowledge;
    private int happiness;
    private Job currentJob;
    private ArrayList<Item> inventory;
    private boolean energyItemUsedThisTurn;

    public Character(String name, String gender, int happiness, int social, int knowledge) {
        super(name, gender);
        this.account = new Account(100);
        this.social = social;
        this.knowledge = knowledge;
        this.happiness = happiness;
        this.inventory = new ArrayList<>();
        this.energyItemUsedThisTurn = false;
    }

    public void statCap(){
        if (this.happiness > 100) this.happiness = 100;
        if (this.happiness < 0) this.happiness = 0;

        if (this.social > 100) this.social = 100;
        if (this.social < 0) this.social = 0;

        if (this.knowledge > 100) this.knowledge = 100;
        if (this.knowledge < 0) this.knowledge = 0;
    }

    public void socialise() {
        this.happiness += 20;
        statCap();
    }

    public int getSocial(){
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
        statCap();
    }

    public void changeSocial(int amt){
        this.social += amt;
        statCap();
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
        statCap();
    }

    public void changeKnowledge(int amt){
        this.knowledge += amt;
        statCap();
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
        statCap();
    }

    public void changeHappiness(int amt){
        this.happiness += amt;
        statCap();
    }

    public double getBalance() {
        return account.getBalance();
    }

    public Account getAccount() {
        return account;
    }


    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public boolean hasJob(){
        return currentJob != null;
    }

    public boolean quitJob(){
        if (currentJob == null){
            System.out.println("There is no job to quit.");
            return false;
        }

        if (currentJob.getTotalShiftsWorked() < currentJob.getMinTurnsRequired()){
            System.out.println("You cannot quit yet. You must work at least " + currentJob.getMinTurnsRequired() + " turns.");
            return false;
        }

        currentJob.resetAllProgress();
        currentJob = null;

        System.out.println("You have quit your job");
        return true;
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public void displayInventory(){
        if (inventory.isEmpty()){
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++){
            Item item = inventory.get(i);
            System.out.println((i+1) + ". " + item.getName());
            System.out.println("   Description: " + item.getDesc());
            // Only show non-zero stat effects for clarity.
            String effectsLine = buildEffectsLine(item);
            if (!effectsLine.isEmpty()) {
                System.out.println("   Effects: " + effectsLine);
            }
        }
    }

    public boolean hasUsedEnergyItemThisTurn(){
        return energyItemUsedThisTurn;
    }

    public void energyItemUsedThisTurn(){
        this.energyItemUsedThisTurn = true;
    }

    public void resetEnergyItemUsage(){
        this.energyItemUsedThisTurn = false;
    }

    // comma-separated list of only the stats this item will change.
    private String buildEffectsLine(Item item) {
        StringBuilder sb = new StringBuilder();
        appendEffect(sb, "Knowledge", item.getKnowledgeEffect());
        appendEffect(sb, "Happiness", item.getHappinessEffect());
        appendEffect(sb, "Social", item.getSocialEffect());
        appendEffect(sb, "Energy", item.getEnergyEffect());
        return sb.toString();
    }

    // Append a labelled effect only when the value is non-zero.
    private void appendEffect(StringBuilder sb, String label, int value) {
        if (value == 0) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append(label).append(" ").append(formatEffect(value));
    }

    private String formatEffect(int value) {
        if (value > 0) {
            return "+" + value;
        }
        return String.valueOf(value);
    }

    public void useItem(int index, TurnState turnState){
        if (index < 0 || index >= inventory.size()){
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.get(index);

        //check if an energy item has been used this turn
        if (item.getItemType() == ItemType.ENERGY && hasUsedEnergyItemThisTurn()){
            System.out.println("You can only use one energy item per turn");
            return;
        }

        item.use(this, turnState);

        if (item.getItemType() == ItemType.ENERGY){
            energyItemUsedThisTurn();
        }

        inventory.remove(index);

        System.out.println(item.getName() + " was used.");
    }


    @Override
    public String toString() {
        return "sims.character.Character{" +
                "name='" + getName() + '\'' +
                ", happiness=" + getHappiness() +
                '}';
    }

//    public void printStats() {
//
//        System.out.println("Name: " + this.getName().substring(0,1).toUpperCase() + this.getName().substring(1));
//        System.out.println("Happiness: " + this.happiness);
//    }

}
