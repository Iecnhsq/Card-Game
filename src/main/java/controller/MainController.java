package controller;

import dao.NewsDAO;
import dao.UserDAO;
import entity.News;
import entity.User;
import holders.OnlineHolder;
import holders.UserHolder;
import holders.WaitHolder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private NewsDAO ndao;
    @Autowired
    private UserHolder uh;

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView model = new ModelAndView("main");
        Date dNow = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd 'at' H:mm:ss");
        String outDate = sdt.format(dNow);
        String page = req.getParameter("page");
        boolean isMain = false;
        boolean isAbout = false;
        boolean isNews = false;
        if (page == null) {
            isMain = true;
        } else {
            if (page.equals("about")) {
                isAbout = true;
            }
            if (page.equals("news")) {
                isNews = true;
                List<News> allNews = ndao.getNews();
                model.addObject("allnews", allNews);
            }
        }

        String login = (String) req.getSession().getAttribute("login");
        boolean isLogin = false;
        boolean isAdmP = false;
        if (login != null) {
            User u = uh.getUser();
            String exit = req.getParameter("exit");
            // если был передан параметр "выход"
            if (exit != null) {
                //разлогинивание юзера
                udao.updateUser(u);
                req.getSession().removeAttribute("login");
                //удаление юзера из списка пользователей онлайн
                oh.remove(login);
                wh.remove(login);
                resp.sendRedirect("/CardGame/main.html");
            } else {
                //если не был передан параметр выхода меняем маркер логина
                isLogin = true;
                
                mServ.getUserCards(model, u.getClasss());
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
        model.addObject("isM", isMain);
        model.addObject("isA", isAbout);
        model.addObject("isN", isNews);
        model.addObject("outDate", outDate);
        return model;
    }

}
