package project4;

import java.util.Random;
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
		player[0] = new Player(null);
		player[1] = new Player(null);
	}

	public void playGame() {
		System.out.println("Let the game begin....");
		deck.shuffle();
		getNames();
		dealCards();
		Player me = player[0];
		Player computer = player[1];
		while (!deck.isEmpty() && me.getTotalCards() != 0 && computer.getTotalCards() != 0) {
			System.out.println("***************************************************************");
			System.out.println(me);
			int askedRank = getRank(computer);
			while (!me.hasRank(askedRank)) {
				System.out.println("You can't ask for a rank that you don't have!");
				askedRank = getRank(computer);
			}
			String says = computer.getName() + " says ";
			if (computer.hasRank(askedRank)) {
				System.out.println(says + "\"Yes!!\"");
				me.addCards(computer.getCard(askedRank));
			} else {
				System.out.println(says + "\"No, Go Fish\"");
				me.addCard((GoFishCard)deck.deal());
			}	
			me.getPoints();
			System.out.println(me);
			System.out.println(deck);
			// computer's automatic turn
			System.out.println("***************************************************************");
			System.out.println(computer);
			Random rand = new Random();
			GoFishCard randCard = computer.getCardAt(rand.nextInt(computer.getTotalCards()));
			askedRank = randCard.getRank();
			System.out.println(me.getName() + ", do you have any: " + randCard.getRankAsString());
			says = me.getName() + " says ";
			if (me.hasRank(askedRank)) {
				System.out.println(says + "\"Yes!!\"");
				computer.addCards(me.getCard(askedRank));
			} else {
				System.out.println(says + "\"No, Go Fish\"");
				computer.addCard((GoFishCard)deck.deal());
			}	
			computer.getPoints();
			System.out.println(computer);
		}
		gameResults();
	}

	public void getNames() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a name for player: ");
		player[0].setName(scan.nextLine());
		//System.out.print("Enter a name for player 2: ");
		player[1].setName("Computer");
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
		System.out.print(p.getName() + ", do you have any: ");
		Scanner scan = new Scanner(System.in);
		String rankStr = scan.nextLine();
		int rankInt = GoFishCard.convertToRank(rankStr);
		return rankInt;
	}

	public void gameResults() {
		if (player[0].getPoints() > player[1].getPoints()) 
			System.out.println(player[0].getName() + " is the Winner...");
		else if (player[1].getPoints() > player[0].getPoints())
			System.out.println(player[1].getName() + " is the Winner...");
		else
			System.out.println("DRAW!");
		
	}
}
