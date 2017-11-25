package service;

import dao.CardDAO;
import entity.Card;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

public class CardService {

    @Autowired
    private CardDAO cdao;

    public void addCard(Set<Card> cards, Integer idInt) {
//        CardDAO cdao = ((CardDAO) SpringContextHolder.getContext().getBean("cdao"));
        boolean alreadyInDeck = false;
        if (cards.size() > 0) {
            for (Card s : cards) {
                if (s.getId() == idInt) {
                    alreadyInDeck = true;
                    break;
                }
            }
            if (!alreadyInDeck) {
                cards.add(cdao.getCardById(idInt));
            }
        } else {
            cards.add(cdao.getCardById(idInt));
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
