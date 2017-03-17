package group9.com;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class MessageReceiverServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(MessageReceiverServlet.class.getName());

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException {

    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
    Message message = xmpp.parseMessage(req);

    JID fromJid = message.getFromJid();
    String body = message.getBody();

    log.info("Received a message with id: " + fromJid + " and body: " + body);
    // ...
  }
}