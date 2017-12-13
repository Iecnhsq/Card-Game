package controller;

import dao.UserDAO;
import entity.User;
import holders.OnlineHolder;
import holders.UserHolder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private OnlineHolder oh;
    @Autowired
    private UserHolder uh;
    @Autowired
    private LoginService lserv;


    @RequestMapping(value = "/login.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        if (login == null) {
            return new ModelAndView("login");
        } else {
            String pass = req.getParameter("pass");
            User u = udao.getUserByLogin(login);
            if (u == null) {
                return new ModelAndView("login");
            } else {
                if (!u.getPass().equals(pass)) {
                    return new ModelAndView("login");
                } else {
                    req.getSession().setAttribute("login", login);
                    oh.put(u.getLogin(), u);
                    uh.set(u);
                    lserv.getAllCardsInDB();
                    try {
                        resp.sendRedirect("/CardGame/main.html");
                    } catch (IOException ex) {
                    }
                }
            }
        }
        ModelAndView model = new ModelAndView("login");
        return model;
    }

}
