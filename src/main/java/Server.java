import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 8080; // Порт, на котором будет слушать сервер
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен на порту " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Новое соединение принято от клиента с портом " + clientSocket.getPort());

                try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        final String name = in.readLine();
                        out.println(String.format("Здравствуйте, %s! Ваш порт: %d", name, clientSocket.getPort()));
                    }
                }
            }
        }
    }
}

