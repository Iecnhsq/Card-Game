package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RegisterService;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService regService;

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest req, HttpServletResponse resp) {
        String loginInSession = (String) req.getSession().getAttribute("login");
        if (regService.isLoginInSessionExists(loginInSession)) {
            try {
                resp.sendRedirect("index.html");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        } else {
            ModelAndView model = new ModelAndView("register");
            regService.statusServer(model);
            regService.dateNow(model);
            regService.online(model);
            String login = req.getParameter("login");
            if (!regService.isLoginCorrect(login)) {
                return new ModelAndView("register");
            } else {
                String pass = req.getParameter("pass");
                String pass2 = req.getParameter("pass2");
                if (!regService.isPasswordCorrect(pass, pass2)) {
                    return new ModelAndView("register");
                } else {
                    if (regService.isUserExists(login)) {
                        return new ModelAndView("register");
                    } else {
                        String email = req.getParameter("email");
                        regService.registerUserInDb(login, pass, req.getParameter("city"), req.getParameter("phone"), email);
                        regService.sendEmail(login, pass, email);
                        try {
                            resp.sendRedirect("main.html");
                        } catch (IOException ex) {
                            System.out.println("Error: " + ex);
                        }
                    }
                }
            }
        }
        return null;
    }
}
