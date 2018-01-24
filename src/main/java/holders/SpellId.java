package holders;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import spell.AttackBuffThisMoveImp;
import spell.AttackHPBuffFriendlyCreatureImp;
import spell.BuffEnemyCreatureImp;
import spell.DamageAllEnemysImp;
import spell.HealOneFriendlyCreatureImp;
import spell.Spell;

public class SpellId {

    public Map<Integer, Spell> spellId = addAllSpell();

    private Map<Integer, Spell> addAllSpell() {
        Map<Integer, Spell> m = new ConcurrentHashMap<>();
        m.put(1, new DamageAllEnemysImp());
        m.put(2, new HealOneFriendlyCreatureImp());
        m.put(3, new AttackBuffThisMoveImp());
        m.put(4, new BuffEnemyCreatureImp());
        m.put(5, new AttackHPBuffFriendlyCreatureImp());
        return m;
    }
}
