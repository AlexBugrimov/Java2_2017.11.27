import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket srvSocket = new ServerSocket(30333);

        while (true) {
            Socket socket = srvSocket.accept();
            System.out.println("New connection from " + socket.getInetAddress().getCanonicalHostName());

            Thread thread = new ServerThread(socket);
            thread.start();
        }
    }
}

class ServerThread extends Thread {

    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (true) {

            BufferedReader reader;
            String str;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                str = reader.readLine();
            } catch (IOException e) {
                System.out.println("Cannot read");
                continue;
            }

            if ("exit".equals(str)) {
                System.out.println("Server exit");
                break;
            }
            System.out.println("Get message: " + str);

            BufferedWriter writer;
            try {
                writer = new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
                writer.write( "ACCEPT\n");
                writer.flush();
            } catch (IOException e) {
                System.out.println("Cannot write to socket");
                break;
            }
        }
        System.out.println(getName() + " client thread quits.");
    }

}
