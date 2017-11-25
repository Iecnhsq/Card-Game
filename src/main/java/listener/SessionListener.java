package listener;

import holders.OnlineHolder;
import holders.WaitHolder;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionListener implements HttpSessionListener {

    @Autowired
    private OnlineHolder oh;
    @Autowired
    private WaitHolder wh;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (se.getSession().getAttribute("login") != null) {
            oh.remove((String) se.getSession().getAttribute("login"));
            wh.remove((String) se.getSession().getAttribute("login"));

        }
    }

}
