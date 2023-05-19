package tankwar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JFrame {

    /** Define dual cache images */
    Image offScreenImage = null;
    //Game State 0 Game not started，1 SINGLE PLAYER，2 two player， 3 Game Pause 4 Game failed，5 Game Success
    int state= 0;
    //Temporary variables
    int a = 1;
    //Redraw Times
    int count = 0;
    //Window length and width
    int width = 800;
    int height = 610;
    //Number of enemies
    int enemyCount = 0;
    //height
    int y = 150;
    //Start or not
    boolean start = false;
    //Object Collection
    List<Bullet> bulletList = new ArrayList<>();
    List<Bot> botList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    List<Wall> wallList = new ArrayList<>();
    List<Bullet> removeList = new ArrayList<>();
    List<Base> baseList = new ArrayList<>();
    List<BlastObj> blastList = new ArrayList<>();
    //Background image
    Image background = Toolkit.getDefaultToolkit().getImage("images/background.jpg");
    //Pointer image
    Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
    //base
    Base base = new Base("images/star.gif", 365, 560, 50, 50, this);
    //game player
    Tank player1 = new Tank("images/player1/p1tankU.gif", 125, 510,40, 30, 15, Direction.UP, TankType.PLAYER1, this);
    Tank player2 = new Tank("images/player2/p1tankU.gif", 675, 510,40, 30, 15, Direction.UP, TankType.PLAYER2, this);

    //Startup method for windows
    public void launch(){
        //title
        setTitle("Tank Battle");
        //Initial window size
        setSize(width, height);
        //User cannot adjust size
        setResizable(false);
        //Make the window visible
        setVisible(true);
        //Obtain screen resolution to center the window during generation
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension d = tool.getScreenSize();
        setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
        //Add Keyboard Event
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_W:
                        y = 150;
                        a = 1;
                        break;
                    case KeyEvent.VK_S:
                        y = 250;
                        a = 2;
                        break;
                    case KeyEvent.VK_ENTER:
                        state = a;
                        //Add Players
                        if(state == 1 && !start){
                            tankList.add(player1);
                        }else{
                            tankList.add(player1);
                            tankList.add(player2);
                        }
                        start = true;
                        break;
                    case KeyEvent.VK_P:
                        if(state != 3){
                            a = state;
                            state = 3;
                        }
                        else{
                            state = a;
                            if(a == 0) {
                                a = 1;
                            }
                        }
                    default:
                        break;
                }
            }
        });
        //Add fence
        for(int i = 0; i< 14; i ++){
            wallList.add(new Wall("images/walls.gif", 50+i*50 ,170,50, 50, this ));
        }
        wallList.add(new Wall("images/walls.gif", 305 ,560, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 305 ,500, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 365 ,500, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 425 ,500, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 425 ,560, 50, 50,this ));
        //Add Base
        baseList.add(base);


        while (true){
            if(botList.size() == 0 && enemyCount == 10){
                state = 5;
            }
            if(tankList.size() == 0 && (state == 1 || state == 2)){

                state = 4;
            }
            if(state == 1 || state == 2){
                if (count % 100 == 1 && enemyCount < 10) {
                    botList.add(new Bot("images/enemy/enemy1U.gif", 125, 110,40, 30, 4, Direction.UP, TankType.BOT, this));
                    enemyCount++;
                    //System.out.println("bot: " + botList.size());
                }
            }
            repaint();
            try {
                //Thread sleep for 1 second=1000 milliseconds
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println(bulletList.size());
        //System.out.println("tank"+tankList.size());
        //Create an Image image of the same size as the container
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        //Obtain the canvas for this image
        Graphics gImage= offScreenImage.getGraphics();
        //Fill the entire canvas
        gImage.fillRect(0, 0, width, height);
        if(state == 0){
            //Add Background
            gImage.drawImage(background,0,0,null);
            //change variable brush color
            gImage.setColor(Color.BLUE);
            //Change text size and style
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //Add Text
            gImage.drawString("mode",220,100);
            gImage.drawString("single",220,200);
            gImage.drawString("double",220,300);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            gImage.drawImage(background,0,0,null);
            //Change the color of the brush
            gImage.setColor(Color.BLUE);
            //Change text size and style
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //Add Text
            gImage.drawString("game start",220,300);

            if(state == 1){
                gImage.drawString("single",220,200);
            }
            else{
                gImage.drawString("double",220,200);
            }

            //paint Redraw game elements
            for(Tank tank : tankList){
                tank.paintSelf(gImage);
            }
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot: botList){
                bot.paintSelf(gImage);
            }
            for (Wall wall: wallList){
                wall.paintSelf(gImage);
            }
            for(Base base : baseList){
                base.paintSelf(gImage);
            }
            for(BlastObj blast : blastList){
                blast.paintSelf(gImage);
            }
            //Redraw times+1
            count++;
        }
        else if(state == 3){
            gImage.drawImage(background,0,0,null);
            //Change the color of the brush
            gImage.setColor(Color.yellow);
            //Change text size and style
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            gImage.drawString("gmae pause",220,200);
        }
        else if(state == 4){
            gImage.drawImage(background,0,0,null);
            //Change the color of the brush
            gImage.setColor(Color.RED);
            //Change text size and style
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            gImage.drawString("game fail",220,200);
        }
        else if(state == 5){
            gImage.drawImage(background,0,0,null);
            //Change the color of the brush
            gImage.setColor(Color.yellow);
            //Change text size and style
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            gImage.drawString("victory",220,200);
        }
        /** Draw the entire buffer drawn graph onto the container's canvas */
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}
