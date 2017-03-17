package group9.com;

import com.google.appengine.api.xmpp.Subscription;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class SubscriptionServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(SubscriptionServlet.class.getName());

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
    XMPPService xmppService = XMPPServiceFactory.getXMPPService();
    Subscription sub = xmppService.parseSubscription(req);

    // Split the bare XMPP address (e.g., user@gmail.com)
    // from the resource (e.g., gmail.CD6EBC4A)
    String from = sub.getFromJid().getId().split("/")[0];

    log.info("Received subscription event from: " + from);
  }
}