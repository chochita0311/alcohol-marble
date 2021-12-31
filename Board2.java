import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.Random;

public class Board2 extends JPanel implements ActionListener {

    private long seed = System.currentTimeMillis();
    private Random rand = new Random(seed);

    // controls the size of the dice
    private int dotSize = 30;
    private int margin = 15;

    private int dot1;
    private int dot2;

    public JTextField luckyMessageBox = new JTextField();

    public Board2() {
        JButton rollButton = new JButton("Roll!");
        rollButton.setBounds((750 - 150) / 2, 300, 150, 50);
        rollButton.addActionListener(this);
        add(rollButton);

        Font font = new Font("BRLNSDB", Font.BOLD, 20);

        luckyMessageBox.setBounds((750 - 300) / 2, 500, 300, 100);
        luckyMessageBox.setBackground(Color.white);
        luckyMessageBox.setFont(font);
        luckyMessageBox.setHorizontalAlignment(JTextField.CENTER);
        luckyMessageBox.setEditable(false);
        add(luckyMessageBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dot1 = rand.nextInt(6) + 1;
        dot2 = rand.nextInt(6) + 1;

        repaint();
        // System.out.println(((Board) getRootPane().getContentPane().getComponent(0)).player.getPos());
        getRootPane().getContentPane().requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDice(750 / 2 - dotSize * 3 - 40 - margin * 2 - margin, 150, dotSize, dot1, margin, g);
        drawDice(750 / 2 + margin, 150, dotSize, dot2, margin, g);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawDice(int x, int y, int dotSize, int dot, int margin, Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, dotSize * 3 + 40 + margin * 2, dotSize * 2 + 20 + margin * 2);

        g.setColor(Color.black);
        g.drawRect(x, y, dotSize * 3 + 40 + margin * 2, dotSize * 2 + 20 + margin * 2);

        if (dot >= 1)
            g.fillRect(x + margin, y + margin, dotSize, dotSize);
        if (dot >= 2)
            g.fillRect(x + dotSize + 20 + margin, y + margin, dotSize, dotSize);
        if (dot >= 3)
            g.fillRect(x + dotSize * 2 + 40 + margin, y + margin, dotSize, dotSize);
        if (dot >= 4)
            g.fillRect(x + margin, y + dotSize + 20 + margin, dotSize, dotSize);
        if (dot >= 5)
            g.fillRect(x + dotSize + 20 + margin, y + dotSize + 20 + margin, dotSize, dotSize);
        if (dot >= 6)
            g.fillRect(x + dotSize * 2 + 40 + margin, y + dotSize + 20 + margin, dotSize, dotSize);
    }

    public int getDot() {
        return dot1 + dot2;
    }

    public void initDot() {
        dot1 = 0;
        dot2 = 0;
    }

    public void showLuckyMessage() {
        luckyMessageBox.setText("제발 집으로 가시오");
    }

}
