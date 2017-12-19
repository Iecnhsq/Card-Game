package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_mage")
public class Mage extends Card {

    private String spell;

    public Mage() {
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
