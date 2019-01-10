import java.net.*;
import java.io.*;

public class MultiEchoServer2 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new Thread(new Runnable() {
                    private Socket clientSocket = serverSocket.accept();
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
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
