package dao;

import entity.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class UserDAO {

    List<User> getUser() {
        List<User> out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = s.createQuery("FROM User").list();
        s.close();
        return out;
    }

    public User getUserByLogin(String login) {
        User out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = null;
        s.beginTransaction();
        out = (User) s.createQuery("FROM User WHERE login='" + login + "'").uniqueResult();
        s.getTransaction().commit();
        s.close();
        return out;
    }

    public void addUser(User u) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }

    public void updateUser(User u) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.update(u);
        s.getTransaction().commit();
        s.close();
    }

    public boolean isExists(String login) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        User u = (User) s.createQuery("FROM User WHERE login='" + login + "'").uniqueResult();
        return u != null;
    }

}
