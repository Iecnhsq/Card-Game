package service;

import holders.OnlineHolder;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.ModelAndView;

public class RecoveryForgotPassService {

    @Autowired
    private OnlineHolder oh;
    @Autowired
    private JavaMailSender mailSender;

    public void online(ModelAndView model) {
        int Online = oh.size();
        String pOnline = "No Players online";
        if (Online > 0) {
            pOnline = "Players online: " + Online;
        }
        model.addObject("pOnline", pOnline);
    }

    public void sendMail(String mail, String CA) {
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
    }

    public void sendAnswer(String pass, String mail) {
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
    }

}
