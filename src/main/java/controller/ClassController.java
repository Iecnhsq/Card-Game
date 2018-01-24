package controller;

import entity.User;
import holders.UserHolder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {

    @Autowired
    private UserHolder uh;

    @RequestMapping("/class.html")
    public ModelAndView Class(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("class");
        User u = uh.getUser();
        model.addObject("classs", u.getClasss());
        model.addObject("lvl", u.getLvl());
        model.addObject("pts", u.getPoints());
        model.addObject("mon", u.getMoney());
        model.addObject("rDate", u.getDate());
        return model;
    }

}
