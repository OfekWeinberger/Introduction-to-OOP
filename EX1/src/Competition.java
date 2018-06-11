import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds.
 * It also keeps track of the number of victories of each player.
 */
public class Competition {

	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments.
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 * player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 *
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// parse program arguments:
		int p1Type = parsePlayer1Type(args);
		int p2Type = parsePlayer2Type(args);
		int numGames = parseNumberOfGames(args);
		// create both player objects:
		Player player1 = new Player(p1Type, 1, in);
		Player player2 = new Player(p2Type, 2, in);
		// raise correct flag for the display message, if one or more of the players is human - display:
		boolean isThereHuman = player1.getPlayerType() == Player.HUMAN ||
				player2.getPlayerType() == Player.HUMAN;
		// create the competition object:
		Competition c = new Competition(player1, player2, isThereHuman);
		// play:
		c.playMultipleRounds(numGames);
		// close scanner as required:
		in.close();
	}

	/**
	 *  represents player 1
	 */
	private Player p1;
	/**
	 *  represents player 2
	 */
	private Player p2;
	/**
	 *  decides whether to display console messages
	 */
	private boolean displayMessage;
	/**
	 *  keeps score of player 1
	 */
	private int score1;
	/**
	 *  keeps score of player 2
	 */
	private int score2;

	/**
	 * Receives two Player objects, representing the two competing opponents,
	 * and a flag determining whether messages should be displayed.
	 *
	 * @param player1        The Player objects representing the first player.
	 * @param player2        The Player objects representing the second player.
	 * @param displayMessage a flag indicating whether game play messages should be printed to the console.
	 */
	public Competition(Player player1, Player player2, boolean displayMessage) {
		this.p1 = player1;
		this.p2 = player2;
		this.displayMessage = displayMessage;
		this.score1 = 0;
		this.score2 = 0;
	}


	/*
	 * Returns the integer representing the type of player 1; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer1Type(String[] args) {
		try {
			return Integer.parseInt(args[0]);
		} catch (Exception E) {
			return -1;
		}
	}

	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer2Type(String[] args) {
		try {
			return Integer.parseInt(args[1]);
		} catch (Exception E) {
			return -1;
		}
	}

	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args) {
		try {
			return Integer.parseInt(args[2]);
		} catch (Exception E) {
			return -1;
		}
	}

	/**
	 * Run the game for the given number of rounds.
	 *
	 * @param numberOfRounds: number of rounds to play.
	 */
	public void playMultipleRounds(int numberOfRounds) {
		System.out.println("Starting a Nim competition of " + numberOfRounds + " rounds between a "
				+ p1.getTypeName() + " player and a " + p2.getTypeName() + " player.");
		// iterate over all rounds:
		for (int i = 0; i < numberOfRounds; i++) {
			// create new empty board for a new game:
			Board gameBoard = new Board();
			if (displayMessage)
				System.out.println("Welcome to the sticks game!");
			// let first player start:
			Player currentPlayer = p1;
			// keep playing while the board isn't full:
			while (gameBoard.getNumberOfUnmarkedSticks() > 0) {
				if (displayMessage)
					System.out.println("Player " + currentPlayer.getPlayerId() + ", it is now your turn!");
				// produce the move from the current player:
				Move currentMove = currentPlayer.produceMove(gameBoard);
				// try to make the move:
				int markingAttemptResult = gameBoard.markStickSequence(currentMove);
				// get a legal move:
				while (markingAttemptResult != 0) {
					if (displayMessage)
						System.out.println("Invalid move. Enter another:");
					currentMove = currentPlayer.produceMove(gameBoard);
					markingAttemptResult = gameBoard.markStickSequence(currentMove);
				}
				if (displayMessage)
					System.out.println("Player " + currentPlayer.getPlayerId()
							+ " made the move: " + currentMove);
				// this always switch player in the end of a turn:
				currentPlayer = currentPlayer == p1 ? p2 : p1;
			}
			// because of the switch in player turns in the end of the loop, winner is the current player:
			if (currentPlayer == p1)
				score1++;
			else
				score2++;
			if (displayMessage)
				System.out.println("Player " + currentPlayer.getPlayerId() + " won!");
		}
		// print final results for the competition:
		System.out.println("The results are " + score1 + ":" + score2);
	}

	/**
	 * If playerPosition = 1, the results of the first player is returned.
	 * If playerPosition = 2, the result of the second player is returned.
	 * If playerPosition equals neither, -1 is returned.
	 *
	 * @param playerPosition: position of the player
	 * @return the number of victories of a player.
	 */
	public int getPlayerScore(int playerPosition) {
		if (playerPosition == 1)
			return score1;
		if (playerPosition == 2)
			return score2;
		return -1;
	}
}
