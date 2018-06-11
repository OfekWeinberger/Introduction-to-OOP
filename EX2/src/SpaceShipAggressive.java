import oop.ex2.SpaceShipPhysics;

/**
 * The aggressive ship implementation, will run after the closest ship to it and shoot at it.
 */
public class SpaceShipAggressive extends SpaceShipComputerControlled {

	private static final double MIN_FIRE_ANGLE = 0.21;

	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this generic driver the doAction will do nothing.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		SpaceShipPhysics closestPhys = game.getClosestShipTo(this).ssp;
		ssp.move(true, ssp.angleTo(closestPhys) < 0 ? -1 : 1);

		if (Math.abs(ssp.angleTo(closestPhys)) < MIN_FIRE_ANGLE)
			fire(game);
		updateCooldownEnergy();
	}
}
