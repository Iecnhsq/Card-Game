package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_warrior")
public class Warrior extends Card {

    private String spell;

    public Warrior() {
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
