package battle;

import com.google.gson.Gson;
import dao.CardDAO;
import entity.Card;
import entity.Deck;
import holders.UserHolder;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class BattleService {

    @Autowired
    private CardDAO cdao;
    @Autowired
    private UserHolder uh;

    public List<Card> setDeckP1(Battle b) {
        //раздаем карты 1му игроку 
        List<Card> l = new CopyOnWriteArrayList<>();
        Deck d = new Gson().fromJson(b.p1.getCards(), Deck.class);
        d.deck.forEach((i) -> {
            if (i < 1000000) {
                l.add(cdao.getCardById(i, "BasicCard"));
            } else {
                l.add(cdao.getCardById(i, b.p1.getClasss()));
            }
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
            if (i < 1000000) {
                l.add(cdao.getCardById(i, "BasicCard"));
            } else {
                l.add(cdao.getCardById(i, b.p2.getClasss()));
            }
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

    
    public void p1CreatureAttack(Battle b, int id) {
        if (!b.onTableP2.checkTaunt()) {
            for (Card c : b.onTableP2) {
                attackProcessP1Creature(b, c, id);
            }
        } else {
            for (Card c : b.onTableP2.getTauntCards()) {
                attackProcessP1Creature(b, c, id);
            }
        }
    }

    public void p2CreatureAttack(Battle b, int id) {
        if (!b.onTableP1.checkTaunt()) {
            for (Card c : b.onTableP1) {
                attackProcessP2Creature(b, c, id);
            }
        } else {
            for (Card c : b.onTableP1.getTauntCards()) {
                attackProcessP2Creature(b, c, id);
            }
        }
    }

    private void attackProcessP1Creature(Battle b,Card c, int id) {
        if (c.getId() == -id && b.atackCardP1 != null) {
            if (!c.getShield() && !b.atackCardP1.getShield()) {
                b.p1points = addPoint(c.getHealth(), b.atackCardP1.getDamage(), b.p1points);
                b.p2points = addPoint(b.atackCardP1.getHealth(), c.getDamage(), b.p2points);
                c.setHealth(c.getHealth() - b.atackCardP1.getDamage());
                b.atackCardP1.setHealth(b.atackCardP1.getHealth() - c.getDamage());
                if (c.getHealth() <= 0) {
                    b.onTableP2.remove(c);
                }
                if (b.atackCardP1.getHealth() <= 0) {
                    b.onTableP1.remove(b.atackCardP1);
                }
                b.p1ActiveCards.remove(b.atackCardP1.getId());
                b.atackCardP1 = null;
            } else if (c.getShield() && !b.atackCardP1.getShield()) {
                c.setShield(false);
                b.p2points = addPoint(b.atackCardP1.getHealth(), c.getDamage(), b.p2points);
                b.atackCardP1.setHealth(b.atackCardP1.getHealth() - c.getDamage());
                if (b.atackCardP1.getHealth() <= 0) {
                    b.onTableP1.remove(b.atackCardP1);
                }
                b.p1ActiveCards.remove(b.atackCardP1.getId());
                b.atackCardP1 = null;
            } else if (!c.getShield() && b.atackCardP1.getShield()) {
                b.atackCardP1.setShield(false);
                b.p1points = addPoint(c.getHealth(), b.atackCardP1.getDamage(), b.p1points);
                c.setHealth(c.getHealth() - b.atackCardP1.getDamage());
                if (c.getHealth() <= 0) {
                    b.onTableP2.remove(c);
                }
                b.p1ActiveCards.remove(b.atackCardP1.getId());
                b.atackCardP1 = null;
            } else {
                b.atackCardP1.setShield(false);
                c.setShield(false);
            }
        }
    }
    private void attackProcessP2Creature(Battle b,Card c,int id){
         if (c.getId() == -id && b.atackCardP2 != null) {
            if (!c.getShield() && !b.atackCardP2.getShield()) {
                b.p2points = addPoint(c.getHealth(), b.atackCardP2.getDamage(), b.p2points);
                b.p1points = addPoint(b.atackCardP2.getHealth(), c.getDamage(), b.p1points);
                c.setHealth(c.getHealth() - b.atackCardP2.getDamage());
                b.atackCardP2.setHealth(b.atackCardP2.getHealth() - c.getDamage());
                if (c.getHealth() <= 0) {
                    b.onTableP1.remove(c);
                }
                if (b.atackCardP2.getHealth() <= 0) {
                    b.onTableP2.remove(b.atackCardP2);
                }
                b.p2ActiveCards.remove(b.atackCardP2.getId());
                b.atackCardP2 = null;
            } else if (c.getShield() && !b.atackCardP2.getShield()) {
                c.setShield(false);
                b.p1points = addPoint(b.atackCardP2.getHealth(), c.getDamage(), b.p1points);
                b.atackCardP2.setHealth(b.atackCardP2.getHealth() - c.getDamage());
                if (b.atackCardP2.getHealth() <= 0) {
                    b.onTableP2.remove(b.atackCardP2);
                }
                b.p2ActiveCards.remove(b.atackCardP2.getId());
                b.atackCardP2 = null;
            } else if (!c.getShield() && b.atackCardP2.getShield()) {
                b.atackCardP2.setShield(false);
                b.p2points = addPoint(c.getHealth(), b.atackCardP2.getDamage(), b.p2points);
                c.setHealth(c.getHealth() - b.atackCardP2.getDamage());
                if (c.getHealth() <= 0) {
                    b.onTableP1.remove(c);
                }
                b.p2ActiveCards.remove(b.atackCardP2.getId());
                b.atackCardP2 = null;
            } else {
                b.atackCardP2.setShield(false);
                c.setShield(false);
            }
        }
    }
}
