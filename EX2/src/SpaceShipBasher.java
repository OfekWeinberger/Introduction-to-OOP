import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;

import java.awt.*;

/**
 * The basher class implementation
 */
public class SpaceShipBasher extends SpaceShipComputerControlled {

	private static final double SHIELD_DISTANCE = 0.19;
	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this basher ship, it will try to bash into other players.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		SpaceShipPhysics closestPhys = game.getClosestShipTo(this).ssp;
		ssp.move(true, ssp.angleTo(closestPhys) < 0 ? -1 : 1);
		shield = false;
		if(ssp.distanceFrom(closestPhys) < SHIELD_DISTANCE)
			shieldOn();
		updateCooldownEnergy();
	}
}
