package battle;

import entity.Card;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Battle {

    public User p1 = new User();
    public User p2 = new User();

    public List<Card> deckP1 = null;
    public List<Card> deckP2 = null;
    public Integer healthP1 = 10;
    public Integer healthP2 = 10;
    public Integer manaP1 = 1;
    public Integer manaP2 = 1;
    public Integer turn = 1;
    public List<Card> onHandP1 = new CopyOnWriteArrayList<>();
    public List<Card> onHandP2 = new CopyOnWriteArrayList<>();
    public List<Card> onTableP1 = new CopyOnWriteArrayList<>();
    public List<Card> onTableP2 = new CopyOnWriteArrayList<>();
    public Card atackCardP1 = null;
    public Card atackCardP2 = null;
    public boolean p1MadeTurn = false;
    public boolean p2MadeTurn = false;
    public Map<Integer, Card> p1ActiveCards = new HashMap<>();
    public Map<Integer, Card> p2ActiveCards = new HashMap<>();
    public boolean battleOn = false;
    public boolean p1Win = false;
    public boolean p2Win = false;
    public Integer p1points = 0;
    public Integer p2points = 0;
    public Boolean p1CheckInFinish = false;
    public Boolean p2CheckInFinish = false;
}