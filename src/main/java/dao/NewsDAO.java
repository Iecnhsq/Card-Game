package dao;

import entity.News;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class NewsDAO {

    public List<News> getNews() {
        List<News> out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = s.createQuery("FROM News").list();
        s.close();
        return out;
    }

    public News getNewsById(int id) {
        News out;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        out = null;
        s.beginTransaction();
        out = (News) s.createQuery("FROM News WHERE id='" + id + "'").uniqueResult();
        s.getTransaction().commit();
        s.close();
        return out;
    }

    public void addNews(News n) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.save(n);
        s.getTransaction().commit();
        s.close();
    }

    public int getMaxId() {
        int maxId = 0;
        try {
            Session s = HibernateUtil.getSESSIONFACTORY().openSession();
            s.beginTransaction();
            Query q = s.createQuery("Select max(id) From News");
            List currentSeq = q.list();
            if (currentSeq.contains(null)) {
                maxId = 1;
                return maxId;
            } else {
                maxId = (int) currentSeq.get(0);
                return maxId + 1;
            }
        } catch (HibernateException ex) {
            System.out.println("Error: " + ex);
        }
        return maxId;
    }

}
