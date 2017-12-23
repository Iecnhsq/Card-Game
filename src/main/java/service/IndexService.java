package service;

import dao.NewsDAO;
import entity.News;
import holders.OnlineHolder;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class IndexService {

    @Autowired
    private NewsDAO ndao;
    @Autowired
    private OnlineHolder oh;

    public void statusServer(ModelAndView model) {
        boolean isAlive = false;
        try {
            URL hp = new URL("http://localhost:8084/CardGame/");
            URLConnection hpCon = hp.openConnection();
            hpCon.getConnectTimeout();
            isAlive = true;
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
        model.addObject("isAlive", isAlive);
    }

    public void dateNow(ModelAndView model) {
        Date dNow = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd 'at' H:mm:ss");
        String outDate = sdt.format(dNow);
        model.addObject("outDate", outDate);
    }

    public void changerMainAbotNews(HttpServletRequest req, ModelAndView model) {
        String page = req.getParameter("page");
        boolean isMain = false;
        boolean isAbout = false;
        boolean isNews = false;
        if (page == null) {
            isMain = true;
        } else {
            if (page.equals("about")) {
                isAbout = true;
            }
            if (page.equals("news")) {
                isNews = true;
                List<News> allNews = ndao.getNews();
                model.addObject("allnews", allNews);
            }
        }
        model.addObject("isM", isMain);
        model.addObject("isA", isAbout);
        model.addObject("isN", isNews);
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
