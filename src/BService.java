import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BService implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public BService(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            handleClient();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClient() throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received command from client: " + inputLine);
            processCommand(inputLine);
        }
    }

    private void processCommand(String command) {
        switch (command.trim()) {
            case "UP":
                // 
                break;
            case "DOWN":
                // 
                break;
            case "LEFT":
                //
                break;
            case "RIGHT":
                // 
                break;
            case "STARTGAME":
                // 
                break;
            case "RESTARTGAME":
                // 
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
    
    private void moveFrogUp() {
        
    }

    private void moveFrogDown() {
        
    }

    private void moveFrogLeft() {
       
    }

    private void moveFrogRight() {
        
    }

    private void startGame() {
       
    }

    private void restartGame() {
        
    }
}
