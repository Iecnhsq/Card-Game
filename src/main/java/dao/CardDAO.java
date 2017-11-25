package dao;

import entity.Card;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class CardDAO {

    public Card getCardById(int id) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        Card out = (Card) s.createQuery("FROM Card WHERE id=" + id + "").uniqueResult();
        s.getTransaction().commit();
        s.close();
        return out;
    }

    public List<Card> getCards() {
        List<Card> out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = s.createQuery("From Card").list();
        s.close();
        return out;
    }

    public List<Card> getByLvl() {
        List<Card> out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = s.createQuery("From Card Level").list();
        s.close();
        return out;
    }
}
