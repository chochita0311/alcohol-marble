import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Board extends JPanel implements ActionListener {

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 75;
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    // image that represents the golden-key on the board
    private String imgPath;
    private BufferedImage image;

    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;

    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    // set Player to be public type to share player position between boards
    public Player player;
    private int dot;

    public Board() {
        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        setBackground(new Color(232, 232, 232));

        // initialize the game state
        player = new Player();
        // load the assets
        loadImage();

        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            imgPath = "images/golden-key.png";
            image = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn

        // get the result of rollig the dice
        dot = ((Board2) getRootPane().getContentPane().getComponent(1)).getDot();

        // prevent the player from disappearing off the board
        player.tick();
        // move player in rectangular way
        player.tick2(dot);

        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();

        if (player.getPos().equals(new Point(5, 0))) {
            ((Board2) getRootPane().getContentPane().getComponent(1)).showLuckyMessage();
        }

        // initialize the dice on board2 after move player
        ((Board2) getRootPane().getContentPane().getComponent(1)).initDot();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // when calling g.drawImage() we can use "this" for the ImageObserver
        // because Component implements the ImageObserver interface, and JPanel
        // extends from Component. So "this" Board instance, as a Component, can
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.
        drawBackground(g);
        player.draw(g, this);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g) {
        // draw a checkered background
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                // only color every other tile
                if ((row + col) % 2 == 1) {
                    // draw a square tile at the current row/column position
                    g.fillRect(
                            col * TILE_SIZE,
                            row * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE);
                }
            }
        }

        // draw a center square
        g.setColor(new Color(53, 128, 48));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (row != 0 && col != 0 && row != ROWS - 1 && col != COLUMNS - 1) {
                    g.fillRect(
                            col * TILE_SIZE,
                            row * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE);
                }
            }
        }

        // draw a golden-key
        g.drawImage(
                image,
                5 * Board.TILE_SIZE,
                0 * Board.TILE_SIZE,
                this);
    }

}
