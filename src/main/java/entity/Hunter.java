package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_hunter")
public class Hunter extends Card{

    public Hunter() {
    }

}
