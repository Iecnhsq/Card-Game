package controller;

import entity.User;
import holders.UserHolder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
class PremiumController {

    @Autowired
    private UserHolder uh;

    @RequestMapping(value = "/premium.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView premium(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("premium");
        User u = uh.getUser();
        model.addObject("classs", u.getClasss());
        model.addObject("lvl", u.getLvl());
        model.addObject("pts", u.getPoints());
        model.addObject("mon", u.getMoney());
        model.addObject("rDate", u.getDate());
        return model;
    }

}
