package sims.character;

public abstract class Person {
    private String name;
    private final String gender;

    public Person (String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

}
