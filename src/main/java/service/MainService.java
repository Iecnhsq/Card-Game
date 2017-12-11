package service;

import com.google.gson.Gson;
import dao.CardDAO;
import entity.Card;
import entity.Deck;
import entity.User;
import holders.UserHolder;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class MainService {


    @Autowired
    private CardDAO cdao;
    @Autowired
    private UserHolder uh;

    public Set<Card> getUserCards(ModelAndView model,String cardClassName) {
        User u = uh.getUser();
        Set<Card> cards = new LinkedHashSet<>();
        //берем карты из базы
        String uCard = u.getCards();
        if (uCard.equals("")) {
            // если там ничего нет сеттим туда джейсон колоды
            Deck d = new Deck();
            Gson g = new Gson();
            String json = g.toJson(d);
            u.setCards(json);
            
//            udao.updateUser(u);
        } else {
            // если карты есть то добавляем их в наш список
            System.out.println(uCard);
            Deck deck = new Gson().fromJson(uCard, Deck.class);
            deck.deck.forEach((i) -> {
                if(i<1000000){
                cards.add(cdao.getCardById(i, "BasicCard"));
                }else{
                cards.add(cdao.getCardById(i, cardClassName));
                }
            });
        }
        //добавляем в нашу модель наши карты и все карты
        model.addObject("card", cards);
        model.addObject("cards", cdao.getCards("BasicCard"));
        return cards;
    }
}
