package solo;

public class Tank {
	private int x;
	private int y;
	private int dir;
	private int speed;
	private boolean life=true;
	private int type;
	private Bullet bullet;
	
	public Tank(int x, int y,int dir, int type) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.type = type;
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
