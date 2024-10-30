import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    final int CONNECTIONS = 64;
    private final static Map<String, Map<String, Handler>> handlers = new ConcurrentHashMap<>();

    public void listen(int port) {
        try (final var serverSocket = new ServerSocket(port);
             var executorService = Executors.newFixedThreadPool(CONNECTIONS)) {
            while (true) {
                final var socket = serverSocket.accept();
                System.out.println(socket);
                var clientHandler = new ClientHandler(socket);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());        }
    }

    public void addHandler(String method, String path, Handler handler) {
        if (handlers.containsKey(method)) {
            handlers.get(method).put(path, handler);
        } else {
            handlers.put(method, new ConcurrentHashMap<>(Map.of(path, handler)));
        }
        System.out.println(handlers);
    }

    public static Map<String, Map<String, Handler>> getHandlers() {
        return handlers;
    }
}
