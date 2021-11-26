import javax.swing.*;
import java.awt.*;

class App {

    private static void initWindow() {
        // create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Alcohol Marble");
        // when we close the window, stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the jpanel to draw on.
        // this also initializes the game loop

        // JPanel mainPanel = new JPanel();
        // mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        Container contentPane = window.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));
        contentPane.setFocusable(true);

        Board board = new Board();
        // board.setFocusable(true);
        // add the jpanel to the window

        Dice dice = new Dice();
        
        // mainPanel.add(board);
        // mainPanel.add(b1);
        contentPane.add(board);
        contentPane.add(dice);

        contentPane.requestFocusInWindow();
        contentPane.addKeyListener(board);
        // contentPane.addMouseListener(dice);
        
        
        // window.add(mainPanel);


        // pass keyboard inputs to the jpanel

        
        
        // don't allow the user to resize the window
        window.setResizable(true);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        // window.setSize(800, 500);
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }

    public static void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}