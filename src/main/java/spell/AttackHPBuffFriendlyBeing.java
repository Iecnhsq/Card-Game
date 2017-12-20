package spell;

import battle.Battle;

public interface AttackHPBuffFriendlyBeing extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
