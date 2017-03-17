package group9.com;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.com.google.common.io.ByteStreams;


// [START example]
@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(ErrorServlet.class.getName());

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException {
    // Parse the POST data, which is sent as a MIME stream containing the stanza.
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ServletInputStream inputStream = req.getInputStream();
    ByteStreams.copy(inputStream, baos);

    // Log the error
    log.warning("Error stanza received: " + baos.toString());
  }
}