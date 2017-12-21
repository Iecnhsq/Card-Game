package spell;

import battle.Battle;
import entity.Card;
import ourlists.OnTableList;

public class AttackHPBuffFriendlyCreatureImp implements AttackHPBuffFriendlyCreature {

    private static Integer id = null;

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {
            addHpDmg(p1OnTable, amount);        
        } else if (batt.p1MadeTurn && !batt.p2MadeTurn) {
            addHpDmg(p2OnTable, amount);
        }
    }

    public static void setId(int idFriendlyCard) {
        id = idFriendlyCard;
    }

    private void addHpDmg(OnTableList OnTable, int amount) {
        for (Card c : OnTable) {
            if (c.getId() == id) {
                c.setHealth(c.getHealth() + amount);
                c.setDamage(c.getDamage() + amount);
            }
        }
    }
}
