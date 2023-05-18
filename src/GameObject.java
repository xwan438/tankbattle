package tankwar;

import java.awt.*;

public abstract class GameObject {

    //game image 
    Image img;
    //image x 
    int x;
    //image y
    int y;
    //image width
    int width;
    //image height 
    int height;
    //image speed
    int speed;
    //image direction
    Direction direction;
    //tank type
    TankType tankType;
    //game main panel
    GamePanel gamePanel;

    public GameObject(){ }
    public GameObject(String img, int x, int y, GamePanel gamePanel) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }
    //wall, base
    public GameObject(String img, int x, int y, int width, int height, GamePanel gamePanel){
        this.img = Toolkit.getDefaultToolkit().getImage(img);;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gamePanel = gamePanel;
    }
    //both tank and ammo
    public GameObject(String img, int x, int y, int width, int height, int speed, Direction direction,
                      TankType tankType, GamePanel gamePanel) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.direction = direction;
        this.tankType = tankType;
        this.gamePanel = gamePanel;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamepanel) {
        this.gamePanel = gamePanel;
    }

    //paint element it self
    public abstract void paintSelf(Graphics g);

    //Gets the rectangle of the current game element, written for collision detection
    public abstract Rectangle getRec();
}
