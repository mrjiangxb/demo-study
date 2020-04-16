package NettyStudy.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(); // 实际中要做try catch处理
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888));
        while (true) {
            Socket socket = serverSocket.accept(); // 阻塞方法

            new Thread(() -> {
                handle(socket);
            }).start();
        }
    }

    static void handle (Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            int length = socket.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0, length));

            socket.getOutputStream().write(bytes, 0, length);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
