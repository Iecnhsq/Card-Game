package holders;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import spell.DamageAllEnemysImp;
import spell.Spell;

public class SpellId {

    public Map<Integer, Spell> spellId=addAllSpell();
    

    
    

private Map<Integer,Spell> addAllSpell(){
    Map<Integer, Spell> m = new ConcurrentHashMap<>();
    m.put(1,new DamageAllEnemysImp());
    // next more magic
    //to do
    return  m;
}
}
