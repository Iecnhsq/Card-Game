package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
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
import service.IndexService;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private IndexService is;

    private static final String CA = captcha();

    @RequestMapping(value = "/forgotpass.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forgotPass(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("forgotpass");
        is.dateNow(model);
        is.online(model);
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
                    String emailSubject = "Your are recovery password in 'Card Game'";
                    StringBuilder sb = new StringBuilder();
                    sb.append("Hi!").append("\n").append("You have just recovery password in 'Card Game'")
                            .append("\n").append("Your code: ").append(CA)
                            .append("\n").append("If you did not do this, contact support immediately!")
                            .append("\n").append("\n")
                            .append("Thank you, good game").append("\n").append("\n")
                            .append("Sincerely, the Card Game team");
                    String emailMessage = sb.toString();
                    mailSender.send((MimeMessage mimeMessage) -> {
                        MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                        mimeMsgHelperObj.setTo(mail);
                        mimeMsgHelperObj.setFrom("cardgamesupp@gmail.com");
                        mimeMsgHelperObj.setText(emailMessage);
                        mimeMsgHelperObj.setSubject(emailSubject);
                    });
                    ModelAndView recovery = new ModelAndView("recovery");
                    req.getSession().setAttribute("mail", mail);
                    req.getSession().setAttribute("pass", u.getPass());
                    return recovery;
                }
            }
        }
    }

    private static String captcha() {
        String s = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

    @RequestMapping(value = "/recovery.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView recovery(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("recovery");
        is.dateNow(model);
        is.online(model);
        String ans = req.getParameter("answer");
        if (ans == null) {
            return new ModelAndView("recovery");
        } else {
            if (ans.length() == 10 && ans.equals(CA)) {
                String mail = (String) req.getSession().getAttribute("mail");
                String pass = (String) req.getSession().getAttribute("pass");
                String emailSubject = "Your are recovery password in 'Card Game'";
                StringBuilder sb = new StringBuilder();
                sb.append("Hi!").append("\n").append("You have just recovery password in 'Card Game'")
                        .append("\n").append("Your Password: ").append(pass)
                        .append("\n").append("\n")
                        .append("Thank you, good game").append("\n").append("\n")
                        .append("Sincerely, the Card Game team");
                String emailMessage = sb.toString();
                mailSender.send((MimeMessage mimeMessage) -> {
                    MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    mimeMsgHelperObj.setTo(mail);
                    mimeMsgHelperObj.setFrom("cardgamesupp@gmail.com");
                    mimeMsgHelperObj.setText(emailMessage);
                    mimeMsgHelperObj.setSubject(emailSubject);
                });
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

}
