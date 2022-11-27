
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.event.*;
import java.util.*;

public class Calculator implements ActionListener {
    JFrame root;
    JButton nums[] = new JButton[10];
    JButton operas[] = new JButton[8];
    JButton equal;
    JButton clear;
    final int bsize = 50;
    final int xoffset = 75;
    final int yoffset = 75;
    final int gap = 20;
    ColorUIResource color = new ColorUIResource(76, 115, 173);
    JTextField entry;

    ArrayList<Character> operations = new ArrayList<Character>();
    ArrayList<Character> digits = new ArrayList<Character>();

    Calculator() {
        Character ops[] = { '+', '-', '*', '/', '^', '(', ')', '<' };
        for (int i = 0; i < ops.length; i++) {
            operations.add(ops[i]);
        }
        for (int i = 0; i < 10; i++) {
            digits.add((char) (i + '0'));
        }

        root = new JFrame();
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.getContentPane().setBackground(color);
        root.setBackground(color);
        root.setSize(500, 500);
        root.setVisible(true);
        root.setLayout(null);
        root.setLocationRelativeTo(null);
        root.setResizable(false);
        root.setTitle("Calculator");

        entry = new JTextField();
        entry.setFont(new FontUIResource(null, 0, 28));
        entry.setEditable(false);
        entry.setBounds(0, 0, 500, 50);
        root.add(entry);

        for (int i = 0; i < 10; i++) {
            nums[i] = new JButton("" + i);
        }

        for (int i = 0; i < 9; i++) {
            nums[i + 1].setBounds((i % 3) * (bsize + gap) + xoffset, (3 - (int) (i / 3)) * (bsize + gap) + yoffset,
                    bsize,
                    bsize);
            nums[i + 1].addActionListener(this);
            root.add(nums[i + 1]);
        }

        JButton zero = nums[0];
        zero.setBounds(nums[2].getX(), nums[2].getY() + bsize + gap, bsize, bsize);
        root.add(zero);

        int x = nums[9].getX();
        int y = nums[9].getY();

        for (int i = 0; i < operas.length; i++) {
            operas[i] = new JButton("" + operations.get(i));
            operas[i].addActionListener(this);
            operas[i].setBounds(x + ((int) (i / 4) + 1) * (gap + bsize), (i % 4) * (gap + bsize) + y, bsize, bsize);
            root.add(operas[i]);
        }

        equal = new JButton("=");
        equal.setBounds(nums[3].getX(), nums[3].getY() + bsize + gap, bsize, bsize);
        equal.addActionListener(this);
        root.add(equal);

        clear = new JButton("C");
        clear.setBounds(nums[1].getX(), nums[1].getY() + bsize + gap, bsize, bsize);
        clear.addActionListener(this);
        root.add(clear);

    }

    public void actionPerformed(ActionEvent event) {
        char input = event.getActionCommand().charAt(0);
        String previous = entry.getText();
        char lastchar = ' ';
        if (previous.length() != 0)
            lastchar = previous.charAt(previous.length() - 1);
        if (input == '<') {
            if (lastchar == ' ')
                return;
            entry.setText(entry.getText().substring(0, entry.getText().length() - 1));
        } else if (input == 'C') {
            entry.setText("");
        } else if (input == '=') {

        } else {
            if (operations.contains(lastchar) && operations.contains(input)) {
                System.out.println(input + " " + lastchar);
                if ((lastchar == '(' || lastchar == ')') && (input != '(' && input != ')')) {
                    entry.setText(
                            previous.substring(0, previous.length() - 1));
                } else if (input != '(' && input != ')') {
                    entry.setText(
                            previous.substring(0, previous.length() - 1) + input);
                } else
                    entry.setText(entry.getText() + input);
            } else
                entry.setText(entry.getText() + input);
        }
    }

    public static void main(String args[]) {
        new Calculator();
    }
}
