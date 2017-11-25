package controller;

import dao.UserDAO;
import entity.User;
import holders.OnlineHolder;
import holders.WaitHolder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView("main");
        String login = (String) req.getSession().getAttribute("login");
        // проверка на авторизацию пользователя
        boolean isLogin = false;
        boolean isAdmP = false;
        if (login != null) {
            String exit = req.getParameter("exit");
            // если был передан параметр "выход"
            if (exit != null) {
                //разлогинивание юзера
                req.getSession().removeAttribute("login");
                //удаление юзера из списка пользователей онлайн
                oh.remove(login);
                wh.remove(login);
                resp.sendRedirect("/CardGame/main.html");

            } else {
                //если не был передан параметр выхода меняем маркер логина
                isLogin = true;
                User u = udao.getUserByLogin(login);
                mServ.getUserCards(login, model);
                if (login.equals(AD)) {
                    isAdmP = true;
                }
                // добавляем в модель все нужные данные
                model.addObject("classs", u.getClasss());
                model.addObject("lvl", u.getLvl());
                model.addObject("pts", u.getPoints());
                model.addObject("mon", u.getMoney());
                model.addObject("rDate", u.getDate());
            }
        }
        //делаем список юзеров онлайн
        int Online = oh.size();
        String pOnline = "No Players online";
        if (Online > 0) {
            pOnline = "Players online: " + Online;
        }
        model.addObject("pOnline", pOnline);
        model.addObject("login", login);
        model.addObject("isLogin", isLogin);
        model.addObject("isAdmP", isAdmP);
        return model;
    }

}
