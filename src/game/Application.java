package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);

		List<String> town = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"..........................................................+.....................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"...................................................................+............",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		".........................................+.+....................................",
		"................................................................................",
		"................................................................................",
		"................................................................................");
		GameMap townMap = new GameMap(groundFactory, town);
		world.addGameMap(townMap);
		
		Actor player = new Player("Player", '@', 1000);
		world.addPlayer(player, gameMap.at(38, 15));
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}

		// Place some random farmers
		String[] farmers = {"Farmer Ada", "Farmer Bob"};
		for (String name: farmers){
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Farmer(name));
		}
		gameMap.at(10, 15).addActor(new Farmer("Farmer Chris"));
		gameMap.at(65, 5).addActor(new Farmer("Farmer Dora"));

		// place a simple weapon
		gameMap.at(60, 20).addItem(new Plank());

		// place a vehicle in compound map to move to town
		Vehicle vehicle = new Vehicle("Car", 'v');
		vehicle.addAction(new MoveActorAction(townMap.at(7,2), "to Town"));
		gameMap.at(39, 15).addItem(vehicle);

		// place a vehicle in town to move to compound map
		Vehicle vehicleInTown = new Vehicle("Car", 'v');
		townMap.at(7, 2).addItem(vehicleInTown);
		vehicleInTown.addAction(new MoveActorAction(gameMap.at(39, 15), "to Compound Map"));

		// place boxes of ammunition in town and compound map
		Item ammo = new PortableItem("box of ammunition", '*');
		townMap.at(9,2).addItem(ammo);
		gameMap.at(39,16).addItem(ammo);

		// place shotgun in town
		townMap.at(8,2).addItem(new Shotgun(ammo));
		
		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(60, 20).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));
		try {
			world.run();
		}
		catch (IllegalArgumentException e){
			System.out.println("Player cannot move to other map in this turn. Please try again in the next time.");
		}
	}
}
