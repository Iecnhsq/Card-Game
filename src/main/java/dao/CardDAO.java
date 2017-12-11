package dao;

import entity.Card;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class CardDAO {

    public Card getCardById(int id, String cardClassName) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        Card out = (Card) s.createQuery("FROM " +cardClassName+ " WHERE id=" + id + "").uniqueResult();
        s.getTransaction().commit();
        s.close();
        return out;
    }

    public List<Card> getCards(String cardClassName) {
        List<Card> out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = s.createQuery("From "+cardClassName+"").list();
        s.close();
        return out;
    }

    public List<Card> getByLvl(String cardClassName) {
        List<Card> out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = s.createQuery("From "+cardClassName+" Card Level").list();
        s.close();
        return out;
    }
}
