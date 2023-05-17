package solo;

import java.util.Vector;

public class MyTank extends Tank implements Runnable{
	Vector<Bullet> bulletVector = new Vector<Bullet>();
	
	public MyTank(int x, int y, int dir, int type) {
		super(x, y, dir, type);
		// TODO Auto-generated constructor stub
	}
	
	public void fire() {
		Bullet bullet = null;
		if(bulletVector.size()<1) {
			switch (this.getDir()) {
			case 0:
				bullet = new Bullet(this.getX(),this.getY()+12,0);
				break;
			case 1:
				bullet = new Bullet(this.getX()+12,this.getY(),1);
				break;
			case 2:
				bullet = new Bullet(this.getX()+24,this.getY()+12,2);
				break;
			case 3:
				bullet = new Bullet(this.getX()+12,this.getY()+24,3);
				break;
			}
			this.setBullet(bullet);
			bulletVector.add(bullet);
			Thread thread = new Thread(bullet);
			thread.start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
