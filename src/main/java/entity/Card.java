package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Card implements Serializable, Cloneable {

    @Id
    private int id;
    private int level;
    private String type;
    private int damage;
    private int health;
    private Boolean taunt;
    private Boolean imun;
    private Boolean shield;
    private Boolean charge;
    private Boolean poison;
    private String name;
    private String spell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Boolean getTaunt() {
        return taunt;
    }

    public void setTaunt(Boolean taunt) {
        this.taunt = taunt;
    }

    public Boolean getImun() {
        return imun;
    }

    public void setImun(Boolean imun) {
        this.imun = imun;
    }

    public Boolean getShield() {
        return shield;
    }

    public void setShield(Boolean shield) {
        this.shield = shield;
    }

    public Boolean getCharge() {
        return charge;
    }

    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

    public Boolean getPoison() {
        return poison;
    }

    public void setPoison(Boolean poison) {
        this.poison = poison;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.level;
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + this.damage;
        hash = 17 * hash + this.health;
        hash = 17 * hash + Objects.hashCode(this.taunt);
        hash = 17 * hash + Objects.hashCode(this.imun);
        hash = 17 * hash + Objects.hashCode(this.shield);
        hash = 17 * hash + Objects.hashCode(this.charge);
        hash = 17 * hash + Objects.hashCode(this.poison);
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.level != other.level) {
            return false;
        }
        if (this.damage != other.damage) {
            return false;
        }
        if (this.health != other.health) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.taunt, other.taunt)) {
            return false;
        }
        if (!Objects.equals(this.imun, other.imun)) {
            return false;
        }
        if (!Objects.equals(this.shield, other.shield)) {
            return false;
        }
        if (!Objects.equals(this.charge, other.charge)) {
            return false;
        }
        return Objects.equals(this.poison, other.poison);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
    
}
