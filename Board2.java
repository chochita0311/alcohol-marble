import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board2 extends JPanel implements ActionListener {

    private long seed = System.currentTimeMillis();
    private Random rand = new Random(seed);

    private int dotSize;
    private int margin;

    private int dot1;
    private int dot2;

    public Board2() {

        JButton rollButton = new JButton("Roll!");
        rollButton.setBounds((750 - 150) / 2, 300, 150, 50);
        rollButton.addActionListener(this);
        add(rollButton);

        dotSize = 30;
        margin = 15;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDice(750 / 2 - dotSize * 3 - 40 - margin * 2 - margin, 150, dotSize, dot1, margin, g);
        drawDice(750 / 2 + margin, 150, dotSize, dot2, margin, g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dot1 = rand.nextInt(6) + 1;
        dot2 = rand.nextInt(6) + 1;

        repaint();
        getRootPane().getContentPane().requestFocusInWindow();
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

}
