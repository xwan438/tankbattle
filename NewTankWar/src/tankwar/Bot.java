package tankwar;

import java.awt.*;
import java.util.Random;

public class Bot extends Tank{
    int moveTime = 0;
    public Bot(String img, int x, int y, int width, int height, int speed, Direction direction, TankType tankType, GamePanel gamePanel) {
        super(img, x, y, width, height, speed, direction, tankType, gamePanel);
    }

    public void go(){
        attack();
        if(moveTime>=20) {
            direction=randomDirection();
            moveTime=0;
        }else {
            moveTime+=1;
        }
        switch(direction) {
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
            case LEFT:
                leftward();
                break;
        }
    }

    //电脑坦克随机方向
    public Direction randomDirection() {
        Random r = new Random();
        int rnum = r.nextInt(4);
        switch(rnum) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.LEFT;
            default:
                return Direction.DOWN;
        }
    }

    //只有4%几率攻击
    public void attack() {
        Point p = getHeadPoint();
        Random r = new Random();
        int rnum =r.nextInt(100);
        //System.out.println("r: "+rnum);
        if(rnum<1) {
            Bullet bullet = new Bullet("images/bullet/bulletYellow.gif",p.x,p.y,10,10,speed,direction,TankType.BOT,gamePanel);
            this.gamePanel.bulletList.add(bullet);
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}