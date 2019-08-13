package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MailService;

@Controller
public class CheckInfo {

    @Autowired
    private UserDAO udao;

    @RequestMapping("/logcheck.html")
    public void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        if (login != null && login.length() > 0) {
            try (PrintWriter out = resp.getWriter()) {
                if (udao.isExists(login)) {
                    out.print("<div style='display:inline; color: red'>Login does not meet the requirements!</div>");
                } else {
                    out.print("<div style='display:inline; color: green'>Login free!</div>");
                }
            }
        }
    }

    @RequestMapping("/logchecksupp.html")
    public void checkLoginSupp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        try (PrintWriter out = resp.getWriter()) {
            if (login != null && login.length() > 0) {
                if (udao.isExists(login)) {
                } else {
                    out.print("<div style='display:inline; color: red'>Login does not exist!</div>");
                }
            } else {
                out.print("<div style='display:inline; color: red'>Login does not exist!</div>");
            }
        }
    }

    @RequestMapping("/passcheck.html")
    public String passLenth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pass = req.getParameter("pass");
        try (PrintWriter out = resp.getWriter()) {
            if (pass.length() == 0) {
                out.print("<div></div>");
            }
            if (pass.length() >= 1 && pass.length() < 6) {
                out.print("<div style='display:inline; color: red'>LOW PASSWORD!</div>");
            }
            if (pass.length() >= 6 && pass.length() < 10) {
                out.print("<div style='display:inline; color: yellow'>MIDDLE PASSWORD!</div>");
            }
            if (pass.length() >= 10) {
                out.print("<div style='display:inline; color: green'>GOOD PASSWORD!</div>");
            }
        }
        return null;
    }

    @RequestMapping("/equalcheck.html")
    public String passEqual(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pass = req.getParameter("pass");
        String pass2 = req.getParameter("pass2");
        try (PrintWriter out = resp.getWriter()) {
            if (!Objects.equals(pass, pass2)) {
                out.print("<div style='display:inline; color: red'>Password are not equals!</div>");
            } else {
                out.print("<div style='display:inline; color: green'>Password are equals!</div>");
            }
        }
        return null;
    }

    @RequestMapping("/mailcheck.html")
    public void checkMail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mail = req.getParameter("email");
        char[] input = mail.toCharArray();
        try (PrintWriter out = resp.getWriter()) {
            if (MailService.isValid(input) == true) {
                out.print("<div style='display:inline; color: green'>E-mail is true!</div>");
            } else {
                out.print("<div style='display:inline; color: red'>E-mail is not true!</div>");
            }
        }
    }

    @RequestMapping("/equalscheck.html")
    public void checkLoginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        if (login != null && login.length() > 0) {
            try (PrintWriter out = resp.getWriter()) {
                User u = udao.getUserByLogin(login);
                if (u != null) {
                    String pass = req.getParameter("pass");
                    if (u.getPass().equals(pass)) {
                    } else {
                        out.print("<div style='display:inline; color: red'>Login or Password are not equals!</div>");
                    }
                } else {
                    out.print("<div style='display:inline; color: red'>Login or Password are not equals!</div>");
                }
            }
        }
    }

    @RequestMapping("/checkrec.html")
    public void checkRec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        if (login != null && login.length() > 0) {
            try (PrintWriter out = resp.getWriter()) {
                User u = udao.getUserByLogin(login);
                if (u != null) {
                    String mail = req.getParameter("email");
                    if (u.getEmail().equals(mail)) {
                    } else {
                        out.print("<div style='display:inline; color: red'>Login or Password are not equals!</div>");
                    }
                } else {
                    out.print("<div style='display:inline; color: red'>Login or Password are not equals!</div>");
                }
            }
        }
    }
}
