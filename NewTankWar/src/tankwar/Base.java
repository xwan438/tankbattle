package tankwar;

import java.awt.*;

public class Base extends GameObject {
    public Base(String img, int x, int y, int width, int height, GamePanel gamePanel){
        super(img, x, y, width, height, gamePanel);
    }
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }
    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}