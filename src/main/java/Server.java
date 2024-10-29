import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    final int CONNECTIONS = 64;

    public void listen(int port) {
        try (final var serverSocket = new ServerSocket(port);
             var executorService = Executors.newFixedThreadPool(CONNECTIONS)) {
            while (true) {
                final var socket = serverSocket.accept();
                System.out.println(socket);
                var handle = new Handle(socket);
                executorService.execute(handle);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
