package service;

import dao.CardDAO;
import entity.Card;
import holders.CardHolder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService {

    @Autowired
    private CardHolder ch;
    @Autowired
    private CardDAO cdao;

    private List<Card> getCardsInDB(String cardClassName) {
        return cdao.getCards(cardClassName);
    }

    public void getAllCardsInDB() {
        final String basicCard = "BasicCard";
        final String hunter = "Hunter";
        final String mage = "Mage";
        final String priest = "Priest";
        final String shaman = "Shaman";
        final String warrior = "Warrior";
        final String thief = "Thief";

        List<Card> BasicCard = getCardsInDB(basicCard);
        List<Card> Hunter = getCardsInDB(hunter);
        List<Card> Mage = getCardsInDB(mage);
        List<Card> Priest = getCardsInDB(priest);
        List<Card> Shaman = getCardsInDB(shaman);
        List<Card> Warrior = getCardsInDB(warrior);
        List<Card> Thief = getCardsInDB(thief);

        methodAdd(basicCard, BasicCard);
        methodAdd(hunter, Hunter);
        methodAdd(mage, Mage);
        methodAdd(priest, Priest);
        methodAdd(shaman, Shaman);
        methodAdd(thief, Thief);
        methodAdd(warrior, Warrior);

    }

    private void methodAdd(String className, List<Card> CardClass) {
        CardClass.forEach((c) -> {
            ch.putId(c.getId(), c);
        });
        ch.putClass(className, CardClass);
    }
}
