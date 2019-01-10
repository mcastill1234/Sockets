import java.net.*;
import java.io.*;

public class ServerRunnable implements Runnable {

    private Socket clientSocket = null;

    public ServerRunnable(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying for a connection");
            System.out.println(e.getMessage());
        }
    }
}
