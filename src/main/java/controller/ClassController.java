package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {

    @RequestMapping("/class.html")
    public ModelAndView Class(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("class");
        return model;
    }

}
