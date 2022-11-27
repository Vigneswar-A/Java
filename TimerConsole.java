
public class TimerConsole extends Thread {
    int count = 60;

    public void run() {
        try {
            while (count >= 0) {
                System.out.print("\rTimer : " + count--);
                sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        TimerConsole timer = new TimerConsole();
        timer.start();
    }
}
