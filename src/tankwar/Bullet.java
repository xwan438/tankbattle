package tankwar;

import java.awt.*;
import java.util.List;

public class Bullet extends GameObject{
    public Bullet(String img, int x, int y, int width, int height, int speed, Direction direction, TankType tankType, GamePanel gamePanel) {
        super(img, x,  y, width, height, speed, direction, tankType, gamePanel);
    }

    public void go(){
        /**Determine the direction of movement*/
        switch (direction){
            case UP:
                upward();
                break;
            case LEFT:
                leftward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
        }
        //collision detection
        hitTank();
        hitWall();
        hitBase();
    }
    //Bullet movement
    public void leftward(){
        x -= speed;
        moveToBorder();
    }
    public void rightward(){
        x += speed;
        moveToBorder();
    }
    public void upward(){
        y -= speed;
        moveToBorder();
    }
    public void downward(){
        y += speed;
        moveToBorder();
    }

    /**Bullet Tank Collision Detection*/
    public void hitTank(){
        Rectangle next= this.getRec();
        List<Tank> tanks = this.gamePanel.tankList;
        List<Bot> bots = this.gamePanel.botList;
        //Bullets and Tank
        for(Tank tank: tanks){
            if(tank.getRec().intersects(next)){
                if(tankType.equals(TankType.BOT)){
                    System.out.println("hit tank");
                    tank.alive = false;
                    this.gamePanel.blastList.add(new BlastObj(tank.x-34, tank.y-14));
                    this.gamePanel.tankList.remove(tank);
                    this.gamePanel.removeList.add(this);
                    break;
                }
            }
        }
        //Bullets andbot
        for(Bot bot: bots){
            if(bot.getRec().intersects(next)){
                if(!tankType.equals(TankType.BOT)){
                    System.out.println("hit bot");
                    this.gamePanel.blastList.add(new BlastObj(bot.x-34, bot.y-14));
                    this.gamePanel.botList.remove(bot);
                    this.gamePanel.removeList.add(this);
                    break;
                }
            }
        }
    }

    public void hitBase(){
        Rectangle next = this.getRec();
        for(Base base: gamePanel.baseList) {
            if (base.getRec().intersects(next)) {
                this.gamePanel.baseList.remove(base);
                this.gamePanel.removeList.add(this);
                this.gamePanel.state = 4;
                break;
            }
        }
    }

    public void hitWall(){
        Rectangle next = this.getRec();
        List<Wall> walls = this.gamePanel.wallList;
        for(Wall w: walls) {
            if (w.getRec().intersects(next)) {
                this.gamePanel.wallList.remove(w);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void moveToBorder(){
        if (x < 0||x > this.gamePanel.getWidth()) {
            this.gamePanel.removeList.add(this);
        }
        if(y < 0||y > this.gamePanel.getHeight()){
            this.gamePanel.removeList.add(this);
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
