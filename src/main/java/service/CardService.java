package service;

import dao.CardDAO;
import entity.Card;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

public class CardService {

    @Autowired
    private CardDAO cdao;

    public Card addCard(String cardClass, Integer idInt) {

        if (cardClass == null||idInt<1000000) {
            System.out.println("you get card id="+idInt+"");
            return cdao.getCardById(idInt, "BasicCard");
        } else {
            System.out.println("you get card id="+idInt+"");
            return cdao.getCardById(idInt, cardClass);
        }
        

    }

    public void removeCard(Set<Card> cards, Integer idInt) {
        for (Card c : cards) {
            if (c.getId() == (-idInt)) {
                cards.remove(c);
                break;
            }
        }
    }

    public List<Card> getCardsByLvl(List<Card> cards, int lvl) {
        List<Card> levelList = new LinkedList<>();
        cards.forEach((c) -> {
            if (c.getLevel() == lvl) {
                levelList.add(c);
            }
        });
        return levelList;
    }
//
//    public static ModelAndView gsonNote(User u, List<Card> allCards, Set<Card> cards) {
//        UserDAO udao = ((UserDAO) SpringContextHolder.getContext().getBean("udao"));
//        ModelAndView model = new ModelAndView();
//        Deck d = new Deck();
//        Gson g = new Gson();
//        String json = g.toJson(d);
//        u.setCards(json);
//        udao.updateUser(u);
//        model.addObject("cards", allCards);
//        model.addObject("card", cards);
//        System.out.println("1 block");
//        return model;
//    }
}
