package holders;

import entity.Card;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CardHolder {
// we have 2 maps holders card

    private final Map<String, List<Card>> cardClasses = new ConcurrentHashMap<>();
    private final Map<Integer, Card> cardID = new ConcurrentHashMap<>();

    public void putClass(String className, List<Card> cardClass) {
        cardClasses.put(className, cardClass);
    }

    public List<Card> getCardByClass(String className) {
        return cardClasses.get(className);
    }

    public void putId(int id, Card card) {
        cardID.put(id, card);
    }

    public Card getCardById(int id) {
        return cardID.get(id);
    }

}
