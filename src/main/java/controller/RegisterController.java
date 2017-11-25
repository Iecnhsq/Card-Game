package controller;

import dao.UserDAO;
import entity.User;
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
public class RegisterController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest req, HttpServletResponse resp) {
        String loginInSession = (String) req.getSession().getAttribute("login");
        if (loginInSession != null) {
            try {
                resp.sendRedirect("/CardGame/main.html");
            } catch (IOException ex) {
            }
        } else {
            String login = req.getParameter("login");
            if (login == null || login.length() == 0) {
                return new ModelAndView("register");
            } else {
                String pass = req.getParameter("pass");
                String pass2 = req.getParameter("pass2");
                if (!pass.equals(pass2) || pass.length() == 0) {
                    return new ModelAndView("register");
                } else {
                    if (udao.getUserByLogin(login) != null) {
                        return new ModelAndView("register");
                    } else {
                        try {
                            User u = new User(new Random().nextInt(), login, pass, new Date(), 0, 0, "", "mage", 0, req.getParameter("city"), req.getParameter("phone"), req.getParameter("email"));
                            udao.addUser(u);
                            String emailSubject = "Your are register in 'Card Game'";
                            StringBuilder sb = new StringBuilder();
                            sb.append("Hi!").append("\n").append("You have just registered in 'Card Game'").append("\n")
                                    .append("Your Login: ").append(u.getLogin()).append("\n")
                                    .append("Your Password: ").append(u.getPass()).append("\n")
                                    .append("Thank you, good game").append("\n").append("\n")
                                    .append("Sincerely, the Card Game team");
                            String emailMessage = sb.toString();
                            mailSender.send((MimeMessage mimeMessage) -> {
                                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                                mimeMsgHelperObj.setTo(u.getEmail());
                                mimeMsgHelperObj.setFrom("cardgamesupp@gmail.com");
                                mimeMsgHelperObj.setText(emailMessage);
                                mimeMsgHelperObj.setSubject(emailSubject);
                            });
                            resp.sendRedirect("/CardGame/main.html");
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        }
        return null;
    }
}
