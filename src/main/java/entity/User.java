package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "cg_user")
public class User implements Serializable {

    @Id
    private int id;
    private String login;
    private String pass;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private int lvl;
    private int points;
    private String cards;
    private String classs;
    private int money;
    private String city;
    private String phone;
    private String email;

    public User() {
    }

    public User(int id, String login, String pass, Date date, int lvl, int points, String cards, String classs, int money, String city, String phone, String email) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.date = date;
        this.lvl = lvl;
        this.points = points;
        this.cards = cards;
        this.classs = classs;
        this.money = money;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public Date getDate() {
        return date;
    }

    public int getLvl() {
        return lvl;
    }

    public int getPoints() {
        return points;
    }

    public String getCards() {
        return cards;
    }

    public String getClasss() {
        return classs;
    }

    public int getMoney() {
        return money;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.pass);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + this.lvl;
        hash = 97 * hash + this.points;
        hash = 97 * hash + Objects.hashCode(this.cards);
        hash = 97 * hash + Objects.hashCode(this.classs);
        hash = 97 * hash + this.money;
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + Objects.hashCode(this.email);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.lvl != other.lvl) {
            return false;
        }
        if (this.points != other.points) {
            return false;
        }
        if (this.money != other.money) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.cards, other.cards)) {
            return false;
        }
        if (!Objects.equals(this.classs, other.classs)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
