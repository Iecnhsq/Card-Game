package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RecoveryForgotPassService;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private RecoveryForgotPassService rfpservice;

    private final String CA = captcha();

    @RequestMapping(value = "/forgotpass.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forgotPass(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("forgotpass");
        rfpservice.online(model);
        String login = req.getParameter("login");
        if (login == null) {
            return new ModelAndView("forgotpass");
        } else {
            String mail = req.getParameter("mail");
            User u = udao.getUserByLogin(login);
            if (u == null) {
                return new ModelAndView("forgotpass");
            } else {
                if (!u.getEmail().equals(mail)) {
                    return new ModelAndView("forgotpass");
                } else {
                    rfpservice.sendMail(mail, CA);
                    ModelAndView recovery = new ModelAndView("recovery");
                    req.getSession().setAttribute("mail", mail);
                    req.getSession().setAttribute("pass", u.getPass());
                    return recovery;
                }
            }
        }
    }

    @RequestMapping(value = "/recovery.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView recovery(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("recovery");
        rfpservice.online(model);
        String ans = req.getParameter("answer");
        if (ans == null) {
            return new ModelAndView("recovery");
        } else {
            if (ans.length() == 10 && ans.equals(CA)) {
                String mail = (String) req.getSession().getAttribute("mail");
                String pass = (String) req.getSession().getAttribute("pass");
                rfpservice.sendAnswer(pass, mail);
                try {
                    resp.sendRedirect("index.html");
                } catch (IOException ex) {
                    System.out.println("Error: " + ex);
                }
            } else {
                return new ModelAndView("recovery");
            }
        }
        return model;
    }

    private String captcha() {
        String s = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

}
