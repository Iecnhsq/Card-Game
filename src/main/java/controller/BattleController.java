package controller;

import battle.Battle;
import battle.BattleService;
import entity.Card;
import holders.BattlesHolder;
import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BattleController {

    public static final Logger LOGGER = Logger.getLogger(BattleController.class);
    @Autowired
    private BattleService bs;
    @Autowired
    private BattlesHolder bh;

    @RequestMapping("/battle.html")
    public ModelAndView fight(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView();
        String login = ((String) req.getSession().getAttribute("login"));
        if (login == null) {
            LOGGER.info("has redirect to main becouse you haven`t login");
            resp.sendRedirect("/CardGame/main.html");

        } else {
            // пытаемся взять бой по атирибуту battleId
            LOGGER.info("you in battle whis login:" + login);
            Battle b = null;
            try {
                b = bh.get((Integer) req.getSession().getAttribute("battleId"));
//                если не получаеться возвращаемся на main.html
            } catch (Exception e) {
                resp.sendRedirect("/CardGame/main.html");
                return null;
            }
            // Проверяем сформирован ли бой по признаку deckP1==null
            if (b.deckP1 == null) {
                // заполняем колоду 1го игрока
                b.deckP1 = bs.setDeckP1(b);
                System.out.println("deck in battle:" + b.deckP1.size());
                // заполняем колоду 2го игрока
                b.deckP2 = bs.setDeckP2(b);
                // и даем им по 2 случайных карты из колоды на руки
                b.onHandP1 = bs.set2cardsP1toHand(b);
                b.onHandP2 = bs.set2cardsP2toHand(b);
            }
            //начало нового хода, можно потом поменять чтобы было как в хс
            String endOfTurn = req.getParameter("end");
            if (endOfTurn != null) {
                if (b.p1.getLogin().equals(login)) {
                    b.p1ActiveCards.clear();
                    b.atackCardP1 = null;
                    b.p1MadeTurn = true;
                }
                if (b.p2.getLogin().equals(login)) {
                    b.p2ActiveCards.clear();
                    b.atackCardP2 = null;
                    b.p2MadeTurn = true;
                }
            }
            if (b.p1MadeTurn && b.p2MadeTurn) {
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
                    b.onHandP1.add(bs.add1CardToP1Hand(b));
                    b.onHandP2.add(bs.add1CardToP2Hand(b));
                }
                for (Card c : b.onTableP1) {
                    b.p1ActiveCards.put(c.getId(), c);
                }
                for (Card c : b.onTableP2) {
                    b.p2ActiveCards.put(c.getId(), c);
                }
            }

            String idString = req.getParameter("id");
            if (b.p1.getLogin().equals(login)) {
                if (idString != null) {
                    int id = Integer.parseInt(idString);
                    //у карт противника id будут передаваться с минусом
                    if (id < 0) {
                        for (Card c : b.onTableP2) {
                            if (c.getId() == -id) {
                                // Считаем очки при нанесении урона,
                                //картой первого игрока карте 2го игрока.
                                b.p1points = bs.addPoint(c.getHealth(), b.atackCardP1.getDamage(), b.p1points);
                                //добавляем очки за защиту от вражеской карты
                                b.p2points = bs.addPoint(b.atackCardP1.getHealth(), c.getDamage(), b.p2points);
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
                            }
                        }
                        //если выбрали свою карту
                    } else if (id > 0) {
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
                                    b.onHandP1.remove(c);
                                    System.out.println(c.getId());
                                    break;
                                }
                            }
                        }
                        //id=0 у перса противника
                    } else if (id == 0) {
                        if (b.atackCardP1 != null) {
                            // добавляем очки 1му игроку за атаку героя
                            b.p1points = bs.addPoint(b.healthP2, b.atackCardP1.getDamage(), b.p1points);
                            b.healthP2 -= b.atackCardP1.getDamage();
                            b.p1ActiveCards.remove(b.atackCardP1.getId());
                            b.atackCardP1 = null;
                        }
                    }
                } else if (req.getParameter("heroAttack") != null) {
                    if (b.manaP1 >= 2) {
                        b.p1points = bs.addPoint(b.healthP2, 2, b.p1points);
                        b.healthP2 -= 2;
                        b.manaP1 -= 2;
                        model.addObject("p1Attacked", true);
                    }
                }
            }
            if (b.healthP2 <= 0) {
                b.p1Win = true;
                resp.sendRedirect("/CardGame/finish.html");
                return null;
            }
            if (b.healthP1 <= 0) {
                b.p2Win = true;
                resp.sendRedirect("/CardGame/finish.html");
                return null;
//                }
            }

            if (b.p2.getLogin().equals(login)) {
                if (b.p1MadeTurn) {
                    if (idString != null) {
                        int id = Integer.parseInt(idString);
                        //у карт противника id будут передаваться с минусом
                        if (id < 0) {
                            for (Card c : b.onTableP1) {
                                if (c.getId() == -id) {
                                    //добавляем очки второму игроку за атаку вражеской карты
                                    b.p2points = bs.addPoint(c.getHealth(), b.atackCardP2.getDamage(), b.p2points);
                                    //добавляем очки за защиту от вражеской карты
                                    b.p1points = bs.addPoint(b.atackCardP2.getHealth(), c.getDamage(), b.p1points);
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
                                }
                            }
                            //если выбрали свою карту
                        } else if (id > 0) {
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
                                        b.onHandP2.remove(c);
                                        break;
                                    }
                                }
                            }
                            //id=0 у перса противника
                        } else if (id == 0) {
                            if (b.atackCardP2 != null) {
                                b.p2points = bs.addPoint(b.healthP1, b.atackCardP2.getDamage(), b.p2points);
                                b.healthP1 -= b.atackCardP2.getDamage();
                                b.p2ActiveCards.remove(b.atackCardP2.getId());
                                b.atackCardP2 = null;
                            }
                        }
                    } else if (req.getParameter("heroAttack") != null) {
                        if (b.manaP2 >= 2) {
                            b.p2points = bs.addPoint(b.healthP1, 2, b.p2points);
                            b.manaP2 -= 2;
                            b.healthP1 -= 2;
                            model.addObject("p2Attacked", true);
                        }
                    }
                }
            }           
            model.addObject("Char1Health", b.healthP1);
            model.addObject("Char2Health", b.healthP2);
            model.addObject("Char1Mana", b.manaP1);
            model.addObject("Char2Mana", b.manaP2);
            model.addObject("class1", b.p1.getClasss());
            model.addObject("class2", b.p2.getClasss());
            model.addObject("onHandP1", b.onHandP1);
            model.addObject("onHandP2", b.onHandP2);
            model.addObject("deckP1", b.deckP1);
            model.addObject("deckP2", b.deckP2);
            model.addObject("p1", b.p1.getLogin());
            model.addObject("p2", b.p2.getLogin());
            model.addObject("onTableP1", b.onTableP1);
            model.addObject("onTableP2", b.onTableP2);
            model.addObject("p2turn", b.p1MadeTurn);
            model.addObject("p1turn", !b.p1MadeTurn);
            model.addObject("p1ActiveCardsIds", b.p1ActiveCards.keySet());
            model.addObject("p2ActiveCardsIds", b.p2ActiveCards.keySet());
            if (b.atackCardP1 != null) {
                model.addObject("p1AttackCardId", b.atackCardP1.getId());
            }
            if (b.atackCardP2 != null) {
                model.addObject("p2AttackCardId", b.atackCardP2.getId());
            }
            if (b.p1.getLogin().equals(login)) {
                model.addObject("p1Logged", true);
                model.addObject("p2Logged", false);
            } else {
                model.addObject("p1Logged", false);
                model.addObject("p2Logged", true);
            }
            System.out.println(b.p1points + " " + b.p2points);
            System.out.println("P1 deck: " + b.deckP1.size());
            return model;
        }
        return null;
    }
}
