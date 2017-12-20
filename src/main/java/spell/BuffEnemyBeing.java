package spell;

import battle.Battle;

public interface BuffEnemyBeing extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
