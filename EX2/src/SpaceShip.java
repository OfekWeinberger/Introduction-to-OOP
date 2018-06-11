import oop.ex2.SpaceShipPhysics;

import java.awt.*;

/**
 * The abstract ship class, implements everything every ship will have implemented in identical way
 *
 * @author oop
 */
public abstract class SpaceShip {

	/**
	 * The initial health for every ship
	 */
	private static final int INITIAL_HEALTH = 22;
	/**
	 * The initial max energy for every ship
	 */
	private static final int INITIAL_MAX_ENERGY = 210;
	/**
	 * The initial current energy for every ship
	 */
	private static final int INITIAL_CURRENT_ENERGY = 190;
	/**
	 * Bashing energy bonus
	 */
	private static final int BASHING_BONUS = 18;
	/**
	 * Energy fee if you get hit
	 */
	private static final int HIT_FEE = -10;
	/**
	 * Energy fee if you fire
	 */
	private static final int FIRE_FEE = 19;
	/**
	 * Energy fee if you shield, one per round
	 */
	private static final int SHIELD_FEE = -3;
	/**
	 * Energy fee if you teleport
	 */
	private static final int TELEPORT_FEE = -140;
	/**
	 * Amount of rounds to wait before next shot (7)
	 */
	private static final int COOLDOWN_FEE = -7;
	/**
	 * The ship's physics model
	 */
	protected SpaceShipPhysics ssp;
	/**
	 * The ship's maximum amount of stored energy
	 */
	protected int maxEnergy;
	/**
	 * The ship's current energy
	 */
	protected int currentEnergy;
	/**
	 * Defines if shield is on
	 */
	protected boolean shield;
	/**
	 * The current cooldown, if cooldown = 0, it is cff
	 */
	protected int cooldown;
	/**
	 * The ship's current health
	 */
	protected int health;

	/**
	 * Creates new generic ship
	 */
	SpaceShip() {
		this.ssp = new SpaceShipPhysics();
		this.maxEnergy = INITIAL_MAX_ENERGY;
		this.currentEnergy = INITIAL_CURRENT_ENERGY;
		this.health = INITIAL_HEALTH;
		this.shield = false;
		this.cooldown = 0;
	}

	/**
	 * Does the actions of this ship for this round.
	 * This is called once per round by the SpaceWars game driver.
	 * For this generic driver the doAction will do nothing.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	public abstract void doAction(SpaceWars game);

	/**
	 * This method updates the cooldown & energy of the ship. to be called once a-round.
	 */
	protected void updateCooldownEnergy() {
		currentEnergy++;
		if (currentEnergy > maxEnergy)
			currentEnergy = maxEnergy;
		cooldown++;
		if (cooldown > 0)
			cooldown = 0;
	}

	/**
	 * This method is called every time a collision with this ship occurs
	 */
	public void collidedWithAnotherShip() {
		if (!shield) {
			health--;
			maxEnergy += HIT_FEE;
			maxEnergy = Math.max(0, maxEnergy);
			currentEnergy = Math.min(maxEnergy, currentEnergy);
		} else {
			maxEnergy += BASHING_BONUS;
			currentEnergy += BASHING_BONUS;
		}
	}

	/**
	 * This method is called whenever a ship has died. It resets the ship's
	 * attributes, and starts it at a new random position.
	 */
	public void reset() {
		ssp = new SpaceShipPhysics();
		maxEnergy = INITIAL_MAX_ENERGY;
		currentEnergy = INITIAL_CURRENT_ENERGY;
		health = INITIAL_HEALTH;
		shield = false;
		cooldown = 0;
	}

	/**
	 * Checks if this ship is dead.
	 *
	 * @return true if the ship is dead. false otherwise.
	 */
	public boolean isDead() {
		return health <= 0;
	}

	/**
	 * Gets the physics object that controls this ship.
	 *
	 * @return the physics object that controls the ship.
	 */
	public SpaceShipPhysics getPhysics() {
		return ssp;
	}

	/**
	 * This method is called by the SpaceWars game object when ever this ship
	 * gets hit by a shot.
	 */
	public void gotHit() {
		if (!shield) {
			health--;
			maxEnergy += HIT_FEE;
			maxEnergy = Math.max(0, maxEnergy);
			currentEnergy = Math.min(maxEnergy, currentEnergy);
		}
	}

	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round.
	 *
	 * @return the image of this ship.
	 */
	public abstract Image getImage();

	/**
	 * Attempts to fire a shot.
	 *
	 * @param game the game object.
	 */
	public void fire(SpaceWars game) {
		if (cooldown == 0 && currentEnergy >= Math.abs(FIRE_FEE)) {
			currentEnergy += FIRE_FEE;
			game.addShot(ssp);
			cooldown = COOLDOWN_FEE;
		}
	}

	/**
	 * Attempts to turn on the shield.
	 */
	public void shieldOn() {
		if (currentEnergy >= Math.abs(SHIELD_FEE)) {
			currentEnergy += SHIELD_FEE;
			shield = true;
		}
	}

	/**
	 * Attempts to teleport.
	 */
	public void teleport() {
		if (currentEnergy >= Math.abs(TELEPORT_FEE)) {
			currentEnergy += TELEPORT_FEE;
			ssp = new SpaceShipPhysics();
		}
	}

}
