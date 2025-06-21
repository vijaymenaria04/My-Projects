import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICalculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton add, sub, mul, div;
    JButton dec, equ, del, clr;
    JPanel panel;

    Font font = new Font("Arial", Font.PLAIN, 20);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    GUICalculator() {
        frame = new JFrame("Calculator");
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(font);
        textField.setEditable(false);
        frame.add(textField);

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        dec = new JButton(".");
        equ = new JButton("=");
        del = new JButton("Del");
        clr = new JButton("C");

        functionButtons[0] = add;
        functionButtons[1] = sub;
        functionButtons[2] = mul;
        functionButtons[3] = div;
        functionButtons[4] = dec;
        functionButtons[5] = equ;
        functionButtons[6] = del;
        functionButtons[7] = clr;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(font);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        del.setBounds(50, 430, 100, 40);
        clr.setBounds(150, 430, 100, 40);

        frame.add(del);
        frame.add(clr);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(add);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(sub);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mul);
        panel.add(dec);
        panel.add(numberButtons[0]);
        panel.add(equ);
        panel.add(div);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        if (e.getSource() == dec) {
            textField.setText(textField.getText() + ".");
        }

        if (e.getSource() == add) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == sub) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mul) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == div) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equ) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0) result = num1 / num2;
                    else result = 0;
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clr) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        if (e.getSource() == del) {
            String s = textField.getText();
            if (s.length() > 0) {
                textField.setText(s.substring(0, s.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}
