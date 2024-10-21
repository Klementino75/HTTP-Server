public class Main {
    final static int PORT = 9999;

    public static void main(String[] args) {
        final Server server = new Server();
        server.listen(PORT);
    }
}