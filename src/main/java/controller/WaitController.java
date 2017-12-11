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

    @RequestMapping("wait.html")
    public ModelAndView wait(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ModelAndView model = new ModelAndView();
        String login = ((String) req.getSession().getAttribute("login"));
        User u = uh.getUser();
        boolean inBattle = false;
        // в случае если на нашу страницу перешел не зарегестрированый пользиватель его оправляем на мейн страницу.
        if (login == null) {
            res.sendRedirect("/CardGame/main.html");
            return null;
        } else {
            // получаем карты нашего игрока которые он выбрал для боя, что бы не пускать игроков с не полной колодой.
            // код можно увидить нажав CTRL+getUserCards.
            Set<Card> cards = mServ.getUserCards(model,u.getClasss());
            if (cards.size() < 10) {
                res.sendRedirect("/CardGame/main.html");
                return null;
            } else {
                // так как при первом запуске баттл ID еще не записан в сесии будет выкидывать NullPointerExeption,
                // но в дальнейшем мы всеравно пытаемся взять этот артибус сесии что бы получить ключ боя в котором находиться наш игрок.
                for (int i : bh.keySet()) {
                    Battle b = bh.get(i);
                    if (b.p2.getLogin().equals(login)) {
                        req.getSession().setAttribute("battleId", i);
                        break;
                    }
                }
                try {
                    Battle inB = bh
                            .get((Integer) req.getSession().getAttribute("battleId"));
                    if (inB != null) {
                        inBattle = WaitService.inBattle(login, inB);
                    }
                } catch (Exception e) {
                    System.out.println("You have exeption on get battle id");
                }
                //если мы не в бою
                if (!inBattle) {
                    // получаем количество игроков онлайн.
                    Set<String> keylogin = wh.keySet();
                    int Online = oh.size();
                    String pOnline = null;
                    model.addObject("classs", u.getClasss());
                    if (Online > 0) {
                        pOnline = "Players online: " + Online;
                    }
                    //если в нашем списке ожидающих нету такого игрока добавляем.
                    if (!keylogin.contains(login)) {
                        wh.put(login, uh.getUser());
                        int waitSize = wh.size();
                        //показываем модель в случае если игрок ждет сам.
                        if (waitSize < 2) {

                            model.addObject("pOnline", pOnline);
                            model.addObject("login", login);
                            return model;
                            //если 2 игрока ожидают бой, формируем бой.
                            // и присваем значения игрок 1 и игрок 2 в бою, и удаляем себя из списка ожидающих игроков.
                        } else {
                            Battle b = new Battle();
                            b.p1 = uh.getUser();
                            wh.remove(b.p1.getLogin());
                            Set<String> waitKeys = wh.keySet();
                            if (b.p2.getLogin() != login) {
                                for (String key : waitKeys) {
                                    if (key != login) {
                                        b.p2 = wh.remove(key);
                                        break;
                                    } else {
                                    }
                                }
                                Integer i = new Random().nextInt();
                                // добавляем наш бой в Мапу в сервлет контекст.
                                bh.put(i, b);
                                //                             добавляем в сесию ID нашего боя.
                                req.getSession().setAttribute("battleId", i);
//                                проверочные строки можно раскоментировать посмотреть.
//                                System.out.println(((Map<Integer, Battle>) req.getServletContext().getAttribute("battle"))
//                                        .get(req.getServletContext().getAttribute("battleId")).p1.getLogin());
//                                System.out.println(((Map<Integer, Battle>) req.getServletContext().getAttribute("battle"))
//                                        .get(req.getServletContext().getAttribute("battleId")).p2.getLogin());
                                // to do when 2 player create battle
                                res.sendRedirect("/CardGame/battle.html");
                            }
                        }
                    } else {
                        model.addObject("level", u.getLvl());
                        model.addObject("pOnline", pOnline);
                        model.addObject("login", login);
                        return model;
                    }
                } else {
                    res.sendRedirect("/CardGame/battle.html");
                }
            }
        }
        return null;
    }
}
