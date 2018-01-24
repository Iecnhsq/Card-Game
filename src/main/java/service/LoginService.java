package service;

import dao.CardDAO;
import dao.UserDAO;
import entity.Card;
import entity.User;
import holders.CardHolder;
import holders.OnlineHolder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class LoginService {

    @Autowired
    private CardHolder ch;
    @Autowired
    private CardDAO cdao;
    @Autowired
    private UserDAO udao;
    @Autowired
    private OnlineHolder oh;

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

    public boolean matchPassword(String userPass, String parameterPass) {
        return userPass.equals(parameterPass);
    }

    public boolean userExists(User u) {
        return u != null;
    }

    public boolean loginEntered(String login) {
        return login != null;
    }

    public User getUserInDB(String login) {
        return udao.getUserByLogin(login);
    }

    public void online(ModelAndView model) {
        int Online = oh.size();
        String pOnline = "No Players online";
        if (Online > 0) {
            pOnline = "Players online: " + Online;
        }
        model.addObject("pOnline", pOnline);
    }

}
