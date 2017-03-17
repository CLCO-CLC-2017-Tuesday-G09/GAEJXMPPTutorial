package group9.com;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class MessageSenderServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(MessageSenderServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException {

    JID jid = new JID("example@gmail.com");
    String msgBody = "Someone has sent you a gift on Example.com. To view: http://example.com/gifts/";
    Message msg =
        new MessageBuilder()
            .withRecipientJids(jid)
            .withBody(msgBody)
            .build();

    boolean messageSent = false;
    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
    SendResponse status = xmpp.sendMessage(msg);
    messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);

    log.info("Message sent? " + messageSent);

    if (!messageSent) {
      // Send an email message instead...
    }
  }
}