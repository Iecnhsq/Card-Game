package battle;

import entity.Card;
import entity.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import ourlists.OnTableList;

public class Battle {

    public User p1 = new User();
    public User p2 = new User();

    public List<Card> p1Deck = null;
    public List<Card> p2Deck = null;
    public Integer p1Health = 20;
    public Integer p2Health = 20;
    public Integer p1Mana = 1;
    public Integer p2Mana = 1;
    public Integer turn = 1;
    public List<Card> p1OnHand = new LinkedList<>();
    public List<Card> p2OnHand = new LinkedList<>();
    public OnTableList p1OnTable = new OnTableList();
    public OnTableList p2OnTable = new OnTableList();
    public Card p1AttackCard = null;
    public Card p2AttackCard = null;
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
    public Boolean p1HeroPowerSelected = false;
    public Boolean p2HeroPowerSelected = false;
    public Boolean p1Attacked = false;
    public Boolean p2Attacked = false;
    public Card p1ChosenHandCard = null;
    public Card p2ChosenHandCard = null;
}
