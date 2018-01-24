package spell;

import battle.Battle;
import ourlists.OnTableList;

public class BuffEnemyCreatureImp implements BuffEnemyCreature {

    @Override
    public void doSpell(int amount, Battle batt) {
        OnTableList p2OnTable = batt.p2OnTable;
        OnTableList p1OnTable = batt.p1OnTable;
        if (!batt.p1MadeTurn && !batt.p2MadeTurn) {
            batt.p2OnTable = takeAwayOneDamageOnTableEnemyCards(p2OnTable, amount);
        } else if (batt.p1MadeTurn && !batt.p2MadeTurn) {
            batt.p1OnTable = takeAwayOneDamageOnTableEnemyCards(p1OnTable, amount);
        }
    }

    private OnTableList takeAwayOneDamageOnTableEnemyCards(OnTableList onTable, int amount) {
        onTable.forEach((c) -> {
            c.setDamage(c.getDamage() - amount);
        });
        return onTable;
    }
}
