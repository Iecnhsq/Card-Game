package controller;

import dao.UserDAO;
import entity.User;
import holders.UserHolder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private UserHolder uh;
    @Autowired
    private AccountService accservice;

    @RequestMapping(value = "/account.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView account(HttpServletRequest req, HttpServletResponse resp) {
        String login = (String) req.getSession().getAttribute("login");
        if (login == null) {
            try {
                resp.sendRedirect("main.html");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
            return null;
        } else {
            User user = uh.getUser();
            String phone = req.getParameter("phone");
            if (phone == null) {
                ModelAndView account = new ModelAndView("account");
                account.addObject("isLogin", true);
                account.addObject("login", login);
                account.addObject("u", user);
                return account;
            } else {
                String email = req.getParameter("email");
                String city = req.getParameter("city");
                String pass = req.getParameter("pass");
                String pass2 = req.getParameter("pass2");
                if (!pass.equals(pass2)) {
                    ModelAndView account = new ModelAndView("account");
                    account.addObject("isLogin", true);
                    account.addObject("login", login);
                    account.addObject("u", user);
                    return account;
                } else {
                    try {
                        if (city.length() > 0) {
                            user.setCity(city);
                        }
                        if (email.length() > 0) {
                            user.setEmail(email);
                        }
                        if (phone.length() > 0) {
                            user.setPhone(phone);
                        }
                        String pass3 = req.getParameter("pass3");
                        if (pass3.equals(user.getPass())) {
                            if (pass.length() > 0) {
                                user.setPass(pass);
                            }
                            uh.set(user);
                            udao.updateUser(user);
                            resp.sendRedirect("main.html");
                        } else {
                            ModelAndView account = new ModelAndView("account");
                            account.addObject("isLogin", true);
                            account.addObject("login", login);
                            account.addObject("u", user);
                            return account;
                        }
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex);
                    }
                    return null;
                }
            }
        }
    }
    
}
