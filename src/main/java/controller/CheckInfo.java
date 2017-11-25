package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
            if (pass == null ? pass2 != null : !pass.equals(pass2)) {
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
            if (isValid(input) == true) {
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

    public static boolean isValid(final char[] input) {
        if (input == null) {
            return false;
        }

        int state = 0;
        char ch;
        int index = 0;
        int mark = 0;
        String local = null;
        ArrayList<String> domain = new ArrayList<>();

        while (index <= input.length && state != -1) {

            if (index == input.length) {
                ch = '\0'; // Так мы обозначаем конец нашей работы
            } else {
                ch = input[index];
                if (ch == '\0') {
                    // символ, которым мы кодируем конец работы, не может быть частью ввода
                    return false;
                }
            }

            switch (state) {

                case 0: {
                    // Первый символ {atext} -- текстовой части локального имени
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        state = 1;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 1: {
                    // Остальные символы {atext} -- текстовой части локального имени
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        break;
                    }
                    if (ch == '.') {
                        state = 2;
                        break;
                    }
                    if (ch == '@') { // Конец локальной части
                        local = new String(input, 0, index - mark);
                        mark = index + 1;
                        state = 3;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 2: {
                    // Переход к {atext} (текстовой части) после точки
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        state = 1;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 3: {
                    // Переходим {alnum} (домену), проверяем первый символ
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        state = 4;
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 4: {
                    // Собираем {alnum} --- домен
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        break;
                    }
                    if (ch == '-') {
                        state = 5;
                        break;
                    }
                    if (ch == '.') {
                        domain.add(new String(input, mark, index - mark));
                        mark = index + 1;
                        state = 5;
                        break;
                    }
                    // Проверка на конец строки
                    if (ch == '\0') {
                        domain.add(new String(input, mark, index - mark));
                        state = 6;
                        break; // Дошли до конца строки -> заканчиваем работу
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 5: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        state = 4;
                        break;
                    }
                    if (ch == '-') {
                        break;
                    }
                    // Если встретили неправильный символ -> отмечаемся в state об ошибке
                    state = -1;
                    break;
                }

                case 6: {
                    // Успех! (На самом деле, мы сюда никогда не попадём)
                    break;
                }
            }
            index++;
        }

        // Остальные проверки
        // Не прошли проверку выше? Возвращаем false!
        if (state != 6) {
            return false;
        }

        // Нам нужен домен как минимум второго уровня
        if (domain.size() < 2) {
            return false;
        }

        // Ограничения длины по спецификации RFC 5321
        if (local.length() > 64) {
            return false;
        }

        // Ограничения длины по спецификации RFC 5321
        if (input.length > 254) {
            return false;
        }

        // Домен верхнего уровня должен состоять только из букв и быть не короче двух символов
        index = input.length - 1;
        while (index > 0) {
            ch = input[index];
            if (ch == '.' && input.length - index > 2) {
                return true;
            }
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                return false;
            }
            index--;
        }
        return true;
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
