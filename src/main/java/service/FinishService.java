package service;

import dao.UserDAO;
import entity.User;
import holders.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FinishService {

    @Autowired
    private UserDAO udao;
    @Autowired
    private UserHolder uh;

    public int updatePoints(User player, int pointsGained) {
        player.setPoints(player.getPoints() + pointsGained);
        uh.set(player);
        udao.updateUser(player);
        return pointsGained;
    }

    public void lvlUp(User u, int point) {
        int i = (int) (125 * Math.pow(1.25, u.getLvl()));
        if (i <= u.getPoints()) {
            int s = u.getLvl();
            s++;
            u.setLvl(s);
            uh.set(u);
            udao.updateUser(u);
        }
    }
}
