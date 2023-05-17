package solo;
public class Builds {
	int [][] builds = new int [16][16];
	public Builds() {
		// 铁的布局
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (i == 0 || i == 15 || j == 0 || j == 15) {
					builds[i][j] = 1;
				}
			}
		}
		builds[7][7] = 1;
		builds[8][7] = 1;
		// 草的布局
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 4; j++) {
				builds[i][j] = 2;
			}
		}
		builds[7][1] = 2;
		builds[8][1] = 2;
		for (int i = 9; i < 15; i++) {
			for (int j = 1; j < 4; j++) {
				builds[i][j] = 2;
			}
		}
		// 海的布局
		for (int i = 7; i < 9; i++) {
			for (int j = 2; j < 6; j++) {
				builds[i][j] = 3;
			}
		}
		for (int i = 7; i < 9; i++) {
			for (int j = 9; j < 13; j++) {
				builds[i][j] = 3;
			}
		}
		// 墙的布局
		builds[7][6] = 4;
		builds[8][6] = 4;
		builds[7][8] = 4;
		builds[8][8] = 4;
		builds[7][13] = 4;
		builds[8][13] = 4;
		builds[7][14] = 4;
		builds[8][14] = 4;

		builds[1][6] = 4;
		builds[1][8] = 4;
		builds[2][7] = 4;

		builds[14][6] = 4;
		builds[14][8] = 4;
		builds[13][7] = 4;
	}
	public int[][] getBuilds() {
		return builds;
	}
	public void setBuilds(int[][] builds) {
		this.builds = builds;
	}
	
}
