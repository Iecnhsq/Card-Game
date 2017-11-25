package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {

    @RequestMapping(value = "/class.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView Class(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView("class");
        String idClass = req.getParameter("idc");
        if (idClass == null) {
            resp.sendRedirect("/CardGame/main.html");
        } else {
            if (idClass.equals("dk")) {
                model.addObject("DK", true);
                return model;
            }
            if (idClass.equals("dh")) {
                model.addObject("DH", true);
                return model;
            }
            if (idClass.equals("dr")) {
                model.addObject("DR", true);
                return model;
            }
            if (idClass.equals("hu")) {
                model.addObject("HU", true);
                return model;
            }
            if (idClass.equals("ma")) {
                model.addObject("MA", true);
                return model;
            }
            if (idClass.equals("mo")) {
                model.addObject("MO", true);
                return model;
            }
            if (idClass.equals("pa")) {
                model.addObject("PA", true);
                return model;
            }
            if (idClass.equals("pr")) {
                model.addObject("PR", true);
                return model;
            }
            if (idClass.equals("ro")) {
                model.addObject("RO", true);
                return model;
            }
            if (idClass.equals("sh")) {
                model.addObject("SH", true);
                return model;
            }
            if (idClass.equals("wl")) {
                model.addObject("WL", true);
                return model;
            }
            if (idClass.equals("wr")) {
                model.addObject("WR", true);
                return model;
            }
        }
        return model;
    }

}
