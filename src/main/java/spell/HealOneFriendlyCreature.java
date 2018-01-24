package spell;

import battle.Battle;

public interface HealOneFriendlyCreature extends Spell {

    @Override
    public void doSpell(int amount, Battle batt);

}
