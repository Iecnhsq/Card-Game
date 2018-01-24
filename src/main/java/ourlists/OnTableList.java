package ourlists;

import entity.Card;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OnTableList extends CopyOnWriteArrayList<Card> {

    public List<Card> getTauntCards() {
        List<Card> tauntCards = new LinkedList<>();
        this.parallelStream().filter((c) -> (c.getTaunt())).forEachOrdered((c) -> {
            tauntCards.add(c);
        });
        return tauntCards;
    }

    public boolean checkTaunt() {
        return getTauntCards().size() > 0;
    }

}
