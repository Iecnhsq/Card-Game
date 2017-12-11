package entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Card implements Serializable{
    
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
}
