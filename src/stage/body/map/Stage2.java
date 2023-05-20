package stage.body.map;

import stage.body.buildings.Grass;
import stage.body.buildings.Ice;
import stage.body.buildings.Sea;
import stage.body.buildings.Steel;
import stage.body.buildings.Wall;
import stage.body.tank.Tank;

public class Stage2 extends Map {

	public Stage2() {
		
		Tank.tanks = new int[560][560];
		walls.add(new Wall(260 - 16 - 32, 520 - 32));
		walls.add(new Wall(260 - 16 - 32, 520 - 32 * 2));
		walls.add(new Wall(260 - 16, 520 - 32 * 2));
		walls.add(new Wall(260 + 16, 520 - 32));
		walls.add(new Wall(260 + 16, 520 - 32 * 2));
		walls.add(new Wall(260 - 16, 520 - 32 * 3));
		walls.add(new Wall(260 - 16 - 32, 520 - 32 * 3));
		walls.add(new Wall(260 - 16 - 32 * 2, 520 - 32 * 3));
		walls.add(new Wall(260 + 16, 520 - 32 * 3));
		walls.add(new Wall(260 + 16 + 32, 520 - 32 * 3));
		walls.add(new Wall(260 + 16 + 32 * 2, 520 - 32 * 3));
		steels.add(new Steel(260 - 16, 520 - 32 * 4));
		walls.add(new Wall(260 - 16 - 32, 520 - 32 * 4));
		walls.add(new Wall(260 + 16, 520 - 32 * 4));
		walls.add(new Wall(260 - 16, 520 - 32 * 5));
		walls.add(new Wall(260 - 16 - 32 * 3, 520 - 32 * 3));
		initHomeLocation();
		
		walls.add(new Wall(0, 80 + 32));
		walls.add(new Wall(0, 80 + 32 * 2));
		walls.add(new Wall(0 + 32, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 2, 80 + 32 * 2));
		walls.add(new Wall(0 + 32, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 2, 80 + 32 * 4));
		walls.add(new Wall(0, 80 + 32 * 5));
		walls.add(new Wall(0 + 32, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 2, 80 + 32 * 5));
		walls.add(new Wall(0, 80 + 32 * 6));
		walls.add(new Wall(0 + 32, 80 + 32 * 6));
		walls.add(new Wall(0 + 32 * 3, 80 + 32));
		walls.add(new Wall(0 + 32 * 3, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 3, 80 + 32 * 3));
		walls.add(new Wall(0 + 32 * 3, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 4, 80 - 32));
		walls.add(new Wall(0 + 32 * 4, 80));
		walls.add(new Wall(0 + 32 * 4, 80 + 32));
		walls.add(new Wall(0 + 32 * 4, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 4, 80 + 32 * 3));
		walls.add(new Wall(0 + 32 * 4, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 4, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 5, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 5, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 5, 80 + 32 * 3));
		walls.add(new Wall(0 + 32 * 5, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 5, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 5, 80 + 32 * 6));
		walls.add(new Wall(0 + 32 * 6, 80 - 32));
		walls.add(new Wall(0 + 32 * 6, 80));
		walls.add(new Wall(0 + 32 * 6, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 6, 80 + 32 * 3));
		walls.add(new Wall(0 + 32 * 6, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 6, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 7, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 7, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 7, 80 + 32 * 3));
		walls.add(new Wall(0 + 32 * 7, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 8, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 8, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 8, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 9, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 9, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 9, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 9, 80 + 32 * 6));
		walls.add(new Wall(0 + 32 * 10, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 10, 80 + 32 * 2));
		walls.add(new Wall(0 + 32 * 10, 80 + 32 * 4));
		walls.add(new Wall(0 + 32 * 10, 80 + 32 * 5));
		walls.add(new Wall(0 + 32 * 10, 80 + 32 * 6));
		walls.add(new Wall(0 + 32 * 12, 80 + 32 * 8));
		walls.add(new Wall(0 + 32 * 13, 80 + 32 * 8));
		walls.add(new Wall(0 + 32 * 14, 80 + 32 * 8));
		walls.add(new Wall(0 + 32 * 15, 80 + 32 * 8));
		walls.add(new Wall(0 + 32 * 12, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 13, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 14, 80 + 32 * 1));
		walls.add(new Wall(0 + 32 * 15, 80 + 32 * 1));
		initWallLocation();
		
		grasses.add(new Grass(0, 80));
		grasses.add(new Grass(0 + 32, 80 + 32));
		grasses.add(new Grass(0 + 32 * 2, 80 + 32));
		grasses.add(new Grass(0 + 32 * 3, 80));
		grasses.add(new Grass(0 + 32 * 5, 80));
		grasses.add(new Grass(0 + 32 * 7, 80));
		grasses.add(new Grass(0 + 32 * 8, 80 + 32));
		grasses.add(new Grass(0 + 32 * 9, 80 + 32));
		grasses.add(new Grass(0 + 32 * 10, 80));
		grasses.add(new Grass(0 + 32 * 11, 80 + 32));
		grasses.add(new Grass(0 + 32 * 11, 80 + 32 * 2));
		grasses.add(new Grass(0 + 32 * 11, 80 + 32 * 3));
		grasses.add(new Grass(0 + 32 * 12, 80 + 32 * 4));
		grasses.add(new Grass(0 + 32 * 12, 80 + 32 * 5));
		grasses.add(new Grass(0 + 32 * 11, 80 + 32 * 5));
		grasses.add(new Grass(0, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32, 80 + 32 * 8));
		grasses.add(new Grass(0 + 32 * 2, 80 + 32 * 8));
		grasses.add(new Grass(0 + 32 * 3, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32 * 4, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32 * 5, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32 * 6, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32 * 7, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32 * 8, 80 + 32 * 8));
		grasses.add(new Grass(0 + 32 * 9, 80 + 32 * 8));
		grasses.add(new Grass(0 + 32 * 10, 80 + 32 * 7));
		grasses.add(new Grass(0 + 32 * 11, 80 + 32 * 8));
		grasses.add(new Grass(0 + 32 * 12, 80 + 32 * 7));
		
		
		seas.add(new Sea(0, 80 + 32 * 3));
		seas.add(new Sea(0, 80 + 32 * 4));
		seas.add(new Sea(0 + 32, 80 + 32 * 3));
		seas.add(new Sea(0 + 32 * 2, 80 + 32 * 3));
		seas.add(new Sea(0 + 32 * 8, 80 + 32 * 3));
		seas.add(new Sea(0 + 32 * 9, 80 + 32 * 3));
		seas.add(new Sea(0 + 32 * 10, 80 + 32 * 3));
		seas.add(new Sea(0 + 32 * 11, 80 + 32 * 4));
		seas.add(new Sea(0 + 32 * 11, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 11, 80 + 32 * 7));
		seas.add(new Sea(0 + 32 * 12, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 3, 80 + 32 * 5));
		seas.add(new Sea(0 + 32 * 2, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 3, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 4, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 1, 80 + 32 * 7));
		seas.add(new Sea(0 + 32 * 2, 80 + 32 * 7));
		seas.add(new Sea(0 + 32 * 7, 80 + 32 * 5));
		seas.add(new Sea(0 + 32 * 6, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 7, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 8, 80 + 32 * 6));
		seas.add(new Sea(0 + 32 * 8, 80 + 32 * 7));
		seas.add(new Sea(0 + 32 * 9, 80 + 32 * 7));
		initSeaLocation();
		
		steels.add(new Steel(0 + 32 * 4, 80 + 32 * 2));
		steels.add(new Steel(0 + 32 * 6, 80 + 32 * 2));
		initSteelLocation();
		
		ices.add(new Ice(0 + 32 * 12, 80 + 32 * 2));
		ices.add(new Ice(0 + 32 * 12, 80 + 32 * 3));
		ices.add(new Ice(0 + 32 * 13, 80 + 32 * 2));
		ices.add(new Ice(0 + 32 * 14, 80 + 32 * 2));
		ices.add(new Ice(0 + 32 * 15, 80 + 32 * 2));
		ices.add(new Ice(0 + 32 * 13, 80 + 32 * 3));
		ices.add(new Ice(0 + 32 * 14, 80 + 32 * 3));
		ices.add(new Ice(0 + 32 * 15, 80 + 32 * 3));
		ices.add(new Ice(0 + 32 * 13, 80 + 32 * 4));
		ices.add(new Ice(0 + 32 * 14, 80 + 32 * 4));
		ices.add(new Ice(0 + 32 * 15, 80 + 32 * 4));
		ices.add(new Ice(0 + 32 * 13, 80 + 32 * 5));
		ices.add(new Ice(0 + 32 * 14, 80 + 32 * 5));
		ices.add(new Ice(0 + 32 * 15, 80 + 32 * 5));
		ices.add(new Ice(0 + 32 * 13, 80 + 32 * 6));
		ices.add(new Ice(0 + 32 * 14, 80 + 32 * 6));
		ices.add(new Ice(0 + 32 * 15, 80 + 32 * 6));
		ices.add(new Ice(0 + 32 * 13, 80 + 32 * 7));
		ices.add(new Ice(0 + 32 * 14, 80 + 32 * 7));
		ices.add(new Ice(0 + 32 * 15, 80 + 32 * 7));
	}
}