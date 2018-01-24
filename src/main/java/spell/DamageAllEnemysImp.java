package spell;

import battle.Battle;
import ourlists.OnTableList;

public class DamageAllEnemysImp implements DamageAllEnemys {

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {
            batt.p2OnTable = takeAwayOneLifeOnTableEnemyCards(p2OnTable, amount);
            batt.p2Health = batt.p2Health - amount;
        } else if (batt.p1MadeTurn && !batt.p2MadeTurn) {
            batt.p1OnTable = takeAwayOneLifeOnTableEnemyCards(p1OnTable, amount);
            batt.p1Health = batt.p1Health - amount;
        }
    }

    private OnTableList takeAwayOneLifeOnTableEnemyCards(OnTableList onTable, int amount) {
        onTable.forEach((c) -> {
            c.setHealth(c.getHealth() - amount);
        });
        return onTable;
    }

}
