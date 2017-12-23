package controller;

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
    private OnlineHolder oh;
    @Autowired
    private UserHolder uh;
    @Autowired
    private LoginService lserv;

    @RequestMapping(value = "/login.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        if (!lserv.loginEntered(login)) {
            return new ModelAndView("index");
        } else {
            String pass = req.getParameter("pass");
            User u = lserv.getUserInDB(login);
            if (!lserv.userExists(u)) {
                return new ModelAndView("index");
            } else {
                if (!lserv.matchPassword(u.getPass(), pass)) {
                    return new ModelAndView("index");
                } else {
                    req.getSession().setAttribute("login", login);
                    oh.put(u.getLogin(), u);
                    uh.set(u);
                    lserv.getAllCardsInDB();
                    try {
                        resp.sendRedirect("main.html");
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex);
                    }
                }
            }
        }
        return new ModelAndView("index");
    }

}
