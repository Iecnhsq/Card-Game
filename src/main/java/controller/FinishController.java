package controller;

import battle.Battle;
import entity.User;
import holders.BattlesHolder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.FinishService;

@Controller
public class FinishController {

    @Autowired
    private BattlesHolder bh;
    @Autowired
    private FinishService fs;

    @RequestMapping("/finish.html")
    public ModelAndView finish(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView();
        Integer i = (Integer) req.getSession().getAttribute("battleId");
        String login = (String) req.getSession().getAttribute("login");
        if (login == null || req.getParameter("refresh") != null || i == null) {
            resp.sendRedirect("/CardGame/main.html");
            return null;
        } else {
            //берем баттл из мапы батлов
            Battle b = bh.get(i);
            User p1 = b.p1;
            User p2 = b.p2;
            int p1Lvl = p1.getLvl();
            int p2Lvl = p2.getLvl();
            Integer oldPointsP1 = p1.getPoints();
            Integer oldPointsP2 = p2.getPoints();
            Integer p1OldLvlPoints = (int) (125 * Math.pow(1.25, p1.getLvl() - 1));
            Integer p2OldLvlPoints = (int) (125 * Math.pow(1.25, p2.getLvl() - 1));
            //делаем просчеты очков и уровня игрока 1
            if (login.equals(b.p1.getLogin()) && !b.p1CheckInFinish) {
                Integer newPointsP1 = null;
                if (b.p1Win && !b.p2Win) {
                    newPointsP1 = fs.updatePoints(p1, b.p1points);
                } else if (!b.p1Win && b.p2Win) {
                    newPointsP1 = fs.updatePoints(p1, b.p1points / 2);
                } else if (b.p1Win && b.p2Win) {
                    newPointsP1 = fs.updatePoints(p1, b.p1points / 2);
                }
                // если достаточно очков повышаем уровень p1
                fs.lvlUp(p1, p1.getPoints());
                Integer p1NextLvlPoints = (int) (125 * Math.pow(1.25, p1.getLvl()));
                b.p1CheckInFinish = true;
                model.addObject("nameP1", p1.getLogin());
                model.addObject("nameP2", p2.getLogin());
                model.addObject("oldPointsP1", oldPointsP1);
                model.addObject("p1OldLvlPoints", p1OldLvlPoints);
                model.addObject("newPointsP1", newPointsP1);
                model.addObject("p1NextLvlPoints", p1NextLvlPoints);
                model.addObject("p1Win", b.p1Win);
                model.addObject("login", login);
                model.addObject("pointNowP1",(oldPointsP1+newPointsP1));
                model.addObject("p1Lvl", p1Lvl);
                // удаляем ид батла из сесии 
                req.getSession().removeAttribute("battleId");
                //делаем просчеты очков и уровня игрока 2
            } else if (login.equals(b.p2.getLogin()) && !b.p2CheckInFinish) {
                Integer newPointsP2 = null;
                if (b.p1Win && !b.p2Win) {
                    newPointsP2 = fs.updatePoints(p2, b.p2points / 2);
                } else if (b.p2Win && !b.p1Win) {
                    newPointsP2 = fs.updatePoints(p2, b.p2points);
                } else if (b.p1Win && b.p2Win) {
                    newPointsP2 = fs.updatePoints(p2, b.p2points / 2);
                }

                // если достаточно очков повышаем уровень p2
                fs.lvlUp(p2, p2.getPoints());
                Integer p2NextLvlPoints = (int) (125 * Math.pow(1.25, p2.getLvl()));
                b.p2CheckInFinish = true;
                model.addObject("nameP1", p1.getLogin());
                model.addObject("nameP2", p2.getLogin());
                model.addObject("oldPointsP1", oldPointsP1);
                model.addObject("oldPointsP2", oldPointsP2);
                model.addObject("p2OldLvlPoints", p2OldLvlPoints);
                model.addObject("newPointsP2", newPointsP2);
                model.addObject("p2NextLvlPoints", p2NextLvlPoints);
                model.addObject("p2Win", b.p2Win);
                model.addObject("login", login);
                model.addObject("pointNowP2",(oldPointsP2+newPointsP2));
                model.addObject("p2Lvl", p2Lvl);
                // удаляем ид батла из сесии 
                req.getSession().removeAttribute("battleId");
            } else {
                resp.sendRedirect("/CardGame/main.html");
                return null;
            }
            // удаляем бой из мабы идущих боев
            if (b.p1CheckInFinish && b.p2CheckInFinish) {
                bh.remove(i);
            }
        }
        return model;
    }
}
