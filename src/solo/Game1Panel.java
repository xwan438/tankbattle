package solo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game1Panel extends JPanel implements KeyListener, Runnable {
	MyTank myTank1 = null;
	MyTank myTank2 = null;
	Builds build = null;
	JFrame g1f = null;
	JFrame sp = null;

	private boolean Vic = false;

	private static Image[] p1TankImg = new Image[4];
	private static Image[] p2TankImg = new Image[4];
	private static Image[] bulletImg = new Image[4];

	private int[][] builds = new int[16][16];

	private boolean A = false;
	private boolean W = false;
	private boolean D = false;
	private boolean S = false;
	private boolean J = false;

	private boolean A2 = false;
	private boolean W2 = false;
	private boolean D2 = false;
	private boolean S2 = false;
	private boolean J2 = false;

	public static void initialize() {
		p1TankImg[0] = Toolkit.getDefaultToolkit().getImage("img\\p1tankL.png");
		p1TankImg[1] = Toolkit.getDefaultToolkit().getImage("img\\p1tankU.png");
		p1TankImg[2] = Toolkit.getDefaultToolkit().getImage("img\\p1tankR.png");
		p1TankImg[3] = Toolkit.getDefaultToolkit().getImage("img\\p1tankD.png");
		p2TankImg[0] = Toolkit.getDefaultToolkit().getImage("img\\p2tankL.png");
		p2TankImg[1] = Toolkit.getDefaultToolkit().getImage("img\\p2tankU.png");
		p2TankImg[2] = Toolkit.getDefaultToolkit().getImage("img\\p2tankR.png");
		p2TankImg[3] = Toolkit.getDefaultToolkit().getImage("img\\p2tankD.png");
		bulletImg[0] = Toolkit.getDefaultToolkit().getImage("img\\bulletL.png");
		bulletImg[1] = Toolkit.getDefaultToolkit().getImage("img\\bulletU.png");
		bulletImg[2] = Toolkit.getDefaultToolkit().getImage("img\\bulletR.png");
		bulletImg[3] = Toolkit.getDefaultToolkit().getImage("img\\bulletD.png");
	}

	public Game1Panel(JFrame g1f, JFrame sp) {
		this.g1f = g1f;
		this.sp = sp;
		initialize();
		build = new Builds();
		builds = build.getBuilds();
		myTank1 = new MyTank(1 * 32, 7 * 32, 2, 0); // 初始化坦克1
		myTank2 = new MyTank(14 * 32, 7 * 32, 0, 1); // 初始化坦克2
		myTank1.setSpeed(4);
		myTank2.setSpeed(4);
		Thread thread = new Thread(myTank2);
		thread.start();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setBackground(Color.BLACK);
		if (myTank1.isLife()) {
			drawTank(myTank1.getX(), myTank1.getY(), myTank1.getDir(), g, myTank1.getType());// 画我方1p坦克
		}
		if (myTank2.isLife()) {
			drawTank(myTank2.getX(), myTank2.getY(), myTank2.getDir(), g, myTank2.getType());// 画我方2p坦克
		}
		drawSteel(g);
		drawGrass(g);
		drawWall(g);
		Move();
		for (int i = 0; i < myTank1.bulletVector.size(); i++) {
			if (this.Vic == false) {
				isBoom(myTank1.bulletVector.get(i));
				isVictory(myTank1.bulletVector.get(i), myTank2);
			}
			if (myTank1.bulletVector.get(i).isBoom() == true) {
				drawBullet(myTank1.bulletVector.get(i).getX(), myTank1.bulletVector.get(i).getY(),
						myTank1.bulletVector.get(i).getDir(), g);
				if (this.Vic == true) {
					myTank2.setLife(false);
				}
				myTank1.bulletVector.get(i).setLife(false);
				myTank1.bulletVector.get(i).setBoom(false);
			}
			if (myTank1.bulletVector.get(i).isLife() == true) {
				drawBullet(myTank1.bulletVector.get(i).getX(), myTank1.bulletVector.get(i).getY(),

						myTank1.bulletVector.get(i).getDir(), g);
			}
			if (myTank1.bulletVector.get(i).isLife() == false) {
				myTank1.bulletVector.remove(myTank1.bulletVector.get(i));
			}
		}
		for (int i = 0; i < myTank2.bulletVector.size(); i++) {
			if (this.Vic == false) {
				isBoom(myTank2.bulletVector.get(i));
				isVictory(myTank2.bulletVector.get(i), myTank1);
			}
			if (myTank2.bulletVector.get(i).isBoom() == true) {
				drawBullet(myTank2.bulletVector.get(i).getX(), myTank2.bulletVector.get(i).getY(),
						myTank2.bulletVector.get(i).getDir(), g);
				if (this.Vic == true) {
					myTank1.setLife(false);
					drawTank(myTank1.getX(), myTank1.getY(), myTank1.getDir(), g, myTank1.getType());
				}
				myTank2.bulletVector.get(i).setLife(false);
				myTank2.bulletVector.get(i).setBoom(false);
			}
			if (myTank2.bulletVector.get(i).isLife() == true) {
				drawBullet(myTank2.bulletVector.get(i).getX(), myTank2.bulletVector.get(i).getY(),
						myTank2.bulletVector.get(i).getDir(), g);
			}
			if (myTank2.bulletVector.get(i).isLife() == false) {
				myTank2.bulletVector.remove(myTank2.bulletVector.get(i));
			}
		}
		drawSea(g);
		if (this.Vic == true) {
			drawOver(g, 200, 224);
		}
	}

	public void isVictory(Bullet bullet, MyTank myTank) {
		int testX = 0, testY = 0;
		switch (bullet.getDir()) {
		case 0:
			testX = bullet.getX() - bullet.getSpeed();
			testY = bullet.getY();
			if (((bullet.getX() - myTank.getX() - 32) <= bullet.getSpeed())
					&& ((bullet.getX() - myTank.getX() - 32) >= 0)
					&& ((testY >= myTank.getY()) && (testY < myTank.getY() + 32))) {
				bullet.setX(myTank.getX() + 32);
				bullet.setY(testY);
				bullet.setBoom(true);
				this.Vic = true;
				// drawBoom
			}
			break;
		case 1:
			testX = bullet.getX();
			testY = bullet.getY() - bullet.getSpeed();
			if (((bullet.getY() - myTank.getY() - 32) <= bullet.getSpeed())
					&& ((bullet.getY() - myTank.getY() - 32) >= 0)
					&& ((testX >= myTank.getX()) && (testX < myTank.getX() + 32))) {
				bullet.setX(testX);
				bullet.setY(myTank.getY() + 32);
				bullet.setBoom(true);
				this.Vic = true;
				// drawBoom
			}
			break;
		case 2:
			testX = bullet.getX() + bullet.getSpeed();
			testY = bullet.getY();
			if (((myTank.getX() - bullet.getX() - 8) <= bullet.getSpeed()) && ((myTank.getX() - bullet.getX() - 8) >= 0)
					&& ((testY >= myTank.getY()) && (testY < myTank.getY() + 32))) {
				bullet.setX(myTank.getX() - 8);
				bullet.setY(testY);
				bullet.setBoom(true);
				this.Vic = true;
				// drawBoom
			}
			break;
		case 3:
			testX = bullet.getX();
			testY = bullet.getY() + bullet.getSpeed();
			if (((myTank.getY() - bullet.getY() - 8) <= bullet.getSpeed()) && ((myTank.getY() - bullet.getY() - 8) >= 0)
					&& ((testX >= myTank.getX()) && (testX < myTank.getX() + 32))) {
				bullet.setX(testX);
				bullet.setY(myTank.getY() - 8);
				bullet.setBoom(true);
				this.Vic = true;
				// drawBoom
			}
			break;
		}
	}

	public void isBoom(Bullet bullet) {
		int testX = 0, testY = 0, i = 0, j = 0, m = 0, n = 0, cX = 0, cY = 0, flag = 0;
		switch (bullet.getDir()) {
		case 0:
			testX = bullet.getX() - bullet.getSpeed();
			testY = bullet.getY();
			i = testX / 32;
			j = testY / 32;
			m = i;
			n = (testY + 8) / 32;
			if ((bullet.getX() - (i + 1) * 32) <= bullet.getSpeed()) {
				flag = 1;
			}
			cX = (i + 1) * 32;
			cY = testY;
			break;
		case 1:
			testX = bullet.getX();
			testY = bullet.getY() - bullet.getSpeed();
			i = testX / 32;
			j = testY / 32;
			m = (testX + 8) / 32;
			n = j;
			if ((bullet.getY() - (j + 1) * 32) <= bullet.getSpeed()) {
				flag = 1;
			}
			cX = testX;
			cY = (j + 1) * 32;
			break;
		case 2:
			testX = bullet.getX() + bullet.getSpeed();
			testY = bullet.getY();
			i = (testX + 8) / 32;
			j = testY / 32;
			m = i;
			n = (testY + 8) / 32;
			if ((i * 32 - (bullet.getX() + 8)) <= bullet.getSpeed()) {
				flag = 1;
			}
			cX = i * 32 - 8;
			cY = testY;
			break;
		case 3:
			testX = bullet.getX();
			testY = bullet.getY() + bullet.getSpeed();
			i = testX / 32;
			j = (testY + 8) / 32;
			m = (testX + 8) / 32;
			n = j;
			if ((j * 32 - (bullet.getY() + 8)) <= bullet.getSpeed()) {
				flag = 1;
			}
			cX = testX;
			cY = j * 32 - 8;
			break;
		}
		if ((builds[i][j] == 1 || builds[i][j] == 4 || builds[m][n] == 1 || builds[m][n] == 4) && flag == 1) {
			bullet.setX(cX);
			bullet.setY(cY);
			bullet.setBoom(true);
			// drawBoom
			if (builds[i][j] == 4)
				builds[i][j] = 0;
			if (builds[m][n] == 4)
				builds[m][n] = 0;
		}
	}

	public void drawTank(int x, int y, int dir, Graphics g, int type) {
		switch (type) {
		case 0:
			g.drawImage(this.p1TankImg[dir], x, y, 32, 32, this);
			break;

		case 1:
			g.drawImage(this.p2TankImg[dir], x, y, 32, 32, this);
			break;
		}
	}

	public void drawBullet(int x, int y, int dir, Graphics g) {
		g.drawImage(this.bulletImg[dir], x, y, 8, 8, this);
	}

	public void drawSteel(Graphics g) {
		Image steel = Toolkit.getDefaultToolkit().getImage("img\\steel2.png");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (builds[i][j] == 1) {
					g.drawImage(steel, i * 32, j * 32, 32, 32, this);
				}
			}
		}
	}

	public void drawGrass(Graphics g) {
		Image grass = Toolkit.getDefaultToolkit().getImage("img\\grass.png");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (builds[i][j] == 2) {
					g.drawImage(grass, i * 32, j * 32, 32, 32, this);
				}
			}
		}
	}

	public void drawSea(Graphics g) {
		Image sea = Toolkit.getDefaultToolkit().getImage("img\\sea.gif");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (builds[i][j] == 3) {
					g.drawImage(sea, i * 32, j * 32, 32, 32, this);
				}
			}
		}
	}

	public void drawWall(Graphics g) {
		Image wall = Toolkit.getDefaultToolkit().getImage("img\\wall.png");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (builds[i][j] == 4) {
					g.drawImage(wall, i * 32, j * 32, 32, 32, this);
				}
			}
		}
	}

	public void drawOver(Graphics g, int x, int y) {
		Image over = Toolkit.getDefaultToolkit().getImage("img\\over.gif");
		g.drawImage(over, x, y, 80, 45, this);
	}

	public boolean isVic() {
		return Vic;
	}

	public void setVic(boolean vic) {
		Vic = vic;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 65:// a
			A = true;
			break;
		case 87:// w
			W = true;
			break;
		case 68:// d
			D = true;
			break;
		case 83:// s
			S = true;
			break;
		case 74:// j
			J = true;
			break;
		case 37:// 左
			A2 = true;
			break;
		case 38:// 上
			W2 = true;
			break;
		case 39:// 右
			D2 = true;
			break;
		case 40:// 下
			S2 = true;
			break;
		case 97:// 1
			J2 = true;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 65:// a
			A = false;
			break;
		case 87:// w
			W = false;
			break;
		case 68:// d
			D = false;
			break;
		case 83:// s
			S = false;
			break;
		case 74:// j
			J = false;
			break;
		case 37:// 左
			A2 = false;
			break;
		case 38:// 上
			W2 = false;
			break;
		case 39:// 右
			D2 = false;
			break;
		case 40:// 下
			S2 = false;
			break;
		case 97:// 1
			J2 = false;
			break;
		}
	}

	public void Move() {
		int testX = 0, testY = 0, i = 0, j = 0, flag1 = 0, flag2 = 0;
		if (myTank1.isLife() == true && myTank2.isLife() == true) {
			if (A == true && W == false && S == false && D == false) {
				myTank1.setDir(0);
				testX = myTank1.getX() - myTank1.getSpeed();
				testY = myTank1.getY();
				i = testX / 32;
				j = testY / 32;
				if (builds[i][j] == 0 || builds[i][j] == 2)
					flag1 = 1;
				if (builds[i][j + 1] == 0 || builds[i][j + 1] == 2)
					flag2 = 1;
				if (testY % 32 == 0)
					flag2 = 1;
				if (myTank1.getX() >= 0 && flag1 == 1 && flag2 == 1)
					myTank1.setX(testX);
			}
			if (A == false && W == true && S == false && D == false) {
				myTank1.setDir(1);
				testX = myTank1.getX();
				testY = myTank1.getY() - myTank1.getSpeed();
				i = testX / 32;
				j = testY / 32;
				if (builds[i][j] == 0 || builds[i][j] == 2)
					flag1 = 1;
				if (builds[i + 1][j] == 0 || builds[i + 1][j] == 2)
					flag2 = 1;
				if (testX % 32 == 0)
					flag2 = 1;
				if (myTank1.getY() >= 0 && flag1 == 1 && flag2 == 1)
					myTank1.setY(testY);
			}
			if (A == false && W == false && S == false && D == true) {
				myTank1.setDir(2);
				testX = myTank1.getX() + myTank1.getSpeed();
				testY = myTank1.getY();
				i = testX / 32;
				j = testY / 32;
				if (builds[i + 1][j] == 0 || builds[i + 1][j] == 2)
					flag1 = 1;
				if (builds[i + 1][j + 1] == 0 || builds[i + 1][j + 1] == 2)
					flag2 = 1;
				if (testY % 32 == 0)
					flag2 = 1;
				if (myTank1.getX() <= 528 && flag1 == 1 && flag2 == 1)
					myTank1.setX(testX);
			}
			if (A == false && W == false && S == true && D == false) {
				myTank1.setDir(3);
				testX = myTank1.getX();
				testY = myTank1.getY() + myTank1.getSpeed();
				i = testX / 32;
				j = testY / 32;
				if (builds[i][j + 1] == 0 || builds[i][j + 1] == 2)
					flag1 = 1;
				if (builds[i + 1][j + 1] == 0 || builds[i + 1][j + 1] == 2)
					flag2 = 1;
				if (testX % 32 == 0)
					flag2 = 1;
				if (myTank1.getY() <= 528 && flag1 == 1 && flag2 == 1)
					myTank1.setY(testY);
			}
			if (J == true) {
				myTank1.fire();
			}
		}

		flag1 = 0;
		flag2 = 0;
		if (myTank2.isLife() == true && myTank1.isLife() == true) {
			if (A2 == true && W2 == false && S2 == false && D2 == false) {
				myTank2.setDir(0);
				testX = myTank2.getX() - myTank2.getSpeed();
				testY = myTank2.getY();
				i = testX / 32;
				j = testY / 32;
				if (builds[i][j] == 0 || builds[i][j] == 2)
					flag1 = 1;
				if (builds[i][j + 1] == 0 || builds[i][j + 1] == 2)
					flag2 = 1;
				if (testY % 32 == 0)
					flag2 = 1;
				if (myTank2.getX() >= 0 && flag1 == 1 && flag2 == 1)
					myTank2.setX(testX);
			}
			if (A2 == false && W2 == true && S2 == false && D2 == false) {
				myTank2.setDir(1);
				testX = myTank2.getX();
				testY = myTank2.getY() - myTank2.getSpeed();
				i = testX / 32;
				j = testY / 32;
				if (builds[i][j] == 0 || builds[i][j] == 2)
					flag1 = 1;
				if (builds[i + 1][j] == 0 || builds[i + 1][j] == 2)
					flag2 = 1;
				if (testX % 32 == 0)
					flag2 = 1;
				if (myTank2.getY() >= 0 && flag1 == 1 && flag2 == 1)
					myTank2.setY(testY);
			}
			if (A2 == false && W2 == false && S2 == false && D2 == true) {
				myTank2.setDir(2);
				testX = myTank2.getX() + myTank2.getSpeed();
				testY = myTank2.getY();
				i = testX / 32;
				j = testY / 32;
				if (builds[i + 1][j] == 0 || builds[i + 1][j] == 2)
					flag1 = 1;
				if (builds[i + 1][j + 1] == 0 || builds[i + 1][j + 1] == 2)
					flag2 = 1;
				if (testY % 32 == 0)
					flag2 = 1;
				if (myTank2.getX() <= 528 && flag1 == 1 && flag2 == 1)
					myTank2.setX(testX);
			}
			if (A2 == false && W2 == false && S2 == true && D2 == false) {
				myTank2.setDir(3);
				testX = myTank2.getX();
				testY = myTank2.getY() + myTank2.getSpeed();
				i = testX / 32;
				j = testY / 32;
				if (builds[i][j + 1] == 0 || builds[i][j + 1] == 2)
					flag1 = 1;
				if (builds[i + 1][j + 1] == 0 || builds[i + 1][j + 1] == 2)
					flag2 = 1;
				if (testX % 32 == 0)
					flag2 = 1;
				if (myTank2.getY() <= 528 && flag1 == 1 && flag2 == 1)
					myTank2.setY(testY);
			}
			if (J2 == true) {
				myTank2.fire();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.repaint();
			if (this.Vic == true) {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (myTank1.isLife() == true)
					JOptionPane.showMessageDialog(null, "玩家1获胜！点击确定返回菜单栏");
				if (myTank2.isLife() == true)
					JOptionPane.showMessageDialog(null, "玩家2获胜！点击确定返回菜单栏");
				g1f.setVisible(false);
				sp.setVisible(true);
				break;
			}
		}
	}

}
