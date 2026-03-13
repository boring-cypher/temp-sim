package sims.character;

import java.util.Map;

public class CCA {
    private String name;
    private String desc;

    public CCA(String name, String desc, Map<String, Integer> effects) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
