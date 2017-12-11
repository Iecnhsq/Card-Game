package controller;

import dao.NewsDAO;
import dao.UserDAO;
import entity.News;
import entity.User;
import holders.UserHolder;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {


    @Autowired
    private UserDAO udao;
    @Autowired
    private NewsDAO ndao;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserHolder uh;

    private int id;
    private static final String CA = captcha();

    @RequestMapping(value = "/admin.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminPanel(HttpServletRequest req, HttpServletResponse resp) {
        boolean isLogAdm = false;
        ModelAndView model = new ModelAndView("admin");
        String passLog = "Login: admin; Password: admin.";
        model.addObject("passlog", passLog);
        model.addObject("CA", CA);
        String login = req.getParameter("login");
        if (login != null && login.length() > 0) {
            User u = udao.getUserByLogin(login);
            if (u != null) {
                String pass = req.getParameter("pass");
                if (u.getPass().equals(pass)) {
                    String ca = req.getParameter("ca");
                    String re = req.getParameter("re");
                    if (ca.equals(re)) {
                        uh.set(u);
                        isLogAdm = true;
                        String emailSubject = "You have just logged in as Administrator!";
                        StringBuilder sb = new StringBuilder();
                        sb.append("Hi!").append("\n").append("You have just logged in as Administrator").append("\n")
                                .append("Date: ").append(new Date()).append("\n")
                                .append("Your Login: ").append(u.getLogin()).append("\n").append("\n")
                                .append("Sincerely, the Card Game team");
                        String emailMessage = sb.toString();
                        mailSender.send((MimeMessage mimeMessage) -> {
                            MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                            mimeMsgHelperObj.setTo("cardgamesupp@gmail.com");
                            mimeMsgHelperObj.setFrom("cardgamesupp@gmail.com");
                            mimeMsgHelperObj.setText(emailMessage);
                            mimeMsgHelperObj.setSubject(emailSubject);
                        });
                    } else {
                        return new ModelAndView("admin");
                    }
                } else {
                    return new ModelAndView("admin");
                }
            } else {
                return new ModelAndView("admin");
            }
        }
        model.addObject("isLogAdm", isLogAdm);
        model.addObject("login", login);
        return model;
    }

    private static String captcha() {
        String s = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

    @RequestMapping(value = "/newspost.html", method = {RequestMethod.GET, RequestMethod.POST})
    public void addNews(HttpServletRequest req, HttpServletResponse resp) {
        id = ndao.getMaxId();
        String subject = req.getParameter("subject");
        if (subject != null && subject.length() > 0) {
            String author = req.getParameter("author");
            if (author != null && author.length() > 0) {
                String text = req.getParameter("text");
                if (text != null) {
                    if (text.length() > 0 && text.length() < 250) {
                        try {
                            News n = new News(id, subject, author, new Date(), text);
                            ndao.addNews(n);                
                            resp.sendRedirect("/CardGame/admin.html");
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        }
    }

}
