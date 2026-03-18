package sims.item;

import sims.character.Character;
import sims.event.TurnState;

public class Item implements Usable {
    private String name;
    protected String desc;
    protected int price;
    private final ItemType itemType;

    protected int knowledgeEffect;
    protected int happinessEffect;
    protected int socialEffect;
    protected int energyEffect;

    public Item(String name, String desc, int price, ItemType type, int knowledgeEffect, int happinessEffect, int socialEffect, int energyEffect) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.itemType = type;
        this.knowledgeEffect = knowledgeEffect;
        this.happinessEffect = happinessEffect;
        this.socialEffect = socialEffect;
        this.energyEffect = energyEffect;
    }

    public String getName(){
        return name;
    }
    public void setName( String name){
        this.name = name;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice(){
        return price;
    }

    public int getKnowledgeEffect(){
        return knowledgeEffect;
    }

    public int getHappinessEffect(){
        return happinessEffect;
    }

    public int getSocialEffect(){
        return socialEffect;
    }

    public int getEnergyEffect(){
        return energyEffect;
    }

    public ItemType getItemType(){
        return itemType;
    }

    @Override
    public void use(Character character, TurnState turnState) {
        character.changeKnowledge(knowledgeEffect);
        character.changeHappiness(happinessEffect);
        character.changeSocial(socialEffect);
        turnState.gainEnergy(energyEffect);
    }

    @Override
    public String toString() {
        return name + " ($" + price + ") - " + desc;
    }

}
