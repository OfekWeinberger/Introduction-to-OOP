import oop.ex2.SpaceShipPhysics;

import java.util.Random;

/**
 * My own drunkard ship implementation
 */
public class SpaceShipDrunkard extends SpaceShipComputerControlled {

	private static final int SHIELD_CHANCE = 5;
	private static final int FIRE_CHANCE = 7;
	private static final int TELEPORT_CHANCE = 50;
	private static final int CHANGE_DIRECTION_CHANCE = 10;

	private Random rnd;
	private int lastTurn;

	public SpaceShipDrunkard() {
		super();
		this.rnd = new Random();
		this.lastTurn = 0;
	}

	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this drunkard ship, it will mostly act like a drunk, spinning around randomly
	 * doing teleports, shields and firing like crazy. it will also always accelerate.
	 *
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		if(rnd.nextInt(TELEPORT_CHANCE) == 0)
			teleport();
		int turn = rnd.nextInt(CHANGE_DIRECTION_CHANCE) == 0 ? rnd.nextInt(2) - 1 : lastTurn;
		lastTurn = turn;
		ssp.move(true, turn);
		shield = false;
		if (rnd.nextInt(SHIELD_CHANCE) == 0)
			shieldOn();
		if(rnd.nextInt(FIRE_CHANCE) == 0)
			fire(game);
		updateCooldownEnergy();
	}
}
