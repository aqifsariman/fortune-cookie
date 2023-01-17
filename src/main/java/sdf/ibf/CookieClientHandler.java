package sdf.ibf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClientHandler implements Runnable {
  private Socket socket;

  public CookieClientHandler() {
  }

  public CookieClientHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    Cookie cookie = new Cookie();
    try {
      cookie.readCookieFile();
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try (InputStream is = socket.getInputStream()) {
      BufferedInputStream bis = new BufferedInputStream(is);
      DataInputStream dis = new DataInputStream(bis);
      String messageReceived = "";
      OutputStream os = socket.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);

      while (!messageReceived.equalsIgnoreCase("close")) {
        messageReceived = dis.readUTF();

        if (messageReceived.equalsIgnoreCase("get-cookie")) {
          String cookieValue = cookie.returnCookie();
          dos.writeUTF(cookieValue);
          dos.flush();
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
