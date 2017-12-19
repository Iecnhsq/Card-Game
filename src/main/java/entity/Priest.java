package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_priest")
public class Priest extends Card {

    private String spell;

    public Priest() {
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
