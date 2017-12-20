package spell;

import battle.Battle;
import entity.Card;
import ourlists.OnTableList;

public class DamageAllEnemysImp implements DamageAllEnemys {

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {
            batt.p2OnTable = takeAwayOneLifeOnTableEnemyCards(p2OnTable);
            batt.p2points = batt.p2points-1;

        } else if(batt.p1MadeTurn && !batt.p2MadeTurn) {
            batt.p1OnTable = takeAwayOneLifeOnTableEnemyCards(p1OnTable);
            batt.p1points = batt.p1points-1;
        }
    }

    private OnTableList takeAwayOneLifeOnTableEnemyCards(OnTableList onTable) {
        for (Card c : onTable) {
            c.setHealth(c.getHealth() - 1);
        }

        return onTable;
    }

}
