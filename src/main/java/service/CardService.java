package service;

import com.google.gson.Gson;
import entity.Card;
import entity.Deck;
import entity.User;
import holders.CardHolder;
import holders.UserHolder;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class CardService {

    @Autowired
    private UserHolder uh;
    @Autowired
    private CardHolder ch;


    private Card addCard(Integer idInt) {
        return ch.getCardById(idInt);
    }

    private void removeCard(Set<Card> cards, Integer idInt) {
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

    public boolean commitGetCard(String getCard) {
        return getCard != null;
    }

    public Set<Card> writeCards(int id, Set<Card> cards) {
        if (id == 0) {
            cards.clear();
            //если больше 0 добавляем выбраную карту в список выбраных карт.
        } else if (id > 0) {
            cards.add(addCard(id));
//                  если меньше 0 то удаляем из списка выбраных карт.
            System.out.println(cards.size());
        } else if (id < 0) {
            removeCard(cards, id);
            System.out.println(cards.size());
        }
        return cards;
        // если количество выбраных карт превышает 10 карт возврашаемся обратно на card.html без изменений
    }

    public boolean deckIsFull(Set<Card> cards) {
        return cards.size() > 10;
    }

    public boolean cardSelected(String idString) {
        return idString != null;
    }

    public void addClassCardInSession(HttpServletRequest req, String heroClass) {
        req.getSession().setAttribute("heroClass", heroClass);
        req.getSession().setAttribute("cardClass", ch.getCardByClass(heroClass));

    }

    public void setEmptyDeck(String idClass) {
        Deck d = new Deck();
        User u = uh.getUser();
        u.setCards(new Gson().toJson(d));
        u.setClasss(idClass);
        uh.set(u);
    }

    public boolean classSelected(String idClass) {
        return idClass != null;
    }

    public boolean presentPlayerOnline(int online) {
        return online > 0;
    }

    public boolean userAuthorized(String login) {
        return login != null;
    }

    public void setDeck(Set<Card> cards) {
        Deck d = new Deck();
        System.out.println(cards.size());
        cards.forEach((c) -> {
            d.deck.add(c.getId());
            d.deck.size();
        });
        String userCards = new Gson().toJson(d);
        System.out.println(userCards);
        uh.getUser().setCards(userCards);
                
    }
}
