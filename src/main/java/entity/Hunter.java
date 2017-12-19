package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_hunter")
public class Hunter extends Card {
    private String spell;

    public Hunter() {
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

}
