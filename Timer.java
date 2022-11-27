import java.awt.Font;
import javax.swing.*;

public class Timer extends Thread {
    JFrame root;
    JLabel timeLabel;
    JTextField entry;
    int time;

    Timer() {
        root = new JFrame();
        root.setSize(500, 500);
        root.setVisible(true);
        root.setLayout(null);

        timeLabel = new JLabel("0");
        timeLabel.setFont(new Font("Arial", 1, 24));
        timeLabel.setBounds(200, 50, 50, 50);
        root.add(timeLabel);
    }

    public void run() {
        try {
            while (true) {
                timeLabel.setText("" + time);
                if (time > 0) {
                    time -= 1;
                    sleep(1000);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new Timer();
    }
}
