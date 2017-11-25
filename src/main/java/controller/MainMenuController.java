package controller;

import dao.NewsDAO;
import dao.UserDAO;
import entity.News;
import entity.User;
import java.io.IOException;
import java.util.List;
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
public class MainMenuController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserDAO udao;
    @Autowired
    private NewsDAO ndao;

    @RequestMapping("/about.html")
    public ModelAndView mMCAbout(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("about");
        return model;
    }

    @RequestMapping("/news.html")
    public ModelAndView mMCNews(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("news");
        List<News> allNews = ndao.getNews();
        model.addObject("allnews", allNews);
        return model;
    }

    @RequestMapping(value = "/support.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mMCsupport(HttpServletRequest req, HttpServletResponse resp) {
        String err = req.getParameter("err");
        if (err == null) {
            return new ModelAndView("support");
        } else {
            String sub = req.getParameter("sub");
            if (sub.length() == 0) {
                return new ModelAndView("support");
            } else {
                String log = req.getParameter("log");
                if (log == null || log.length() == 0) {
                    return new ModelAndView("support");
                } else {
                    User u = udao.getUserByLogin(log);
                    if (u == null) {
                        return new ModelAndView("support");
                    } else {
                        if (u.getEmail() == null) {
                            return new ModelAndView("support");
                        } else {
                            String mess = req.getParameter("mess");
                            if (mess.length() == 0) {
                                return new ModelAndView("support");
                            } else {
                                try {
                                    //emailSender
                                    String emailSubject = sub;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(err).append("\n").append("\n")
                                            .append(mess).append("\n").append("\n")
                                            .append("Send from User: ")
                                            .append(u.getLogin());
                                    String emailMessage = sb.toString();
                                    mailSender.send((MimeMessage mimeMessage) -> {
                                        MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                                        mimeMsgHelperObj.setTo("cardgamesupp@gmail.com");
                                        mimeMsgHelperObj.setFrom(u.getEmail());
                                        mimeMsgHelperObj.setText(emailMessage);
                                        mimeMsgHelperObj.setSubject(emailSubject);
                                    });
                                    //
                                    resp.sendRedirect("/CardGame/main.html");
                                } catch (IOException ex) {
                                }
                            }
                        }
                    }
                }
            }
        }
        ModelAndView model = new ModelAndView("support");
        return model;
    }

}