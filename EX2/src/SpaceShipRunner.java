import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;

import java.awt.*;

/**
 *  The implementation of the runner algorithm AI ship
 */
public class SpaceShipRunner extends SpaceShipComputerControlled {

	private static final double THREAT_DISTANCE = 0.25;
	private static final double THREAT_ANGLE = 0.23;

	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this runner ship, it will try to run away from everyone.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		SpaceShipPhysics closestPhys = game.getClosestShipTo(this).ssp;
		if(ssp.distanceFrom(closestPhys) < THREAT_DISTANCE &&
				Math.abs(ssp.angleTo(closestPhys)) < THREAT_ANGLE)
			teleport();
		// update closest ship
		closestPhys = game.getClosestShipTo(this).ssp;
		ssp.move(true, ssp.angleTo(closestPhys) < 0 ? 1 : -1);
		updateCooldownEnergy();
	}
}
