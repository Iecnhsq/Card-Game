package service;

import dao.NewsDAO;
import entity.News;
import holders.OnlineHolder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class IndexService {

    @Autowired
    private NewsDAO ndao;
    @Autowired
    private OnlineHolder oh;

    public void online(ModelAndView model) {
        int Online = oh.size();
        String pOnline = "No Players online";
        if (Online > 0) {
            pOnline = "Players online: " + Online;
        }
        model.addObject("pOnline", pOnline);
    }

    public void getNews(ModelAndView model) {
        List<News> allnews = ndao.getNews();
        model.addObject("allnews", allnews);
    }

}
