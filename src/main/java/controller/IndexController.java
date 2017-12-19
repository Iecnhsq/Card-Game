package controller;

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
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private NewsDAO ndao;
    @Autowired
    private OnlineHolder oh;

    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("index");
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
        Date dNow = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd 'at' H:mm:ss");
        String outDate = sdt.format(dNow);
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
        int Online = oh.size();
        String pOnline = "No Players online";
        if (Online > 0) {
            pOnline = "Players online: " + Online;
        }
        model.addObject("pOnline", pOnline);
        model.addObject("isM", isMain);
        model.addObject("isA", isAbout);
        model.addObject("isN", isNews);
        model.addObject("outDate", outDate);
        return model;
    }

}
