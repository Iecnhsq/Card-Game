package spell;

import battle.Battle;
import ourlists.OnTableList;

public class DamageAllEnemysImp implements DamageAllEnemys {

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (batt.p1MadeTurn) {
            batt.p2OnTable = takeAwayOneLifeOnTableEnemyCards(p2OnTable);
        } else {
            batt.p1OnTable = takeAwayOneLifeOnTableEnemyCards(p1OnTable);
        }
    }

    private OnTableList takeAwayOneLifeOnTableEnemyCards(OnTableList onTable) {
        onTable.forEach((c) -> {
            c.setHealth(c.getHealth() - 1);
        });
        return onTable;
    }

}
