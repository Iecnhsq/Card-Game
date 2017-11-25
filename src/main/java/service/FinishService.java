package service;

import dao.UserDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class FinishService {

    @Autowired
    private UserDAO udao;

    public void updatePoints(User player, int pointsGained) {
        player.setPoints(player.getPoints() + pointsGained);
        udao.updateUser(player);
    }

    public void lvlUp(User u, int point) {
        int i = (int) (125 * Math.pow(1.25, u.getLvl()));
        if (i <= u.getPoints()) {
            int s = u.getLvl();
            s++;
            u.setLvl(s);
            udao.updateUser(u);
        }
    }
}
