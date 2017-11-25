package battle;

import com.google.gson.Gson;
import dao.CardDAO;
import entity.Card;
import entity.Deck;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class BattleService {

    @Autowired
    private CardDAO cdao;

    public List<Card> setDeckP1(Battle b) {
        //раздаем карты 1му игроку 
        List<Card> l = new CopyOnWriteArrayList<>();
        Deck d = new Gson().fromJson(b.p1.getCards(), Deck.class);
        d.deck.forEach((i) -> {
            l.add(cdao.getCardById(i));
        });
        return l;
    }

    public List<Card> setDeckP2(Battle b) {
        //раздаем карты 2му игроку 
        List<Card> l = new CopyOnWriteArrayList<>();
        //создаем колоду и сеттим ей значение из карт второго игрока в бое
        Deck d = new Gson().fromJson(b.p2.getCards(), Deck.class);
        // проходим по списку id наших карт из колоды
        d.deck.forEach((i) -> {
            // добавляем в наш список карты из колоды
            l.add(cdao.getCardById(i));
        });
        return l;
    }

    public List<Card> set2cardsP1toHand(Battle b) {
//            добавляем 2 карты в руки игроку
        List<Card> l = new CopyOnWriteArrayList<>();
        //по одному проходу цыкла на карту
        for (Integer i = 0; i < 2; i++) {
            //выбираем рандомную карту из колоды первого игрока
            // добавляем в наш спискок
            // убираем эту карту из колоды
            l.add(b.deckP1.remove(new Random().nextInt(b.deckP1.size())));

        }
        return l;
    }

    public List<Card> set2cardsP2toHand(Battle b) {
//                    добавляем 2 карты в руки игроку
        List<Card> l = new CopyOnWriteArrayList<>();
        for (Integer i = 0; i < 2; i++) {
            //выбираем рандомную карту из колоды второго игрока
            // добавляем в наш спискок
            // убираем эту карту из колоды
            l.add(b.deckP2.remove(new Random().nextInt(b.deckP2.size())));
        }
        return l;
    }

    public void p1Turn(Battle b, ModelAndView model, HttpServletRequest req) {
        //готовая логика хода
    }

    public void p2Turn(Battle b, ModelAndView model, HttpServletRequest req) {
        //готовая логика хода
    }

    public Card add1CardToP1Hand(Battle b) {
        if (b.deckP1.size() > 0) {
            return b.deckP1.remove(new Random().nextInt(b.deckP1.size()));
        } else {
            return b.deckP1.remove(0);
        }
    }

    public Card add1CardToP2Hand(Battle b) {
        if (b.deckP2.size() > 0) {
            return b.deckP2.remove(new Random().nextInt(b.deckP2.size()));
        } else {
            return b.deckP2.remove(0);
        }
    }

    public int addPoint(int health, int damage, int points) {
        int point = 0;
        if (health > damage) {
            point = damage;
        } else {
            point = health;
        }
        int p = point + points;
        return p;

    }

}
