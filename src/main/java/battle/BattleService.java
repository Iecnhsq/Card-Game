package battle;

import com.google.gson.Gson;
import entity.Card;
import entity.Deck;
import holders.CardHolder;
import holders.UserHolder;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class BattleService {

    @Autowired
    private CardHolder ch;
    @Autowired
    private UserHolder uh;

    public void setDeckCards(Battle b) {
        // заполняем колоду 1го игрока
        b.deckP1 = setDeckP1(b);
        System.out.println("deck in battle:" + b.deckP1.size());
        // заполняем колоду 2го игрока
        b.deckP2 = setDeckP2(b);
        // и даем им по 2 случайных карты из колоды на руки
        b.onHandP1 = set2cardsP1toHand(b);
        b.onHandP2 = set2cardsP2toHand(b);
    }

    private List<Card> setDeckP1(Battle b) {
        //раздаем карты 1му игроку 
        List<Card> l = new CopyOnWriteArrayList<>();
        Deck d = new Gson().fromJson(b.p1.getCards(), Deck.class);
        d.deck.forEach((i) -> {
                l.add(ch.getCardById(i));
        });
        return l;
    }

    private List<Card> setDeckP2(Battle b) {
        //раздаем карты 2му игроку 
        List<Card> l = new CopyOnWriteArrayList<>();
        //создаем колоду и сеттим ей значение из карт второго игрока в бое
        Deck d = new Gson().fromJson(b.p2.getCards(), Deck.class);
        // проходим по списку id наших карт из колоды
        d.deck.forEach((i) -> {
            // добавляем в наш список карты из колоды
 
                l.add(ch.getCardById(i));

        });
        return l;
    }

    private List<Card> set2cardsP1toHand(Battle b) {
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

    private List<Card> set2cardsP2toHand(Battle b) {
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

    public void endTurn(Battle b, String login) {
        if (b.p1.getLogin().equals(login)) {
            p1EndTurn(b);
        }
        if (b.p2.getLogin().equals(login)) {
            p2EndTurn(b);
        }
    }

    public void beginNewTurn(Battle b) {
        b.p1MadeTurn = false;
        b.p2MadeTurn = false;
        b.turn++;
        if (b.turn < 10) {
            b.manaP1 = b.turn;
            b.manaP2 = b.turn;
        } else {
            b.manaP1 = 10;
            b.manaP2 = 10;
        }
        if (!b.deckP1.isEmpty()) {
            b.onHandP1.add(add1CardToP1Hand(b));
            b.onHandP2.add(add1CardToP2Hand(b));
        }
        for (Card c : b.onTableP1) {
            b.p1ActiveCards.put(c.getId(), c);
        }
        for (Card c : b.onTableP2) {
            b.p2ActiveCards.put(c.getId(), c);
        }
    }

    private void p1EndTurn(Battle b) {
        b.p1ActiveCards.clear();
        b.atackCardP1 = null;
        b.p1MadeTurn = true;
        b.p1HeroPowerSelected = false;
        b.p1Attacked = false;
    }

    private void p2EndTurn(Battle b) {
        b.p2ActiveCards.clear();
        b.atackCardP2 = null;
        b.p2MadeTurn = true;
        b.p2HeroPowerSelected = false;
        b.p2Attacked = false;
    }

    public void p1CreatureTurn(Battle b, int id) {
        if (id < 0) {
            p1CreatureToCreatureAttack(b, id);
            //если выбрали свою карту
        } else if (id > 0) {
            p1CardChoice(b, id);
            //id=0 у перса противника
        } else if (id == 0 && !b.onTableP2.checkTaunt() && b.atackCardP1 != null) {
            // добавляем очки 1му игроку за атаку героя
            p1CreatureToHeroAttack(b);
        }
    }

    public void p2CreatureTurn(Battle b, int id) {
        //у карт противника id будут передаваться с минусом
        if (id < 0) {
            p2CreatureToCreatureAttack(b, id);
            //если выбрали свою карту
        } else if (id > 0) {
            p2CardChoice(b, id);
            //id=0 у перса противника
        } else if (id == 0 && b.atackCardP2 != null && !b.onTableP1.checkTaunt()) {
            p2CreatureToHeroAttack(b);
        }
    }

    private void p1CreatureToCreatureAttack(Battle b, int id) {
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

    private void p2CreatureToCreatureAttack(Battle b, int id) {
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

    private void attackProcessP1Creature(Battle b, Card c, int id) {
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

    private void attackProcessP2Creature(Battle b, Card c, int id) {
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

    private void p1CardChoice(Battle b, int id) {
        Set<Integer> activeCardsKeySet = b.p1ActiveCards.keySet();
        for (int cardId : activeCardsKeySet) {
            if (cardId == id) {
                for (Card c : b.onTableP1) {
                    if (c.getId() == id) {
                        if (c.equals(b.atackCardP1)) {
                            b.atackCardP1 = null;
                        } else {
                            b.atackCardP1 = c;
                        }
                        break;
                    }
                }
            }
        }
        for (Card c : b.onHandP1) {
            if (c.getId() == id) {
                if (c.getLevel() <= b.manaP1) {
                    b.manaP1 -= c.getLevel();
                    b.onTableP1.add(c);
                    if (c.getCharge()) {
                        b.p1ActiveCards.put(id, c);
                    }
                    b.onHandP1.remove(c);
                    break;
                }
            }
        }
    }

    private void p2CardChoice(Battle b, int id) {
        if (b.p2ActiveCards != null) {
            Set<Integer> activeCardsKeySet = b.p2ActiveCards.keySet();
            for (int cardId : activeCardsKeySet) {
                if (cardId == id) {
                    for (Card c : b.onTableP2) {
                        if (c.getId() == id) {
                            if (c.equals(b.atackCardP2)) {
                                b.atackCardP2 = null;
                            } else {
                                b.atackCardP2 = c;
                            }
                            break;
                        }
                    }
                }
            }
        }
        for (Card c : b.onHandP2) {
            if (c.getId() == id) {
                if (c.getLevel() <= b.manaP2) {
                    b.manaP2 -= c.getLevel();
                    b.onTableP2.add(c);
                    if (c.getCharge()) {
                        b.p2ActiveCards.put(id, c);
                    }
                    b.onHandP2.remove(c);
                    break;
                }
            }
        }
    }

    private void p1CreatureToHeroAttack(Battle b) {
        b.p1points = addPoint(b.healthP2, b.atackCardP1.getDamage(), b.p1points);
        b.healthP2 -= b.atackCardP1.getDamage();
        b.p1ActiveCards.remove(b.atackCardP1.getId());
        b.atackCardP1 = null;
    }

    private void p2CreatureToHeroAttack(Battle b) {
        b.p2points = addPoint(b.healthP1, b.atackCardP2.getDamage(), b.p2points);
        b.healthP1 -= b.atackCardP2.getDamage();
        b.p2ActiveCards.remove(b.atackCardP2.getId());
        b.atackCardP2 = null;
    }

    public void p1HeroPowerAttack(Battle b, int id) {
        if (id == 0) {
            p1HeroAttackEnemyHero(b);
        }
        if (id > 0) {
            p1HeroAttackHisCard(b, id);
        }
        if (id < 0) {
            p1HeroAttackEnemyCard(b, id);
        }
    }

    public void p2HeroPowerAttack(Battle b, int id) {
        if (id == 0) {
            p2HeroAttackEnemyHero(b);
        }
        if (id > 0) {
            p2HeroAttackHisCard(b, id);
        }
        if (id < 0) {
            p2HeroAttackEnemyCard(b, id);
        }
    }

    private void p1HeroAttackEnemyHero(Battle b) {
        b.healthP2 -= 1;
        b.manaP1 -= 2;
        b.p1points += 1;
        b.p1HeroPowerSelected = false;
        b.p1Attacked = true;
    }

    private void p2HeroAttackEnemyHero(Battle b) {
        b.healthP1 -= 1;
        b.p2HeroPowerSelected = false;
        b.manaP2 -= 2;
        b.p2points += 1;
        b.p2Attacked = true;
    }

    private void p1HeroAttackHisCard(Battle b, int id) {
        for (Card c : b.onTableP1) {
            if (c.getId() == id) {
                if (!c.getShield()) {
                    c.setHealth(c.getHealth() - 1);
                    if (c.getHealth() <= 0) {
                        b.onTableP1.remove(c);
                    }
                    b.manaP1 -= 2;
                    b.p1points += 1;
                    b.p1HeroPowerSelected = false;
                    b.p1Attacked = true;
                } else {
                    c.setShield(false);
                    b.manaP1 -= 2;
                    b.p1HeroPowerSelected = false;
                    b.p1Attacked = true;
                }
            }
        }
    }

    private void p2HeroAttackHisCard(Battle b, int id) {
        for (Card c : b.onTableP2) {
            if (c.getId() == id) {
                if (!c.getShield()) {
                    c.setHealth(c.getHealth() - 1);
                    if (c.getHealth() <= 0) {
                        b.onTableP2.remove(c);
                    }
                    b.p2HeroPowerSelected = false;
                    b.manaP2 -= 2;
                    b.p2points += 1;
                    b.p2Attacked = true;
                } else {
                    c.setShield(false);
                    b.p2HeroPowerSelected = false;
                    b.manaP2 -= 2;
                    b.p2Attacked = true;
                }
            }
        }
    }

    private void p1HeroAttackEnemyCard(Battle b, int id) {
        for (Card c : b.onTableP2) {
            if (c.getId() == -id) {
                if (!c.getShield()) {
                    c.setHealth(c.getHealth() - 1);
                    if (c.getHealth() <= 0) {
                        b.onTableP2.remove(c);
                    }
                    b.manaP1 -= 2;
                    b.p1points += 1;
                    b.p1HeroPowerSelected = false;
                    b.p1Attacked = true;
                } else {
                    c.setShield(false);
                    b.manaP1 -= 2;
                    b.p1HeroPowerSelected = false;
                    b.p1Attacked = true;
                }
            }
        }
    }

    private void p2HeroAttackEnemyCard(Battle b, int id) {
        for (Card c : b.onTableP1) {
            if (c.getId() == -id) {
                if (!c.getShield()) {
                    c.setHealth(c.getHealth() - 1);
                    if (c.getHealth() <= 0) {
                        b.onTableP1.remove(c);
                    }
                    b.p2HeroPowerSelected = false;
                    b.manaP2 -= 2;
                    b.p2points += 1;
                    b.p2Attacked = true;
                } else {
                    c.setShield(false);
                    b.p2HeroPowerSelected = false;
                    b.manaP2 -= 2;
                    b.p2Attacked = true;
                }
            }
        }
    }

}
