package project4;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p>
 * Title: The Hand Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a GoFish Hand.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * 
 * @author F. Graham
 */
public class Hand {
	/**
	 * LinkList of GoFish Cards
	 */
	private LinkedList<GoFishCard> hand;

	/**
	 * Default constructor
	 */
	public Hand() {
		hand = new LinkedList<GoFishCard>();
	}

	/**
	 * Returns the number of cards in the hand
	 * 
	 * @return the number of cards in the hand
	 */
	public int getCount() {
		return hand.size();
	}

	/**
	 * Returns the hand as LinkedList of GoFish cards
	 * 
	 * @return the hand as a LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> getHand() {
		return hand;
	}

	/**
	 * Returns <i>true</i> if this rank is the hand
	 * 
	 * @param rank - the rank to search for
	 * @return Returns <i>true</i> if this rank is the hand,<br>
	 *         <i>false</i> otherwise
	 */
	public boolean hasRank(int rank) {
		for (GoFishCard card : hand) 
			if (card.getRank() == rank)
				return true;
		return false;
	}

	/**
	 * Returns a string representation of the hand
	 */
	public String toString() {
		String str = "";
		for (GoFishCard card : hand)
			str += card.toString() + " ";
		return str;
	}

	/**
	 * Finds and returns all cards of the specified rank
	 * 
	 * @param rank - the rank to search for
	 * @return all of the cards of that rank as a LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> findRank(int rank) {
		LinkedList<GoFishCard> ranks = new LinkedList<>();
		for (GoFishCard card : hand)
			if (card.getRank() == rank)
				ranks.add(card);
		hand.removeAll(ranks);
		return ranks;
	}

	/**
	 * Adds a Card to the hand, the hand is sorted by rank
	 * 
	 * @param card - a GoFish Card
	 */
	public void insertByRank(GoFishCard card) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).compareByRank(card) >= 0) {
				hand.add(i, card);
				return;
			}
		}
		// if card's rank is the largest, add it to the end of the hand
		hand.add(card);
	}

	/**
	 * Adds a LinkList of Cards to the hand, the hand is sorted by rank
	 * 
	 * @param otherHand LinkedList of GoFish Cards
	 */
	public void insertHand(Collection<? extends GoFishCard> otherHand) {
		for (GoFishCard card : otherHand)
			this.insertByRank(card);
	}

	/**
	 * Determines if the hand is empty
	 * 
	 * @return - Returns <i>true</i> if the hand is empty, <i>false</i> otherwise
	 */
	public boolean isEmpty() {
		return hand.size() == 0;
	}

	/**
	 * Returns 1 if a book (all 4 cards of a particular RANK) is in the hand and
	 * removes the book from the hand
	 * 
	 * @return the number of books (all 4 cards of a particular RANK) in the hand
	 */
	public int evaluate() {
		int books = 0;
		for (int i = 0; i < 13; i++) 
			if (countRank(i) == 4) {
				books++;
				findRank(i);
			}
		return books;
	}

	/**
	 * Counts the number of cards of a particular rank in the hand
	 * 
	 * @param rank - the rank to count
	 * @return the number of cards of that rank
	 */
	public int countRank(int rank) {
		int count = 0;
		for (GoFishCard card : hand) {
			if (card.getRank() == rank)
				count++;
		}
		return count;
	}

	/**
	 * Returns the card at the specified position in this list.
	 * 
	 * @param index the index of the list
	 * @return the card at the specified position in this list.
	 */
	public GoFishCard getCardAt(int index) {
		return hand.get(index);
	}

	/**
	 * Returns a list of cards of a specified rank
	 * 
	 * @param rank - the rank to search for
	 * @return the cards as LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> getCards(int rank) {
		LinkedList<GoFishCard> ranks = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			GoFishCard tempCard = new GoFishCard(rank, i);
			ranks.add(tempCard);
		}
		return ranks;
	}
}
