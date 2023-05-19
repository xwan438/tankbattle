package tankwar;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Tank extends GameObject{

    private boolean attackCoolDown =true;//Attack cooling state
    private int attackCoolDownTime =1000;//Attack cooldown time milliseconds interval 1000ms Launch bullets
    private String upImage; //Picture when moving up
    private String downImage;//Picture when moving down
    private String rightImage;//Picture when moving right
    private String leftImage;//Picture when moving left
    boolean alive = true;
    //tank size
    int width = 40;
    int height = 50;
    //Tank head coordinates
    Point p;

    //Tank coordinates, direction, image, type (good, bad), panel (which level)
    public Tank(String img, int x, int y, int width, int height, int speed, Direction direction, TankType tankType, GamePanel gamePanel) {
        super(img, x,  y, width, height, speed, direction, tankType, gamePanel);

        //Pictures of different types of tanks
        switch(tankType){
            case PLAYER1:
                upImage = "images/player1/p1tankU.gif";
                downImage = "images/player1/p1tankD.gif";
                leftImage = "images/player1/p1tankL.gif";
                rightImage = "images/player1/p1tankR.gif";
                break;
            case PLAYER2:
                upImage = "images/player2/p2tankU.gif";
                downImage = "images/player2/p2tankD.gif";
                leftImage = "images/player2/p2tankL.gif";
                rightImage = "images/player2/p2tankR.gif";
                break;
            case BOT:
                upImage = "images/enemy/enemy1U.gif";
                downImage = "images/enemy/enemy1D.gif";
                leftImage = "images/enemy/enemy1L.gif";
                rightImage = "images/enemy/enemy1R.gif";
                break;
            default:
                break;
        }
    }

    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImage);
        if(!hitWall(x-speed, y) && !moveToBorder(x-speed, y) && alive){
            this.x -= speed;
        }
    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImage);
        if(!hitWall(x+speed, y) && !moveToBorder(x+speed, y) && alive){
            this.x += speed;
        }
    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImage);
        if(!hitWall(x, y-speed) && !moveToBorder(x, y- speed) && alive){
            this.y -= speed;
        }
    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImage);
        if(!hitWall(x, y+speed) && !moveToBorder(x, y+speed) && alive){
            this.y += speed;
        }
    }
    public void attack(){
        Point p = getHeadPoint();
        if(attackCoolDown && alive){
            Bullet bullet = new Bullet("images/bullet/bulletGreen.gif",p.x,p.y,10,10,speed,direction,TankType.PLAYER1,gamePanel);
            this.gamePanel.bulletList.add(bullet);
            attackCoolDown = false;
            new AttackCD().start();
        }
    }

    public boolean hitWall(int x, int y){
        //Assuming the player's tank advances, the rectangle formed by the next position
        Rectangle next = new Rectangle(x, y, width, height);
        //All the walls in the map
        List<Wall> walls = this.gamePanel.wallList;
        //Determine whether two rectangles intersect (i.e. whether they collide with a wall)
        for(Wall w:walls){
            if(w.getRec().intersects(next)){
                return true;
            }
        }
        return false;
    }

    public boolean moveToBorder(int x, int y){
        if(x < 0){
            return true;
        }else if(x > this.gamePanel.getWidth()-width){
            return true;
        }
        if(y < 0){
            return true;
        }else if(y > this.gamePanel.getHeight()-height){
            return true;
        }
        return false;
    }

    public class AttackCD extends Thread{
        public void run(){
            attackCoolDown=false;//Set the attack function to a cooling state
            try{
                Thread.sleep(attackCoolDownTime);//Sleep for 1 second
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            attackCoolDown=true;//Deactivate the cooling state of the attack function
            this.stop();
        }
    }

    //Determine the head position based on the direction, where x and y are the points in the lower left corner
    public Point getHeadPoint(){
        switch (direction){
            case UP:
                return new Point(x + width/2, y );
            case LEFT:
                return new Point(x, y + height/2);
            case DOWN:
                return new Point(x + width/2, y + height);
            case RIGHT:
                return new Point(x + width, y + height/2);
            default:
                return null;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        if(this.gamePanel.count == 1){
            this.gamePanel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e){
                    new AttackCD();
                    int key = e.getKeyCode();
                    switch (key){
                        case KeyEvent.VK_A:
                            gamePanel.player1.leftward();
                            break;
                        case KeyEvent.VK_S:
                            gamePanel.player1.downward();
                            break;
                        case KeyEvent.VK_D:
                            gamePanel.player1.rightward();
                            break;
                        case KeyEvent.VK_W:
                            gamePanel.player1.upward();
                            break;
                        case KeyEvent.VK_SPACE:
                            gamePanel.player1.attack();
                            break;
                        default:
                            break;
                    }
                }
            });
            this.gamePanel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e){
                    new AttackCD();
                    int key = e.getKeyCode();
                    switch (key){
                        case KeyEvent.VK_LEFT:
                            gamePanel.player2.leftward();
                            break;
                        case KeyEvent.VK_DOWN:
                            gamePanel.player2.downward();
                            break;
                        case KeyEvent.VK_RIGHT:
                            gamePanel.player2.rightward();
                            break;
                        case KeyEvent.VK_UP:
                            gamePanel.player2.upward();
                            break;
                        case KeyEvent.VK_K:
                            gamePanel.player2.attack();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
