package controller;

import com.google.gson.Gson;
import dao.CardDAO;
import dao.UserDAO;
import entity.Card;
import entity.Deck;
import entity.User;
import holders.OnlineHolder;
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

    @RequestMapping("/card.html")
    public ModelAndView card(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView("card");
        List<Card> allCards = cdao.getCards();
        String login = ((String) req.getSession().getAttribute("login"));
        User u = udao.getUserByLogin(login);
        // в случае если на нашу страницу перешел не зарегестрированый пользиватель его оправляем на мейн страницу.       
        if (login != null) {
            //выводим количество игроков онлайн на экран.
            int Online = oh.size();
            String pOnline = "No Players online";
            if (Online > 0) {
                pOnline = "Players online: " + Online;
            }
            model.addObject("pOnline", pOnline);
            model.addObject("user", u);
            // берем айди карты
            String id = req.getParameter("id");
            Set<Card> cards;
//          если у пользывателя в базе пустое поле card то приводим его к типу Deck.class что бы можно было его обрабарывать в наше json.
            // иначе добавляем в наш колоду выбраных карт карты которые уже были выбраны ранее.            
            cards = mserv.getUserCards(login, model);
            // в случаее если мы не выбрали не одной карты и перезапустили страницу, возвращаем ее без изменений.
            if (id == null) {
                return model;
                // иначе приводим наш параметер id к типу int, и если он равен нулю очищаем список выбраных карт.
            } else {

                int idInt = Integer.parseInt(id);
                if (idInt == 0) {
                    cards.clear();
                    //если больше 0 добавляем выбраную карту в список выбраных карт.
                } else if (idInt > 0) {
                    cserv.addCard(cards, idInt);
//                  если меньше 0 то удаляем из списка выбраных карт.
                } else if (idInt < 0) {
                    cserv.removeCard(cards, idInt);
                }

                // если количество выбраных карт превышает 10 карт возврашаемся обратно на card.html без изменений
                if (cards.size() > 10) {
                    resp.sendRedirect("card.html");
                    //иначе добавляем карту
                } else {
                    Deck deck = new Deck();
                    //запускаем цыкл для переноса значений из cards в deck.deck
                    cards.forEach((c) -> {
                        deck.deck.add(c.getId());
                    });
                    // приводим сет Id выбраных карт к типу стринг через json, и обновляем значение поля card у пользивателя в БД,
                    //дальше выводим мрдель но экран.
                    String userCards = new Gson().toJson(deck);
                    u.setCards(userCards);
                    udao.updateUser(u);
                    model.addObject("cards", allCards);
                    model.addObject("card", cards);
                    return model;
                }
            }
        } else {
            resp.sendRedirect("login.html");
        }
        return null;
    }
}
