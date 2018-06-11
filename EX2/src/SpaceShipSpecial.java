import oop.ex2.SpaceShipPhysics;

public class SpaceShipSpecial extends SpaceShipComputerControlled {

	private static final double MIN_FIRE_ANGLE = 0.21;
	private static final double SHIELD_DISTANCE = 0.19;
	private static final double SHIELD_HEALTH_THRESHOLD = 5;

	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this special ship, it will be some sort of combination between the
	 * aggressive and the basher. it will pursue the closest ship, and try to shoot it,
	 * however, when it is close to a ship it will turn on shields.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		SpaceShipPhysics closestPhys = game.getClosestShipTo(this).ssp;
		ssp.move(true, ssp.angleTo(closestPhys) < 0 ? -1 : 1);
		shield = false;
		if(health < SHIELD_HEALTH_THRESHOLD || ssp.distanceFrom(closestPhys) < SHIELD_DISTANCE)
			shieldOn();
		if(Math.abs(ssp.angleTo(closestPhys)) < MIN_FIRE_ANGLE)
			fire(game);
		updateCooldownEnergy();
	}
}
