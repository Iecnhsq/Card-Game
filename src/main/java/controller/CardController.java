package controller;

import com.google.gson.Gson;
import dao.CardDAO;
import dao.UserDAO;
import entity.Card;
import entity.Deck;
import entity.User;
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
    private CardDAO cdao;
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

    @RequestMapping("/card.html")
    public ModelAndView card(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView("card");
        List<Card> allCards = cdao.getCards("BasicCard");
        String login = ((String) req.getSession().getAttribute("login"));
        User u = uh.getUser();
        // в случае если на нашу страницу перешел не зарегестрированый пользиватель его оправляем на мейн страницу.       
        if (login != null) {
            System.out.println("in login");
            //выводим количество игроков онлайн на экран.
            int Online = oh.size();
            String pOnline = "No Players online";
            if (Online > 0) {
                pOnline = "Players online: " + Online;
            }
            model.addObject("pOnline", pOnline);
            model.addObject("user", u);
            // берем айди карты
            String idString = req.getParameter("id");
            Set<Card> cards;
            String idClass = req.getParameter("idclass");
            String classNameJoin = u.getClasss();
            req.getSession().setAttribute("cardClass", cdao.getCards(classNameJoin));
            req.getSession().setAttribute("heroClass", classNameJoin);
            boolean newClass = false;
            if (idClass != null) {
                newClass = true;
                req.getSession().setAttribute("heroClass", idClass);
                req.getSession().setAttribute("cardClass", cdao.getCards(idClass));
                u.setClasss(idClass);
                uh.set(u);
                udao.updateUser(u);
            }

            String CardClassName = u.getClasss();
//            req.setAttribute("cardClass", className);
            cards = mserv.getUserCards(model, CardClassName);
            if (newClass) {
                cards.clear();
            }
            if (idString != null) {
                System.out.println("in id string");
                int id = Integer.parseInt(idString);
                if (id == 0) {
                    cards.clear();
                    //если больше 0 добавляем выбраную карту в список выбраных карт.
                } else if (id > 0) {
                    cards.add(cserv.addCard(CardClassName, id));
//                  если меньше 0 то удаляем из списка выбраных карт.
                    System.out.println(cards.size());
                } else if (id < 0) {
                    cserv.removeCard(cards, id);
                }
                // если количество выбраных карт превышает 10 карт возврашаемся обратно на card.html без изменений
                if (cards.size() > 10) {
                    resp.sendRedirect("card.html");
                    return null;
                }

//                req.getSession().setAttribute("cards", cards);
            }
            Deck deck = new Deck();
            //запускаем цыкл для переноса значений из cards в deck.deck
            cards.forEach((c) -> {
                deck.deck.add(c.getId());
            });
            String userCards = new Gson().toJson(deck);
            if (newClass) {
                u.setClasss(idClass);

            } else {
                u.setClasss(classNameJoin);
            }
            u.setCards(userCards);
            uh.set(u);
            if (req.getParameter("getCards") != null) {
                udao.updateUser(u);
                resp.sendRedirect("main.html");
                return null;
            }
            for(Card c:cards){
                System.out.println(c.getId());
            }
            model.addObject("deckCards", cards);
            for (int j = 0; j <= 10; j++) {
                model.addObject("level" + j + "Cards", cserv.getCardsByLvl(allCards, j));
            }
            return model;
        } else {
            resp.sendRedirect("login.html");
        }
        return null;
    }
}
