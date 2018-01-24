package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IndexService;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("index");
        indexService.online(model);
        return model;
    }

    @RequestMapping("/about.html")
    public ModelAndView about(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("about");
        indexService.online(model);
        return model;
    }

    @RequestMapping("/news.html")
    public ModelAndView news(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("news");
        indexService.online(model);
        indexService.getNews(model);
        return model;
    }

}
