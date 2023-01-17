package sdf.ibf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class App {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        while (true) {
            Socket socket = server.accept();
            System.out.printf("Connection received on port %s.", args[0]);
            CookieClientHandler cCH = new CookieClientHandler(socket);
            executorService.submit(cCH);
        }

    }
}
