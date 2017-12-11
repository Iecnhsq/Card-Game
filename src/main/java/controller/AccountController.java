package controller;

import dao.UserDAO;
import entity.User;
import holders.UserHolder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserHolder uh;

    @RequestMapping(value = "/account.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView account(HttpServletRequest req, HttpServletResponse resp) {
        String login = (String) req.getSession().getAttribute("login");
        //если пользователь не авторизован, выбрасываем на мейн
        if (login == null) {
            try {
                resp.sendRedirect("/CardGame/main.html");
            } catch (IOException ex) {
            }
            return null;
        } else {
            User user = uh.getUser();
            if (user.getClasss().equals("hunter")) {
                req.getSession().setAttribute("hu", true);
            } else {
                req.getSession().setAttribute("hu", false);
            }
            if (user.getClasss().equals("mage")) {
                req.getSession().setAttribute("ma", true);
            } else {
                req.getSession().setAttribute("ma", false);
            }
            if (user.getClasss().equals("priest")) {
                req.getSession().setAttribute("pr", true);
            } else {
                req.getSession().setAttribute("pr", false);
            }
            if (user.getClasss().equals("rogue")) {
                req.getSession().setAttribute("ro", true);
            } else {
                req.getSession().setAttribute("ro", false);
            }
            if (user.getClasss().equals("shaman")) {
                req.getSession().setAttribute("sh", true);
            } else {
                req.getSession().setAttribute("sh", false);
            }
            if (user.getClasss().equals("warrior")) {
                req.getSession().setAttribute("wr", true);
            } else {
                req.getSession().setAttribute("wr", false);
            }

            String mailNow = user.getEmail();
            System.out.println(mailNow + "++++++++++++++++");

            String phone = req.getParameter("phone");
            // если не указан телефон, "перезагружаем" страницу
            if (phone == null) {
                ModelAndView account = new ModelAndView("account");
                account.addObject("isLogin", true);
                account.addObject("login", login);
                account.addObject("u", user);
                return account;
            } else {
                String email = req.getParameter("email");
                String city = req.getParameter("city");
                String pass = req.getParameter("pass");//новый пароль
                String pass2 = req.getParameter("pass2");//подтвержденный новый пароль
                //если пароли не сходятся перезагружаем страницу
                if (!pass.equals(pass2)) {
                    ModelAndView account = new ModelAndView("account");
                    account.addObject("isLogin", true);
                    account.addObject("login", login);
                    account.addObject("u", user);
                    return account;
                } else {
                    try {
                        // добавляем данные если они заданы
                        if (city.length() > 0) {
                            user.setCity(city);
                        }
                        if (email.length() > 0) {
                            user.setEmail(email);
                        }
                        if (phone.length() > 0) {
                            user.setPhone(phone);
                        }
                        String pass3 = req.getParameter("pass3");//старый пароль
                        // проверяем равен ли пароль действующему паролю
                        if (pass3.equals(user.getPass())) {
                            //изменяем пароль если он задан
                            if (pass.length() > 0) {
                                user.setPass(pass);
                            }
                            //отсылаем изменения на мыло
//                            String emailSubject = "Your are register in 'Card Game'";
//                            StringBuilder sb = new StringBuilder();
//                            sb.append("Hi!").append("\n").append("You just changed your details in 'Card Game'")
//                                    .append("\n").append("\n")
//                                    .append("Your new Login: ").append(user.getLogin()).append("\n")
//                                    .append("Your new Password: ").append(user.getPass()).append("\n")
//                                    .append("\n")
//                                    .append("If you did not, immediately contact support!").append("\n")
//                                    .append("\n")
//                                    .append("Sincerely, the Card Game team");
//                            String emailMessage = sb.toString();
//                            mailSender.send((MimeMessage mimeMessage) -> {
//                                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//                                mimeMsgHelperObj.setTo(mailNow);
//                                mimeMsgHelperObj.setFrom("cardgamesupp@gmail.com");
//                                mimeMsgHelperObj.setText(emailMessage);
//                                mimeMsgHelperObj.setSubject(emailSubject);
//                            });
//                            System.out.println("\nMessage Send Successfully.... Hurrey!\n");
//System.out.println(mailNow + "++++++++++++++++");
                            //
                            // меняем юзера в дб
                            uh.set(user);
                            udao.updateUser(user);
                            // переходим обратно в мейн
                            resp.sendRedirect("/CardGame/main.html");
                        } else {
                            ModelAndView account = new ModelAndView("account");
                            account.addObject("isLogin", true);
                            account.addObject("login", login);
                            account.addObject("u", user);
                            return account;
                        }
                    } catch (IOException ex) {
                    }
                    return null;
                }
            }
        }
    }
}
