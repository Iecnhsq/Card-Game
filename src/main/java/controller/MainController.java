package controller;

import dao.UserDAO;
import entity.User;
import holders.OnlineHolder;
import holders.UserHolder;
import holders.WaitHolder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IndexService;
import service.MainService;

@Controller
public class MainController {

    private static final String AD = "admin";

    @Autowired
    private UserDAO udao;
    @Autowired
    private MainService mServ;
    @Autowired
    private OnlineHolder oh;
    @Autowired
    private WaitHolder wh;
    @Autowired
    private UserHolder uh;
    @Autowired
    private IndexService is;

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("main");
        is.dateNow(model);
        String login = (String) req.getSession().getAttribute("login");
        boolean isLogin = false;
        boolean isAdmP = false;
        if (login != null) {
            User u = uh.getUser();
            String exit = req.getParameter("exit");
            if (exit != null) {
                udao.updateUser(u);
                req.getSession().removeAttribute("login");
                oh.remove(login);
                wh.remove(login);
                try {
                    resp.sendRedirect("index.html");
                } catch (IOException ex) {
                    System.out.println("Error: " + ex);
                }
            } else {
                isLogin = true;
                mServ.getUserCards(model, u.getClasss());
                if (login.equals(AD)) {
                    isAdmP = true;
                }
                model.addObject("classs", u.getClasss());
                model.addObject("lvl", u.getLvl());
                model.addObject("pts", u.getPoints());
                model.addObject("mon", u.getMoney());
                model.addObject("rDate", u.getDate());
            }
        }
        is.online(model);
        model.addObject("login", login);
        model.addObject("isLogin", isLogin);
        model.addObject("isAdmP", isAdmP);
        return model;
    }

}
