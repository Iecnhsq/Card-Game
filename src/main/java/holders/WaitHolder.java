package holders;

import entity.User;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WaitHolder {

    private final Map<String, User> wait = new ConcurrentHashMap<>();

    public User get(String key) {
        return wait.get(key);
    }

    public User put(String key, User value) {
        return wait.put(key, value);
    }

    public int size() {
        return wait.size();
    }

    public Set<String> keySet() {
        return wait.keySet();
    }

    public User remove(String login) {
        return wait.remove(login);
    }

}
