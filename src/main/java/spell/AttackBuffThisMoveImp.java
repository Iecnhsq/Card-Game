package spell;

import battle.Battle;
import entity.Card;
import java.util.Map;
import ourlists.OnTableList;

public class AttackBuffThisMoveImp implements AttackBuffThisMove {

    private static Integer id = null;

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        Map<Integer, Card> p1ActiveCards = batt.p1ActiveCards;
        Map<Integer, Card> p2ActiveCards = batt.p2ActiveCards;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {
            addMove(p1OnTable, p1ActiveCards);
        } else if (batt.p1MadeTurn && !batt.p2MadeTurn) {
            addMove(p2OnTable, p2ActiveCards);
        }
    }

    public static void setId(int idCard) {
        id = idCard;
    }

    private void addMove(OnTableList OnTable, Map<Integer, Card> ActiveCards) {
        for (Card c : OnTable) {
            int thisId = c.getId();
            if (thisId == id) {
                ActiveCards.put(thisId, OnTable.remove(thisId));
            }
        }
    }

}
