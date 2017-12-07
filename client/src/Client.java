import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 30333);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String str = scanner.nextLine();
            str += "\n";
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()
                    )
            );

            writer.write(str);
            writer.flush();

            if ("exit\n".equals(str)) {
                break;
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            str = reader.readLine();
            System.out.println("Got " + str + " from server");
        }
    }
}
