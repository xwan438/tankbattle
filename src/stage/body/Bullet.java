package stage.body;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import stage.body.buildings.Wall;
import stage.body.tank.Tank;

public class Bullet implements Runnable {

	private int x;
	private int y;
	private int type;
	// 0 : stand for player
	// 1 : stand for enemy
	private int direction;
	private int speed = 12;
	private Boolean Alive;
	private Boolean hitBuilding;
	private Boolean hitSteel;
	
	public Bullet(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.Alive = true;
		this.type = -1;
		this.hitBuilding = false;
		this.hitSteel = false;
		this.direction = direction;
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			try {
				Thread.sleep(50);
			} catch(Exception e) {
				e.printStackTrace();
			}
			int beginX = x;
			int beginY = y;
			switch (direction) {
			case 0:
				y -= speed;
				break;
			case 1:
				x -= speed;
				break;
			case 2:
				y += speed;
				break;
			case 3:
				x += speed;
				break;
			default:
				break;
			}
			if(this.Alive == false) {
				return;
			}
			if(x < 0 || y < 0 || x > 520 - 8 || y > 520 - 8) {
				if(this.getType() == 0) {
					try {
						AudioClip bgm = Applet.newAudioClip(new File("bgm//hitLimit.wav").toURL());
						bgm.play();
					} catch (MalformedURLException e2) {
						e2.printStackTrace();
					}
				}
				this.Alive = false;
				return;
			}
			switch (this.getDirection()) {
			case 0:
				if((Tank.tanks[x][y] == 2 || Tank.tanks[x + 7][y] == 2) ||
						(Tank.tanks[x][y] == 3 || Tank.tanks[x + 7][y] == 3) ||
						(Tank.tanks[x][y] == 4 || Tank.tanks[x + 7][y] == 4)) {
					this.Alive = false;
					if(Tank.tanks[x][y] == 2 || Tank.tanks[x + 7][y] == 2) {

						this.hitBuilding = true;
						this.hitSteel = true;
//						this.x = beginX;
//						this.y = beginY;
					}
					else if(Tank.tanks[x][y] == 3 || Tank.tanks[x + 7][y] == 3) {
						StageBody.homeAlive = false;
					}
					else if(Tank.tanks[x][y] == 4 || Tank.tanks[x + 7][y] == 4) {
						for(int from = 0; from < StageBody.walls.size(); from++) {
							Wall wall = (Wall)StageBody.walls.get(from);
							if(wall.getAlive()) {
								if(wall.inside(x, y) || wall.inside(x + 7, y)) {
									wall.setAlive(false);
									// 为了方便画墙的爆炸
									this.setX(wall.getX());
									this.setY(wall.getY());
								}
							}
						}
						this.hitBuilding = true;
					}
				}
				break;

			case 1:
				if((Tank.tanks[x][y] == 2 || Tank.tanks[x][y + 7] == 2) ||
						(Tank.tanks[x][y] == 3 || Tank.tanks[x][y + 7] == 3) ||
						(Tank.tanks[x][y] == 4 || Tank.tanks[x][y + 7] == 4)) {
					this.Alive = false;
					if(Tank.tanks[x][y] == 2 || Tank.tanks[x][y + 7] == 2) {

						this.hitBuilding = true;
						this.hitSteel = true;
//						this.x = beginX;
//						this.y = beginY;
					}
					else if(Tank.tanks[x][y] == 3 || Tank.tanks[x][y + 7] == 3) {
						StageBody.homeAlive = false;
					}
					else if(Tank.tanks[x][y] == 4 || Tank.tanks[x][y + 7] == 4) {
						for(int from = 0; from < StageBody.walls.size(); from++) {
							Wall wall = (Wall)StageBody.walls.get(from);
							if(wall.getAlive()) {
								if(wall.inside(x, y) || wall.inside(x, y + 7)) {
									wall.setAlive(false);
									// 为了方便画墙的爆炸
									this.setX(wall.getX());
									this.setY(wall.getY());
								}
							}
						}
						this.hitBuilding = true;
					}
				}
				break;
				
			case 2:
				if((Tank.tanks[x][y + 7] == 2 || Tank.tanks[x + 7][y + 7] == 2) ||
						(Tank.tanks[x][y + 7] == 3 || Tank.tanks[x + 7][y + 7] == 3) ||
						(Tank.tanks[x][y + 7] == 4 || Tank.tanks[x + 7][y + 7] == 4)) {
					this.Alive = false;
					if(Tank.tanks[x][y + 7] == 2 || Tank.tanks[x + 7][y + 7] == 2) {

						this.hitBuilding = true;
						this.hitSteel = true;
//						this.x = beginX;
//						this.y = beginY;
					}
					else if(Tank.tanks[x][y + 7] == 3 || Tank.tanks[x + 7][y + 7] == 3) {
						StageBody.homeAlive = false;
					}
					else if(Tank.tanks[x][y + 7] == 4 || Tank.tanks[x + 7][y + 7] == 4) {
						for(int from = 0; from < StageBody.walls.size(); from++) {
							Wall wall = (Wall)StageBody.walls.get(from);
							if(wall.getAlive()) {
								if(wall.inside(x, y + 7) || wall.inside(x + 7, y + 7)) {
									wall.setAlive(false);
									// 为了方便画墙的爆炸
									this.setX(wall.getX());
									this.setY(wall.getY());
								}
							}
						}
						this.hitBuilding = true;
					}
				}
				break;
				
				
			case 3:
				
				if((Tank.tanks[x + 7][y] == 2 || Tank.tanks[x + 7][y + 7] == 2) ||
						(Tank.tanks[x + 7][y] == 3 || Tank.tanks[x + 7][y + 7] == 3) ||
						(Tank.tanks[x + 7][y] == 4 || Tank.tanks[x + 7][y + 7] == 4)) {
					this.Alive = false;
					if(Tank.tanks[x + 7][y] == 2 || Tank.tanks[x + 7][y + 7] == 2) {

						this.hitBuilding = true;
						this.hitSteel = true;
//						this.x = beginX;
//						this.y = beginY;
					}
					else if(Tank.tanks[x + 7][y] == 3 || Tank.tanks[x + 7][y + 7] == 3) {
						StageBody.homeAlive = false;
					}
					else if(Tank.tanks[x + 7][y] == 4 || Tank.tanks[x + 7][y + 7] == 4) {
						for(int from = 0; from < StageBody.walls.size(); from++) {
							Wall wall = (Wall)StageBody.walls.get(from);
							if(wall.getAlive()) {
								if(wall.inside(x + 7, y) || wall.inside(x + 7, y + 7)) {
									wall.setAlive(false);
									// 为了方便画墙的爆炸
									this.setX(wall.getX());
									this.setY(wall.getY());
								}
							}
						}
						this.hitBuilding = true;
					}
				}
				break;
				
			default:
				break;
			}
			
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	
	
	public Boolean getHitSteel() {
		return hitSteel;
	}

	public void setHitSteel(Boolean hitSteel) {
		this.hitSteel = hitSteel;
	}

	public Boolean isAlive() {
		return Alive;
	}

	public void setAlive(Boolean alive) {
		Alive = alive;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Boolean isHitBuilding() {
		return hitBuilding;
	}

	public void setHitBuilding(Boolean hitBuilding) {
		this.hitBuilding = hitBuilding;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}
