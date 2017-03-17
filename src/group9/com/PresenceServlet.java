package group9.com;

import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.PresenceType;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class PresenceServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(PresenceServlet.class.getName());

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException {

    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
    Presence presence = xmpp.parsePresence(req);

    // Split the XMPP address (e.g., user@gmail.com)
    // from the resource (e.g., gmail.CD6EBC4A)
    String from = presence.getFromJid().getId().split("/")[0];

    log.info("Received presence from: " + from);

    // Mirror the contact's presence back to them
    xmpp.sendPresence(
        presence.getFromJid(),
        PresenceType.AVAILABLE,
        presence.getPresenceShow(),
        presence.getStatus());
  }
}