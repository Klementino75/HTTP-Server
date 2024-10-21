import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    final int CONNECTIONS = 64;
    ExecutorService executorService = Executors.newFixedThreadPool(CONNECTIONS);

    public void listen(int port) {
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var socket = serverSocket.accept();
                System.out.println(socket);
                Handle handle = new Handle(socket);
                executorService.submit(handle);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}