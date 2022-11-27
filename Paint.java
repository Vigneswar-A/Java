import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Paint implements MouseListener, MouseMotionListener {

    JFrame f;
    JButton b1, b2;
    int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

    Paint() {
        f = new JFrame("Demo");
        b1 = new JButton("dddd");
        f.setLayout(new FlowLayout());
        // b1.addActionListener(this);
        f.addMouseListener(this);
        f.addMouseMotionListener(this);
        f.add(b1);
        f.setSize(600, 600);
        // f.setResizable(false);
        f.setVisible(true);

    }

    public void mouseExited(MouseEvent me) {
        // System.out.println("Exit");
    }

    public void mouseEntered(MouseEvent me) {
        // System.out.println("Enter");
    }

    public void mousePressed(MouseEvent me) {

        x1 = me.getX();
        y1 = me.getY();
        System.out.println("While Press :" + x1 + "  " + y1);
        // System.out.println("Press");
    }

    public void mouseReleased(MouseEvent me) {

        Graphics g = f.getGraphics();
        x2 = me.getX();
        y2 = me.getY();
        g.drawLine(x2, y2, x2, y2);
        System.out.println("While Release :" + x2 + "  " + y2);
        // System.out.println("Release");
    }

    public void mouseClicked(MouseEvent me) {
        // System.out.println("Click");
    }

    public void mouseMoved(MouseEvent me) {
        Graphics g = f.getGraphics();
        x2 = me.getX();
        y2 = me.getY();
        g.drawLine(x1, y1, x2, y2);
        x1 = x2;
        y1 = y2;
        // System.out.println("Move");
    }

    public void mouseDragged(MouseEvent me) {
        // System.out.println("Drag");
    }

    public static void main(String a[]) {
        new Paint();
    }

}
