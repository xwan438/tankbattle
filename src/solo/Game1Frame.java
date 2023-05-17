package solo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Game1Frame extends JFrame {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	public Game1Frame(JFrame sp){
		this.setTitle("µÚÒ»¹Ø");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(528, 548);		//x=16*32+16,y=20+16*32+16
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2
				, screenSize.height / 2 - this.getWidth() / 2);
		this.setResizable(false);
		Container container = this.getContentPane();
		container.setBackground(Color.BLACK);
		Image img = Toolkit.getDefaultToolkit().getImage("img\\home.png");
		this.setIconImage(img);
		Game1Panel gp = new Game1Panel(this,sp);
		Thread thread = new Thread(gp);
		thread.start();
		container.add(gp);
		this.addKeyListener(gp);
		this.setVisible(true);
	}
	
}
