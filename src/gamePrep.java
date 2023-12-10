import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class gamePrep {

    private frogger frogger;
    private car[][] cars;
    private log[][] logs;

    private boolean gameStarted = false;

    private void carLogInit() {
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars[i].length; j++) {
                cars[i][j] = new car(40 + j * 150, 255 + i * 60, 150, 150, "carLeft.png", (i % 2 == 0) ? 0 : 1);
            }
        }
        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[i].length; j++) {
                logs[i][j] = new log(40 + j * 150, 60 + i * 60, 150, 150, "log.png", (i % 2 == 0) ? 0 : 1);
            }
        }
    }

    private void winCondition() {
        gameStarted = false;
        System.out.println("you made it! press Restart to play again");
    }

    private void resetObjects() {
        updateFroggerPosition(275, 400);
        stopCarsAndLogs();
        resetCarsAndLogs();
    }

    private void stopCarsAndLogs() {
        for (car[] carRow : cars) {
            for (car car : carRow) {
                car.setMoving(false);
                car.stopThread();
            }
        }
        for (log[] logRow : logs) {
            for (log log : logRow) {
                log.setMoving(false);
                log.stopThread();
            }
        }
    }

    private void resetCarsAndLogs() {
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars[i].length; j++) {
                int initialX = 40 + j * 150;
                int initialY = 255 + i * 60;
            }
        }
        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[i].length; j++) {
                int initialX = 40 + j * 150;
                int initialY = 60 + i * 60;
            }
        }
    }

    private void startCarsAndLogs() {
        for (car[] carRow : cars) {
            for (car car : carRow) {
                car.setMoving(true);
                car.startThread();
            }
        }
        for (log[] logRow : logs) {
            for (log log : logRow) {
                log.setMoving(true);
                log.startThread();
            }
        }
    }

    private void updateFroggerPosition(int x, int y) {
        frogger.setX(x);
        frogger.setY(y);
    }

    public static void main(String[] args)  throws IOException {
        frogger frogger;
        car[][] cars;
        log[][] logs;

        frogger = new frogger(100, 200, 96, 96, "frogger.png");
        cars = new car[3][4];
        logs = new log[3][4];

        frogger.setX(275);
        frogger.setY(400);
        frogger.setWidth(161);
        frogger.setHeight(200);
        frogger.setImage("frogger.png");

        // new thread
        // set up server to receive commands from client
        // pass command to serverService
        // pass GET FROG\Ns
        
        final int SERVER_PORT = 5556;
		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Waiting for clients to connect...");
		while(true) {
			Socket s = server.accept();
			System.out.println("client connected");
			
			BService myService = new BService (s);
			Thread t = new Thread(myService);
			t.start();
		}
    }
}
