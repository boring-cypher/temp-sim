package sims.character;

import sims.event.TurnState;
import sims.item.Item;
import sims.job.Job;

import java.util.ArrayList;

public class Character extends Person {
    private final Account account;
    private int social;
    private int knowledge;
    private int happiness;
    private Job currentJob;
    private ArrayList<Item> inventory;

    public Character(String name, String gender, int happiness, int social, int knowledge) {
        super(name, gender);
        this.account = new Account(100);
        this.social = social;
        this.knowledge = knowledge;
        this.happiness = happiness;
        this.inventory = new ArrayList<>();
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
        if (social < 0 || social > 100) {
            throw new IllegalArgumentException("Social must be between 0 and 100.");
        }
    }

    public void changeSocial(int amt){
        this.social += amt;
        statCap();
        if (social < 0 || social > 100) {
            throw new IllegalArgumentException("Social must be between 0 and 100.");
        }
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

        currentJob.resetJobProgress();
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
            System.out.println((i+1) + ". " + item);
        }
    }

    public void useItem(int index, TurnState turnState){
        if (index < 0 || index >= inventory.size()){
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.get(index);
        item.use(this, turnState);
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

    public void printStats() {

        System.out.println("Name: " + this.getName().substring(0,1).toUpperCase() + this.getName().substring(1));
        System.out.println("Happiness: " + this.happiness);
    }

}
