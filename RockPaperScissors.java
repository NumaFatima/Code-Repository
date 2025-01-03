import java.util.Scanner;

/**
 *
 * A program to play Rock Paper Scissors
 *
 * Name: Numa Fatima Begum 
 * email address: NumaFatima@utexas.edu 
 */

public class RockPaperScissors {
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;
	
	/*
	 * A program to allow a human player to play rock - paper - scissors against the
	 * computer. If args.length != 0 then we assume the first element of args can be
	 * converted to an int
	 */
	
	/*
	 * Main method that calls method intro to start printing the code.
	 * Method also creates a scanner called keyboard and a random player called computerPlayer.
	 * Closes the keyboard when everything is done printing.
	 */
	public static void main(String[] args) {
		RandomPlayer computerPlayer = buildRandomPlayer(args);
		Scanner keyboard = new Scanner(System.in);
		intro(keyboard, computerPlayer);
		keyboard.close();
	}
	
	/*
	 * Build the random player. If args is length 0 then the default RandomPlayer is
	 * built that follows a predictable sequence. If args.length > 0 then we assume
	 * we can convert the first element to an int and build the RandomPlayer with
	 * that initial value.
	 */
	public static RandomPlayer buildRandomPlayer(String[] args) {
		if (args.length == 0) {
			return new RandomPlayer();
		} else {
			int seed = Integer.parseInt(args[0]);
			return new RandomPlayer(seed);
		}
	}
	
	/* 
	 * Method that converts the numbers the user and computer pick to strings.
	 * Parameter number is the number choice of either
	 * the computer or the user. The method returns the string to userResponse or 
	 * computerResponse in the while loop. 
	 */
	public static String numberToString(int number) {
		if (number == ROCK) {
			String one = "ROCK";
			return one;
		}
		if (number == PAPER) {
			String two = "PAPER";
			return two;
		}
		if (number == SCISSORS) {
			String three = "SCISSORS";
			return three;
		}
		return "";
	}
	
	/* Method that prints all the introductory sentences. The parameter keyboard
	 * allows for user input and parameter computerPlayer allows for the computer input.
	 */
	public static void intro(Scanner keyboard, RandomPlayer computerPlayer) {
		int userChoice = 0;
		int computerChoice = 0;
		System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
		System.out.print("Please type in your name and press return: ");
		String name = keyboard.nextLine();
		System.out.println();
		System.out.println("Welcome " + name + ".");
		System.out.println();
		System.out.println("All right " + name + ". How many rounds would you like to play?");
		playGame(keyboard, name, computerPlayer, userChoice, computerChoice);
	}
	
	/* Method that allows the user to enter how many rounds to play and allows them
	 * to play that many. It calls other helper methods to do that. The parameter keyboard
	 * allows for user input and parameter computerPlayer allows for the computer input. 
	 * The parameter name allows the name the user entered shown throughout the code output. 
	 * Parameters userChoice and computerChoice allow the user and computer to pick between 
	 * rock, paper, and scissors. 
	 * In the end this method calls method conclusion that prints the conclusion.
	 * Overall, this method basically prints almost all of the game.
	 */
	public static void playGame(Scanner keyboard, String name, RandomPlayer computerPlayer,
			int userChoice, int computerChoice) {
		System.out.print("Enter the number of rounds you want to play and press return: ");
		int numberOfGames = keyboard.nextInt();
		System.out.println();
		int gameNumber = 1;
		int userWinsCount = 0;
		int computerWinsCount = 0;
		int drawCount = 0;
		while (gameNumber <= numberOfGames) {
			System.out.println("Round " + gameNumber);
			System.out.println(name + ", please enter your choice for this round.");
			System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
			userChoice = keyboard.nextInt();
			String userResponse = numberToString(userChoice);
			computerChoice = computerPlayer.getComputerChoice();
			String computerResponse = numberToString(computerChoice);
			System.out.println("Computer picked " + computerResponse + ", " 
			+ name + " picked " + userResponse + ".");			
			whoWins(userChoice, computerChoice);
			System.out.println();
			gameNumber++;
			computerWinsCount = computerWinsCount(userChoice, computerChoice, computerWinsCount);
			userWinsCount = userWinsCount(userChoice, computerChoice, userWinsCount);
			drawCount = drawCount(userChoice, computerChoice, drawCount);
		}
		conclusion(numberOfGames, name, userWinsCount, computerWinsCount, drawCount);
	}

	/* Method that tells the users who won a particular round by calling other methods
	 * such as rockAndPaper,scissorsAndPaper, and scissorsAndRock.
	 * Also shows if there was a draw by calling the draw method. Parameters userChoice and 
	 * computerChoice are here to show who won a round based on the players choices. 
	 */
	public static void whoWins(int userChoice, int computerChoice) {
		System.out.println();
		rockAndPaper(userChoice, computerChoice);
		scissorsAndPaper(userChoice, computerChoice);
		scissorsAndRock(userChoice, computerChoice);
		draw(userChoice, computerChoice);
	}
	
	/* The method that shows and prints which player won a round if either one picks rock or
	 * paper. Parameters userChoice and computerChoice are here to show who won a round based 
	 * on the players choices.
	 */
	public static void rockAndPaper(int userChoice, int computerChoice) {
		if (userChoice == PAPER && computerChoice == ROCK) {
			String youWin = "PAPER COVERS ROCK. You win.";
			System.out.println(youWin);
		}
		if (userChoice == ROCK && computerChoice == PAPER) {
			String iWin = "PAPER COVERS ROCK. I win.";
			System.out.println(iWin);
		}
	}
	
	/* The method that shows and prints who won a particular round if either one picks paper or
	 * scissors. Parameters userChoice and computerChoice are here to show who won a round based 
	 * on the players choices.
	 */
	public static void scissorsAndPaper(int userChoice, int computerChoice) {
		if (userChoice == SCISSORS && computerChoice == PAPER) {
			String youWin = "SCISSORS cut PAPER. You win.";
			System.out.println(youWin);
		}
		if (userChoice == PAPER && computerChoice == SCISSORS) {
			String iWin = "SCISSORS cut PAPER. I win.";
			System.out.println(iWin);
		}
	}
	
	/* The method that shows and prints who won a particular round if either one picks rock or
	 * scissors. Parameters userChoice and computerChoice are here to show who won a round based 
	 * on the players choices. 
	 */
	public static void scissorsAndRock(int userChoice, int computerChoice) {
		if (userChoice == SCISSORS && computerChoice == ROCK) {
			String iWin = "ROCK breaks SCISSORS. I win.";
			System.out.println(iWin);
		}
		if (userChoice == ROCK && computerChoice == SCISSORS) {
			String youWin = "ROCK breaks SCISSORS. You win.";
			System.out.println(youWin);
		}
	}
	
	/* Method that shows and prints if the particular round was a draw. Parameters userChoice 
	 * and computerChoice are here to show who won a round based on the players choices.
	 */
	public static void draw(int userChoice, int computerChoice) {
		if (userChoice == computerChoice) {
			String draw = "We picked the same thing! This round is a draw.";
			System.out.println(draw);
		}
	}
	
	/* Method that keeps track of the # of times the computer won. Parameters userChoice and 
	 * computerChoice are here to show who picked what in favor of the computer. 
	 * Parameter computerWinsCount is here to increase every time the user and computer make
	 * these specific choices. The computerWinsCount is then returned to the 
	 * computerWinsCount in the while loop.
	 */
	public static int computerWinsCount(int userChoice, int computerChoice, int computerWinsCount) {
		if ((userChoice == ROCK && computerChoice == PAPER) || (userChoice == PAPER && 
				computerChoice == SCISSORS)
				|| (userChoice == SCISSORS && computerChoice == ROCK)) {
			computerWinsCount++;
		}
		return computerWinsCount;
	}
	
	/* Method that keeps track of the number of times the user won. Parameters userChoice and 
	 * computerChoice are here to show who picked what in favor of the user. 
	 * Parameter userWinsCount is here to increase every time the user and computer make
	 * these specific choices. The userWinsCount is then returned to the 
	 * userWinsCount in the while loop.
	 */
	public static int userWinsCount(int userChoice, int computerChoice, int userWinsCount) {
		if ((userChoice == PAPER && computerChoice == ROCK) || (userChoice == SCISSORS 
				&& computerChoice == PAPER)
				|| (userChoice == ROCK && computerChoice == SCISSORS)) {
			userWinsCount++;
		}
		return userWinsCount;
	}
	
	/* Method that keeps track of the number of times there was a draw. Parameters userChoice and 
	 * computerChoice are here to show who picked what. 
	 * Parameter drawCount is here to increase every time the user and computer make the same
	 * choices. The drawCount is then returned to the drawCount in the while loop.
	 */
	public static int drawCount(int userChoice, int computerChoice, int drawCount) {
		if (userChoice == computerChoice) {
			drawCount++;
		}
		return drawCount;
	}
	
	/* Method that prints the number of times the computer won, user won, and number of draws.
	 * Also says the overall winner. All the parameters here are to show the summary of the game 
	 * and overall winner of the game.
	 */
	public static void conclusion(int numberOfGames, String name, int userWinsCount, 
			int computerWinsCount, int drawCount) {
		System.out.println();
		System.out.println("Number of games of ROCK PAPER SCISSORS: " + numberOfGames);
		System.out.println("Number of times Computer won: " + computerWinsCount);
		System.out.println("Number of times " + name + " won: " + userWinsCount);
		System.out.println("Number of draws: " + drawCount);
		if (computerWinsCount > userWinsCount) {
			System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
		}else if (computerWinsCount == userWinsCount) {
			System.out.println("We are evenly matched.");
		}else if (computerWinsCount < userWinsCount)
			System.out.println("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
	}
}
