package service;

import com.google.gson.Gson;
import dao.CardDAO;
import dao.UserDAO;
import entity.Card;
import entity.Deck;
import entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class MainService {

    @Autowired
    private UserDAO udao;
    @Autowired
    private CardDAO cdao;

    public Set<Card> getUserCards(String login, ModelAndView model) {
        User u = udao.getUserByLogin(login);
//        CardDAO cdao = ((CardDAO) SpringContextHolder.getContext().getBean("cdao"));
//        UserDAO udao = ((UserDAO) SpringContextHolder.getContext().getBean("udao"));
        Set<Card> cards = new LinkedHashSet<>();
        //берем карты из базы
        String uCard = u.getCards();
        if (uCard.equals("")) {
            // если там ничего нет сеттим туда джейсон колоды
            Deck d = new Deck();
            Gson g = new Gson();
            String json = g.toJson(d);
            u.setCards(json);
            udao.updateUser(u);
        } else {
            // если карты есть то добавляем их в наш список
            Deck deck = new Gson().fromJson(uCard, Deck.class);
            deck.deck.forEach((i) -> {
                cards.add(cdao.getCardById(i));
            });
        }
        //добавляем в нашу модель наши карты и все карты
        model.addObject("card", cards);
        model.addObject("cards", cdao.getCards());
        return cards;
    }
//    public static boolean completeHand(String login) {
//        User u = ((UserDAO) SpringContextHolder.getContext().getBean("udao")).getUserByLogin(login);
//        String uCard = u.getCards();
//        Deck deck = new Gson().fromJson(uCard, Deck.class);
//        Set<Card> cards = new HashSet<>();
//        CardDAO cdao = ((CardDAO) SpringContextHolder.getContext().getBean("cdao"));
//        for (Integer i : deck.deck) {
//            cards.add(cdao.getCardById(i));
//        }
//        return cards.size() <= 10;
//    }
}
