package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
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
import service.IndexService;

@Controller
public class SupportController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserDAO udao;
    @Autowired
    private IndexService is;

    @RequestMapping(value = "/support.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mMCsupport(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("support");
        is.statusServer(model);
        is.dateNow(model);
        is.online(model);
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
                                    String emailSubject = sub;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(err).append("\n").append("\n")
                                            .append(mess).append("\n").append("\n")
                                            .append("Send from User: ")
                                            .append(log);
                                    String emailMessage = sb.toString();
                                    mailSender.send((MimeMessage mimeMessage) -> {
                                        MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                                        mimeMsgHelperObj.setTo("cardgamesupp@gmail.com");
                                        mimeMsgHelperObj.setFrom(u.getEmail());
                                        mimeMsgHelperObj.setText(emailMessage);
                                        mimeMsgHelperObj.setSubject(emailSubject);
                                    });
                                    resp.sendRedirect("index.html");
                                } catch (IOException ex) {
                                    System.out.println("Error: " + ex);
                                }
                            }
                        }
                    }
                }
            }
        }
        return model;
    }

}
