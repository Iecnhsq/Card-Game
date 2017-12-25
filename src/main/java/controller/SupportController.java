package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.SupportService;

@Controller
public class SupportController {

    @Autowired
    private UserDAO udao;
    @Autowired
    private SupportService supportService;

    @RequestMapping(value = "/support.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView support(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("support");
        String problem = req.getParameter("problem");
        if (problem == null || problem.length() <= 10) {
            return new ModelAndView("support");
        } else {
            String subject = req.getParameter("subject");
            if (subject == null || subject.length() <= 10) {
                return new ModelAndView("support");
            } else {
                String loginsupport = req.getParameter("loginsupport");
                if (loginsupport == null || loginsupport.length() <= 3) {
                    return new ModelAndView("support");
                } else {
                    User u = udao.getUserByLogin(loginsupport);
                    if (u == null) {
                        return new ModelAndView("support");
                    } else {
                        String email = u.getEmail();
                        if (email == null) {
                            return new ModelAndView("support");
                        } else {
                            String message = req.getParameter("message");
                            if (message == null || message.length() <= 10) {
                                return new ModelAndView("support");
                            } else {
                                supportService.sendMail(subject, problem, message, loginsupport, email);
                                try {
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
        supportService.statusServer(model);
        supportService.dateNow(model);
        supportService.online(model);
        return model;
    }

}
