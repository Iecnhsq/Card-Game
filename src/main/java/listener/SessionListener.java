package listener;

import dao.UserDAO;
import holders.OnlineHolder;
import holders.UserHolder;
import holders.WaitHolder;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionListener implements HttpSessionListener {

    @Autowired
    private OnlineHolder oh;
    @Autowired
    private WaitHolder wh;
    @Autowired
    private UserHolder uh;
    @Autowired
    private UserDAO udao;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (se.getSession().getAttribute("login") != null) {
            udao.updateUser(uh.getUser());
            oh.remove((String) se.getSession().getAttribute("login"));
            wh.remove((String) se.getSession().getAttribute("login"));

        }
    }

}
