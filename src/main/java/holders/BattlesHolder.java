package holders;

import battle.Battle;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BattlesHolder {

    private final Map<Integer, Battle> battle = new ConcurrentHashMap<>();

    public Set<Integer> keySet() {
        return battle.keySet();
    }

    public Battle get(Integer key) {
        return battle.get(key);
    }

    public Battle put(Integer key, Battle value) {
        return battle.put(key, value);
    }

    public int size() {
        return battle.size();
    }

    public void remove(int id) {
        battle.remove(id);
    }
    
}
