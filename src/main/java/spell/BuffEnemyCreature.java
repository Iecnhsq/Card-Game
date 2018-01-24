package spell;

import battle.Battle;

public interface BuffEnemyCreature extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
