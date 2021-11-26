import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Dice extends JPanel implements ActionListener {
    
    private Board board;
    private Player player;

    private long seed = System.currentTimeMillis();
    private Random rand = new Random(seed);
    
    private int x;
    private int y;
    private int dot;

    public Dice() {

        JButton b1 = new JButton("Roll");
        b1.setBounds(50, 50, 200, 50);
        b1.addActionListener(this);
        // b1.addFocusListener(this);
 
        add(b1);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDice(115, 100, g);

        Toolkit.getDefaultToolkit().sync();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        dot = rand.nextInt(6) + 1;

        // System.out.println(getRootPane().getContentPane().getComponent(0));
        // ((Board) getRootPane().getContentPane().getComponent(0)).actionPerformed(e);

        
        repaint();
        getRootPane().getContentPane().requestFocusInWindow();

    }

    private void drawDice(int x, int y, Graphics g) {

        g.setColor(Color.white);
        g.fillRect(x, y, 70, 65);

        g.setColor(Color.black);
        g.drawRect(x, y, 70, 65);
        
        if (dot>=1) g.fillRect(x + 10, y + 10, 15, 15);
        if (dot>=2) g.fillRect(x + 30, y + 10, 15, 15);
        if (dot>=3) g.fillRect(x + 50, y + 10, 15, 15);
        if (dot>=4) g.fillRect(x + 10, y + 40, 15, 15);
        if (dot>=5) g.fillRect(x + 30, y + 40, 15, 15);
        if (dot>=6) g.fillRect(x + 50, y + 40, 15, 15);

    }

    public int getDot() {
        return dot;
    }

    public void initDot() {
        dot = 0;
    }


}
