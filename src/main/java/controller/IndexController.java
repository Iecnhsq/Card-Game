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
    private IndexService is;

    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("index");
        is.statusServer(model);
        is.changerMainAbotNews(req, model);
        is.dateNow(model);
        is.online(model);
        return model;
    }

}
