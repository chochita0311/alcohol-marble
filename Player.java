import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {

    // image that represents the player's position on the board
    private String imgPath;
    private BufferedImage image;
    // current position of the player on the board grid
    private Point pos;

    public Player() {
        // load the assets
        loadImage();

        // initialize the state
        pos = new Point(0, 0);
    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            imgPath = "images/player1span.png";
            image = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        g.drawImage(
                image,
                pos.x * Board.TILE_SIZE,
                pos.y * Board.TILE_SIZE,
                observer);
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= Board.COLUMNS) {
            pos.x = Board.COLUMNS - 1;
        }
        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= Board.ROWS) {
            pos.y = Board.ROWS - 1;
        }
    }

    public void tick2(int dot) {
        // this gets called once every tick, before the repainting process happens.
        if (dot != 0) {
            if (pos.y == 0 && pos.x < Board.COLUMNS - 1) {
                pos.translate(dot, 0);
                if (pos.x > Board.COLUMNS - 1) {
                    pos.translate(0, pos.x - Board.COLUMNS + 1);
                }
            } else if (pos.x == Board.COLUMNS - 1 && pos.y < Board.ROWS - 1) {
                pos.translate(0, dot);
                if (pos.y > Board.ROWS - 1) {
                    pos.translate(-pos.y + Board.ROWS - 1, 0);
                }
            } else if (pos.y == Board.ROWS - 1 && pos.x > 0) {
                pos.translate(-dot, 0);
                if (pos.x < 0) {
                    pos.translate(0, pos.x);
                }
            } else if (pos.x == 0 && pos.y > 0) {
                pos.translate(0, -dot);
                if (pos.y < 0) {
                    pos.translate(-pos.y, 0);
                }
            }
        }
    }

    public Point getPos() {
        return pos;
    }

}
