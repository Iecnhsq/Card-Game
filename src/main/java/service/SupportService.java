package service;

import holders.OnlineHolder;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.ModelAndView;

public class SupportService {

    @Autowired
    private OnlineHolder oh;
    @Autowired
    private JavaMailSender mailSender;

    public void statusServer(ModelAndView model) {
        boolean isAlive = false;
        try {
            URL hp = new URL("http://localhost:8084/CardGame/");
            URLConnection hpCon = hp.openConnection();
            hpCon.getConnectTimeout();
            isAlive = true;
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
        model.addObject("isAlive", isAlive);
    }

    public void dateNow(ModelAndView model) {
        Date dNow = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd 'at' H:mm:ss");
        String outDate = sdt.format(dNow);
        model.addObject("outDate", outDate);
    }

    public void online(ModelAndView model) {
        int Online = oh.size();
        String pOnline = "No Players online";
        if (Online > 0) {
            pOnline = "Players online: " + Online;
        }
        model.addObject("pOnline", pOnline);
    }

    public void sendMail(String subject, String problem, String message, String loginsupport, String email) {
        String emailSubject = subject;
        StringBuilder sb = new StringBuilder();
        sb.append(problem).append("\n").append("\n")
                .append(message).append("\n").append("\n")
                .append("Send from User: ")
                .append(loginsupport);
        String emailMessage = sb.toString();
        mailSender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMsgHelperObj.setTo("cardgamesupp@gmail.com");
            mimeMsgHelperObj.setFrom(email);
            mimeMsgHelperObj.setText(emailMessage);
            mimeMsgHelperObj.setSubject(emailSubject);
        });
    }

}
