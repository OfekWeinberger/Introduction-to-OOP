import oop.ex2.GameGUI;


import java.awt.*;

/**
 * This class implements the appearance all AI ships have in common.
 */
public abstract class SpaceShipComputerControlled extends SpaceShip {
	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round. In this AI ship, it will display enemy spaceship.
	 *
	 * @return the image of this ship.
	 */
	@Override
	public Image getImage() {
		return shield ? GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD : GameGUI.ENEMY_SPACESHIP_IMAGE;
	}
}
