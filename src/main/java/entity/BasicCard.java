package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cg_card")
public class BasicCard extends Card {

    public BasicCard() {
    }

}
