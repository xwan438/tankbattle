package tankwar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JFrame {

    /** 定义双缓存图片 */
    Image offScreenImage = null;
    //游戏状态 0 游戏未开始，1 单人模式，2 双人模式， 3 游戏暂停， 4 游戏失败，5 游戏成功
    int state= 0;
    //临时变量
    int a = 1;
    //重绘次数
    int count = 0;
    //窗口长宽
    int width = 800;
    int height = 610;
    //敌人数量
    int enemyCount = 0;
    //高度
    int y = 150;
    //是否开始
    boolean start = false;
    //物体集合
    List<Bullet> bulletList = new ArrayList<>();
    List<Bot> botList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    List<Wall> wallList = new ArrayList<>();
    List<Bullet> removeList = new ArrayList<>();
    List<Base> baseList = new ArrayList<>();
    List<BlastObj> blastList = new ArrayList<>();
    //背景图片
    Image background = Toolkit.getDefaultToolkit().getImage("images/background.jpg");
    //指针图片
    Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
    //基地
    Base base = new Base("images/star.gif", 365, 560, 50, 50, this);
    //玩家
    Tank player1 = new Tank("images/player1/p1tankU.gif", 125, 510,40, 30, 15, Direction.UP, TankType.PLAYER1, this);
    Tank player2 = new Tank("images/player2/p1tankU.gif", 675, 510,40, 30, 15, Direction.UP, TankType.PLAYER2, this);

    //窗口的启动方法
    public void launch(){
        //标题
        setTitle("坦克大战");
        //窗口初始大小
        setSize(width, height);
        //用户不能调整大小
        setResizable(false);
        //使窗口可见
        setVisible(true);
        //获取屏幕分辨率，使窗口生成时居中
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension d = tool.getScreenSize();
        setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
        //添加键盘事件
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
                        //添加玩家
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
        //添加围墙
        for(int i = 0; i< 14; i ++){
            wallList.add(new Wall("images/walls.gif", 50+i*50 ,170,50, 50, this ));
        }
        wallList.add(new Wall("images/walls.gif", 305 ,560, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 305 ,500, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 365 ,500, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 425 ,500, 50, 50,this ));
        wallList.add(new Wall("images/walls.gif", 425 ,560, 50, 50,this ));
        //添加基地
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
                //线程休眠  1秒 = 1000毫秒
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
        //创建和容器一样大小的Image图片
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        //获得该图片的画布
        Graphics gImage= offScreenImage.getGraphics();
        //填充整个画布
        gImage.fillRect(0, 0, width, height);
        if(state == 0){
            //添加背景
            gImage.drawImage(background,0,0,null);
            //挂变画笔颜色
            gImage.setColor(Color.BLUE);
            //改变文字大小和样式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //添加文字
            gImage.drawString("mode",220,100);
            gImage.drawString("single",220,200);
            gImage.drawString("double",220,300);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            gImage.drawImage(background,0,0,null);
            //改变画笔的颜色
            gImage.setColor(Color.BLUE);
            //改变文字大小和样式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            //添加文字
            gImage.drawString("游戏开始",220,300);

            if(state == 1){
                gImage.drawString("单人模式",220,200);
            }
            else{
                gImage.drawString("双人模式",220,200);
            }

            //paint重绘游戏元素
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
            //重绘次数+1
            count++;
        }
        else if(state == 3){
            gImage.drawImage(background,0,0,null);
            //改变画笔的颜色
            gImage.setColor(Color.yellow);
            //改变文字大小和样式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            gImage.drawString("游戏暂停",220,200);
        }
        else if(state == 4){
            gImage.drawImage(background,0,0,null);
            //改变画笔的颜色
            gImage.setColor(Color.RED);
            //改变文字大小和样式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            gImage.drawString("游戏失败",220,200);
        }
        else if(state == 5){
            gImage.drawImage(background,0,0,null);
            //改变画笔的颜色
            gImage.setColor(Color.yellow);
            //改变文字大小和样式
            gImage.setFont(new Font("仿宋",Font.BOLD,50));
            gImage.drawString("游戏胜利",220,200);
        }
        /** 将缓冲区绘制好的图形整个绘制到容器的画布中 */
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}