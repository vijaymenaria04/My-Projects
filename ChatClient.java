import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while (true) {
            System.out.print("You: ");
            message = userInput.readLine();
            out.println(message);
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Disconnected from server.");
                break;
            }
            String response = in.readLine();
            System.out.println("Server: " + response);
        }

        socket.close();
    }
}
