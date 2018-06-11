import oop.ex2.GameGUI;
import java.awt.*;

/**
 * This is the implementation of the human controlled ship, including user input
 */
public class SpaceShipHumanControlled extends SpaceShip {


	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this human controlled ship, it will check user input for it's actions.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		GameGUI gui = game.getGUI();
		if(gui.isTeleportPressed())
			teleport();
		ssp.move(gui.isUpPressed(), (gui.isLeftPressed() && !gui.isRightPressed()) ? 1 :
				(gui.isRightPressed() && !gui.isLeftPressed()) ? -1 : 0);
		shield = false;
		if(gui.isShieldsPressed())
			shieldOn();
		if(gui.isShotPressed())
			fire(game);
		updateCooldownEnergy();
	}

	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round. for this human controlled ship, it will return the image
	 * of human ship (not AI ship).
	 *
	 * @return the image of this ship.
	 */
	@Override
	public Image getImage() {
		return shield ? GameGUI.SPACESHIP_IMAGE_SHIELD : GameGUI.SPACESHIP_IMAGE;
	}
}
