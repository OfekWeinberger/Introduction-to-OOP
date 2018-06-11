/**
 * This  class is responsible of creating the ships for the game
 */
public class SpaceShipFactory {

	/**
	 * The human-controlled spaceship type
	 */
	private static final String HUMAN_CONTROLLED_SHIP = "h";

	/**
	 * The runner spaceship type
	 */
	private static final String RUNNER_SHIP = "r";

	/**
	 * The aggressive spaceship type
	 */
	private static final String AGGRESSIVE_SHIP = "a";

	/**
	 * The basher spaceship type
	 */
	private static final String BASHER_SHIP = "b";

	/**
	 * The special ship you designed yourself
	 */
	private static final String SPECIAL_SHIP = "s";

	/**
	 * The drunkard ship you designed yourself
	 */
	private static final String DRUNKARD_SHIP = "d";

	/**
	 * This method will create ships according to given arguments
	 * @param args: an array of arguments
	 * @return an array of ships
	 */
	public static SpaceShip[] createSpaceShips(String[] args) {
		SpaceShip[] shipArray = new SpaceShip[args.length];
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case HUMAN_CONTROLLED_SHIP:
					shipArray[i] = new SpaceShipHumanControlled();
					break;
				case RUNNER_SHIP:
					shipArray[i] = new SpaceShipRunner();
					break;
				case BASHER_SHIP:
					shipArray[i] = new SpaceShipBasher();
					break;
				case AGGRESSIVE_SHIP:
					shipArray[i] = new SpaceShipAggressive();
					break;
				case DRUNKARD_SHIP:
					shipArray[i] = new SpaceShipDrunkard();
					break;
				case SPECIAL_SHIP:
					shipArray[i] = new SpaceShipSpecial();
					break;
			}
		}
		return shipArray;
	}
}
