package spell;

import battle.Battle;

public interface AttackHPBuffFriendlyCreature extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
