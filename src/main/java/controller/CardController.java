package controller;

import dao.UserDAO;
import entity.Card;
import entity.User;
import holders.CardHolder;
import holders.OnlineHolder;
import holders.UserHolder;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.MainService;

@Controller
public class CardController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private CardService cserv;
    @Autowired
    private MainService mserv;
    @Autowired
    private OnlineHolder oh;
    @Autowired
    private UserHolder uh;
    @Autowired
    private CardHolder ch;

    @RequestMapping("/card.html")
    public ModelAndView card(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("card");
        List<Card> allCards = ch.getCardByClass("BasicCard");
        String login = ((String) req.getSession().getAttribute("login"));
        User u = uh.getUser();
        // в случае если на нашу страницу перешел не зарегестрированый пользиватель его оправляем на мейн страницу.       
        if (cserv.userAuthorized(login)) {
            System.out.println("in login");
            //выводим количество игроков онлайн на экран.
            int Online = oh.size();
            String pOnline = "No Players online";
            if (cserv.presentPlayerOnline(Online)) {
                pOnline = "Players online: " + Online;
            }
            model.addObject("pOnline", pOnline);
            model.addObject("user", u);
            // берем айди карты
            String idString = req.getParameter("id");
            Set<Card> cards;
            String idClass = req.getParameter("idclass");
            String classNameJoin = u.getClasss();

            if (cserv.classSelected(idClass)) {
                cserv.addClassCardInSession(req, idClass);
                cserv.setEmptyDeck(idClass);
                udao.updateUser(u);
            } else {
                cserv.addClassCardInSession(req, classNameJoin);
            }
            String CardClassName = u.getClasss();
//            req.setAttribute("cardClass", className);
            cards = mserv.getUserCards(model, CardClassName);

            if (cserv.cardSelected(idString)) {
                System.out.println("in id string");
                int id = Integer.parseInt(idString);
                cards = cserv.writeCards(id, cards);
                if (cserv.deckIsFull(cards)) {
                    try {
                        resp.sendRedirect("card.html");
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex);
                    }
                    return null;
                }
            }

            //запускаем цыкл для переноса значений из cards в deck.deck
            cserv.setDeck(cards);
            String getCards = req.getParameter("getCards");
            if (cserv.commitGetCard(getCards)) {
                udao.updateUser(u);
                try {
                    resp.sendRedirect("main.html");
                } catch (IOException ex) {
                    System.out.println("Error: " + ex);
                }
                return null;
            }
            model.addObject("deckCards", cards);
            for (int j = 0; j <= 10; j++) {
                model.addObject("level" + j + "Cards", cserv.getCardsByLvl(allCards, j));
            }
            return model;
        } else {
            try {
                resp.sendRedirect("login.html");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return null;
    }
}
