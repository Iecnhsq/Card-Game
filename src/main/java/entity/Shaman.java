package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_shaman")
public class Shaman extends Card {

    private String spell;

    public Shaman() {
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
