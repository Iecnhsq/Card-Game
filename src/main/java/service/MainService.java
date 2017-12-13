package service;

import com.google.gson.Gson;
import entity.Card;
import entity.Deck;
import entity.User;
import holders.CardHolder;
import holders.UserHolder;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class MainService {

    @Autowired
    private CardHolder ch;
    @Autowired
    private UserHolder uh;

    public Set<Card> getUserCards(ModelAndView model, String cardClassName) {
        User u = uh.getUser();
        Set<Card> cards = new LinkedHashSet<>();
        //берем карты из базы
        String uCard = u.getCards();
        // если карты есть то добавляем их в наш список
        System.out.println(uCard);
        Deck deck = new Gson().fromJson(uCard, Deck.class);
        deck.deck.forEach((i) -> {
            cards.add(ch.getCardById(i));
        });
        //добавляем в нашу модель наши карты и все карты
        model.addObject("card", cards);
        model.addObject("cards", ch.getCardByClass("BasicCard"));
        return cards;
    }

}
