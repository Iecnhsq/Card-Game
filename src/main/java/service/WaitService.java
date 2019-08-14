package service;

import battle.Battle;
import entity.User;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WaitService {

    public void pairUsers(@NotNull Map<String, User> inSearch) {
        if (!inSearch.isEmpty()) {
            List<String> paired = new LinkedList<>();
            while (inSearch.size() > 1) {
                inSearch.entrySet().stream().peek((entry) -> paired.add(entry.getKey())).filter((_item) -> (paired.size() == 2)).peek((_item) -> inSearch.remove(paired.get(0))).forEachOrdered((_item) -> {
                    inSearch.remove(paired.get(1));
                });
            }
        }
    }

    public boolean inBattle(String login, @NotNull Battle b) {
        return b.p1.getLogin().equals(login) || b.p2.getLogin().equals(login);
    }

}
