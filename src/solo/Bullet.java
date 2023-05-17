package solo;

public class Bullet implements Runnable {
	private int x;
	private int y;
	private int speed=8;
	private int dir;
	private boolean life=true;
	private boolean boom=false;

	public Bullet(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			switch (this.getDir()) {
			case 0:
				this.setX(this.getX()-this.getSpeed());
				break;
			case 1:
				this.setY(this.getY()-this.getSpeed());
				break;
			case 2:
				this.setX(this.getX()+this.getSpeed());
				break;
			case 3:
				this.setY(this.getY()+this.getSpeed());
				break;
			}
			if(this.getX()<0||this.getY()<0||this.getX()>528||this.getY()>528) {
				this.setLife(false);
				break;
			}
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(this.isLife()==false)
				break;
		}
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


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getDir() {
		return dir;
	}


	public void setDir(int dir) {
		this.dir = dir;
	}


	public boolean isLife() {
		return life;
	}


	public void setLife(boolean life) {
		this.life = life;
	}
	
	
	public boolean isBoom() {
		return boom;
	}
	
	
	public void setBoom(boolean boom) {
		this.boom = boom;
	}


}
