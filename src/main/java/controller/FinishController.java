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
        
        if (login == null || req.getParameter("refresh") != null ||i==null) {
            resp.sendRedirect("/CardGame/main.html");
            return null;
        } else {
            //берем баттл из мапы батлов
            Battle b = bh.get(i);
            User p1 = b.p1; User p2 = b.p2;
            System.out.println(login);
            //делаем просчеты очков и уровня игрока 1
            if (login.equals(b.p1.getLogin())&&!b.p1CheckInFinish) {
                if (b.p1Win && !b.p2Win) {
                    fs.updatePoints(p1, b.p1points);
                } else if (!b.p1Win && b.p2Win) {
                    fs.updatePoints(p1, b.p1points / 2);
                } else if (b.p1Win && b.p2Win) {
                    fs.updatePoints(p1, b.p1points / 2);
                }
                // если достаточно очков повышаем уровень p1
                fs.lvlUp(p1, p1.getPoints());
                b.p1CheckInFinish = true;
                // удаляем ид батла из сесии 
                req.getSession().removeAttribute("battleId");
                //делаем просчеты очков и уровня игрока 2
            } else if (login.equals(b.p2.getLogin())&&!b.p2CheckInFinish) {
                System.out.println("you p2");
                if (b.p1Win && !b.p2Win) {
                    fs.updatePoints(p2, b.p2points / 2);
                } else if (b.p2Win && !b.p1Win) {
                    fs.updatePoints(p2, b.p2points);
                } else if (b.p1Win && b.p2Win) {
                    fs.updatePoints(p2, b.p2points / 2);
                }
                // если достаточно очков повышаем уровень p2
                fs.lvlUp(p2, p2.getPoints());
                b.p2CheckInFinish = true;
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
