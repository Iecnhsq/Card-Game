package spell;

import battle.Battle;

public interface AttackBuffThisMove extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
