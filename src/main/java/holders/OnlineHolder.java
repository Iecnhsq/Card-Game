package holders;

import entity.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineHolder {

    private final Map<String, User> online = new ConcurrentHashMap<>();

    public User get(String key) {
        return online.get(key);
    }

    public User put(String key, User value) {
        return online.put(key, value);
    }

    public int size() {
        return online.size();

    }

    public User remove(String key) {
        return online.remove(key);
    }
    
}
