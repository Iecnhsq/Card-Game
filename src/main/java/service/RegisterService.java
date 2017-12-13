package service;

import com.google.gson.Gson;
import dao.UserDAO;
import entity.Deck;
import entity.User;
import java.util.Date;
import java.util.Random;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class RegisterService {

    @Autowired
    private UserDAO udao;
    @Autowired
    private JavaMailSender mailSender;

    public boolean registerUserInDb(String login, String pass, String city, String phone, String email) {
        User u = new User(new Random().nextInt(), login, pass, new Date(), 0, 0, new Gson().toJson(new Deck()), "Mage", 0, city, phone, email);
        udao.addUser(u);
        return true;
    }

    public boolean isUserExists(String login) {
        return udao.getUserByLogin(login) != null;
    }

    public boolean isPasswordCorrect(String pass, String pass2) {
        return pass.equals(pass2) && pass.length() != 0;
    }

    public boolean isLoginCorrect(String login) {
        return login != null && login.length() > 0;
    }

    public boolean isLoginInSessionExists(String loginInSession) {
        return loginInSession != null;
    }

    public void sendEmail(String login, String pass, String email) {
        String emailSubject = "Your are register in 'Card Game'";
        StringBuilder sb = new StringBuilder();
        sb.append("Hi!").append("\n").append("You have just registered in 'Card Game'").append("\n")
                .append("Your Login: ").append(login).append("\n")
                .append("Your Password: ").append(pass).append("\n")
                .append("Thank you, good game").append("\n").append("\n")
                .append("Sincerely, the Card Game team");
        String emailMessage = sb.toString();
        mailSender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMsgHelperObj.setTo(email);
            mimeMsgHelperObj.setFrom("cardgamesupp@gmail.com");
            mimeMsgHelperObj.setText(emailMessage);
            mimeMsgHelperObj.setSubject(emailSubject);
        });
    }

}
