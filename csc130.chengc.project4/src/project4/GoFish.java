package project4;

import java.util.Scanner;

/**
 * <p>
 * Title: The GoFish Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a GoFish game.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * 
 * @author F. Graham
 */
public class GoFish {
	private Deck deck = new Deck();
	private Player[] player = new Player[2];
	
	public GoFish() {
		deck.shuffle();
		player[0] = new Player(null);
		player[1] = new Player(null);
	}

	public void playGame() {
		System.out.println("Let the game begin....");
		getNames();
		dealCards();
		displayHands();
	}

	public void getNames() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a name for player 1: ");
		player[0].setName(scan.nextLine());
		System.out.print("Enter a name for player 2: ");
		player[1].setName(scan.nextLine());
	}

	public void dealCards() {
		for (int i = 0; i < 7; i++) {
			player[0].addCard((GoFishCard)deck.deal());
			player[1].addCard((GoFishCard)deck.deal());
		}
	}

	public void displayHands() {
		System.out.println(player[0].toString());
		System.out.println(player[1].toString());
	}

	public int getRank(Player p) {
		System.out.println("do you have any: ");
		Scanner scan = new Scanner(System.in);
		String rankStr = scan.nextLine();
		int rankInt = GoFishCard.convertToRank(rankStr);
		return p.getCard(rankInt).size();
	}

	public static void gameResults() {
		System.out.println("game ended");
	}
}
