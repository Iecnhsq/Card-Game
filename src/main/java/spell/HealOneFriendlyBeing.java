
package spell;

import battle.Battle;


public interface HealOneFriendlyBeing extends Spell{

    @Override
    public void doSpell(int amount, Battle batt);

}
