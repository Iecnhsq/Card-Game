package controller;

import battle.Battle;
import entity.Card;
import entity.User;
import holders.BattlesHolder;
import holders.OnlineHolder;
import holders.UserHolder;
import holders.WaitHolder;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.MainService;
import service.WaitService;

@Controller
public class WaitController {

    @Autowired
    private MainService mServ;
    @Autowired
    private BattlesHolder bh;
    @Autowired
    private WaitHolder wh;
    @Autowired
    private OnlineHolder oh;
    @Autowired
    private UserHolder uh;
    @Autowired
    private WaitService wService;

    @RequestMapping("wait.html")
    public ModelAndView wait(HttpServletRequest req, HttpServletResponse res) {
        ModelAndView model = new ModelAndView("wait");
        String login = ((String) req.getSession().getAttribute("login"));
        User u = uh.getUser();
        boolean inBattle = false;
        if (login == null) {
            try {
                res.sendRedirect("main.html");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
            return null;
        } else {
            Set<Card> cards = mServ.getUserCards(model, u.getClasss());
            if (cards.size() < 10) {
                try {
                    res.sendRedirect("main.html");
                } catch (IOException ex) {
                    System.out.println("Error: " + ex);
                }
                return null;
            } else {
                for (int i : bh.keySet()) {
                    Battle b = bh.get(i);
                    if (b.p2.getLogin().equals(login)) {
                        req.getSession().setAttribute("battleId", i);
                        break;
                    }
                }
                try {
                    Battle inB = bh.get((Integer) req.getSession().getAttribute("battleId"));
                    if (inB != null) {
                        inBattle = wService.inBattle(login, inB);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
                if (!inBattle) {
                    Set<String> keylogin = wh.keySet();
                    int Online = oh.size();
                    String pOnline = null;
                    model.addObject("classs", u.getClasss());
                    if (Online > 0) {
                        pOnline = "Players online: " + Online;
                    }
                    if (!keylogin.contains(login)) {
                        wh.put(login, uh.getUser());
                        int waitSize = wh.size();
                        if (waitSize < 2) {
                            model.addObject("pOnline", pOnline);
                            model.addObject("login", login);
                            return model;
                        } else {
                            Battle b = new Battle();
                            b.p1 = uh.getUser();
                            wh.remove(b.p1.getLogin());
                            Set<String> waitKeys = wh.keySet();
                            if (b.p2.getLogin() != login) {
                                for (String key : waitKeys) {
                                    if (!key.equals(login)) {
                                        b.p2 = wh.remove(key);
                                        break;
                                    }
                                }
                                Integer i = new Random().nextInt();
                                bh.put(i, b);
                                req.getSession().setAttribute("battleId", i);
                                try {
                                    res.sendRedirect("battle.html");
                                } catch (IOException ex) {
                                    System.out.println("Error: " + ex);
                                }
                            }
                        }
                    } else {
                        model.addObject("level", u.getLvl());
                        model.addObject("pOnline", pOnline);
                        model.addObject("login", login);
                        return model;
                    }
                } else {
                    try {
                        res.sendRedirect("battle.html");
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex);
                    }
                }
            }
        }
        return null;
    }
}
