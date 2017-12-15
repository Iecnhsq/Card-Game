package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
class PremiumController {
    
    @RequestMapping(value = "/premium.html", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getPremium(HttpServletRequest req, HttpServletResponse resp){
        ModelAndView model = new ModelAndView();
        return model;
    }
    
}
