import java.io.IOException;

public class Main {
    final static int PORT = 9999;

    public static void main(String[] args) {
        final var server = new Server();

        for (String validPath : ClientHandler.validPaths) {
            server.addHandler("GET", validPath, (request, responseStream) -> {
                try {
                    ClientHandler.responseOK(request, responseStream);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
        }

        server.addHandler("POST", "/resources.html", (request, responseStream) -> {
            try {
                ClientHandler.responseOK(request, responseStream);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });

        server.listen(PORT);
    }
}