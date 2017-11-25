package service;

import battle.Battle;
import entity.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WaitService {

    public static void pairUsers(Map<String, User> inSearch) {
        if (!inSearch.isEmpty()) {

            List<String> paired = new LinkedList<>();
            while (inSearch.size() > 1) {
                inSearch.entrySet().stream().map((entry) -> {
                    paired.add(entry.getKey());
                    return entry;
                }).filter((_item) -> (paired.size() == 2)).map((_item) -> {
                    inSearch.remove(paired.get(0));
                    return _item;
                }).forEachOrdered((_item) -> {
                    inSearch.remove(paired.get(1));
                    // Method which starts a battle, na vhod kotoromy
                    //inSearch.get(paired.get(0))
                    //inSearch.get(paired.get(1))
                });
            }
        }
    }

    public static boolean inBattle(String login, Battle b) {
        return b.p1.getLogin().equals(login) || b.p2.getLogin().equals(login);

    }

}
