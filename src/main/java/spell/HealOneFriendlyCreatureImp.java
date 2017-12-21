package spell;

import battle.Battle;
import entity.Card;
import ourlists.OnTableList;

public class HealOneFriendlyCreatureImp implements HealOneFriendlyCreature {

    private static Integer id = null;

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {

            for (Card c : p1OnTable) {
                if (c.getId() == id) {
                    c.setHealth(c.getHealth() + amount);
                }
            }
        } else if (batt.p1MadeTurn && !batt.p2MadeTurn) {
            for (Card c : p2OnTable) {
                if (c.getId() == id) {
                    c.setHealth(c.getHealth() + amount);
                }
            }
        }
    }

    public static void setId(int idFriendlyCard) {
        id = idFriendlyCard;
    }

}
