package spell;

import battle.Battle;

public interface DamageAllEnemys extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
