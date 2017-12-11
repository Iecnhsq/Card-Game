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
                bs.setDeckCards(b);
            }
            //начало нового хода, можно потом поменять чтобы было как в хс
            String endOfTurn = req.getParameter("end");
            if (endOfTurn != null) {
                bs.endTurn(b, login);
            }
            if (b.p1MadeTurn && b.p2MadeTurn) {
                bs.beginNewTurn(b);
            }

            String idString = req.getParameter("id");
            if (b.p1.getLogin().equals(login)) {
                if (idString != null) {
                    int id = Integer.parseInt(idString);
                    //у карт противника id будут передаваться с минусом
                    if (!b.p1HeroPowerSelected) {
                        bs.p1CreatureTurn(b, id);
                    } else {
                        bs.p1HeroPowerAttack(b, id);
                    }
                } else if (req.getParameter("heroAttack") != null) {
                    b.atackCardP1 = null;
                    if (b.manaP1 >= 2 && !b.p1Attacked) {
                        if (b.p1HeroPowerSelected) {
                            b.p1HeroPowerSelected = false;
                        } else {
                            b.p1HeroPowerSelected = true;
                        }
                    }
                }
            }

//            
//            
//            
//            
//            
//            
//            
//            
//            
//            
            if (b.p2.getLogin().equals(login)) {
                if (b.p1MadeTurn) {
                    if (idString != null) {
                        int id = Integer.parseInt(idString);
                        if (!b.p2HeroPowerSelected) {
                            bs.p2CreatureTurn(b, id);
                        } else {
                            bs.p2HeroPowerAttack(b, id);
                        }
                    } else if (req.getParameter("heroAttack") != null) {
                        b.atackCardP2 = null;
                        if (b.manaP2 >= 2 && !b.p2Attacked) {
                            if (b.p2HeroPowerSelected) {
                                b.p2HeroPowerSelected = false;
                            } else {
                                b.p2HeroPowerSelected = true;
                            }
                        }
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
            }
            model.addObject("p1Attacked", b.p1Attacked);
            model.addObject("p2Attacked", b.p2Attacked);
            model.addObject("p1HeroPowerSelected", b.p1HeroPowerSelected);
            model.addObject("p2HeroPowerSelected", b.p2HeroPowerSelected);
            model.addObject("p1TauntCards", b.onTableP1.getTauntCards());
            model.addObject("p2TauntCards", b.onTableP2.getTauntCards());
            model.addObject("p1HasTaunt", b.onTableP1.checkTaunt());
            model.addObject("p2HasTaunt", b.onTableP2.checkTaunt());
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
            return model;
        }
        return null;
    }
}
