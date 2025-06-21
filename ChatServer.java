import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while (true) {
            message = in.readLine();
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Client disconnected.");
                break;
            }
            System.out.println("Client: " + message);
            System.out.print("You: ");
            String response = userInput.readLine();
            out.println(response);
        }

        socket.close();
        serverSocket.close();
    }
}
