package LabSolutions.list_iterator;

import java.util.Iterator;
import LabSolutions.list_iterator.Card.Suit;
import net.datastructures.LinkedPositionalList;

/**
 * A class that supports the arrangement of a hand of cards. Represents the sequence of cards using a single positional list ADT so that cards of the same suit are kept together.
 * Cards are pulled from the positional list in constant time.<br><br><b>Assignment Restrictions</b>: You may edit this file as much as you please as long as the following functions are
 * implemented: <i>addCard</i>, <i>play</i>, <i>iterator</i>, <i>suitIterator</i> and a <i>single</i> LinkedPositionalList is used.
 * @author YourName

 */
public final class CardHand {
	
	private LinkedPositionalList<Card> hand;
	
	/**
	 * Initializes the LinkedPositionalList hand with five new cards
	 * @param cards
	 */
	public CardHand(Card[] cards) {
		
	}
	
	/**
	 * @return - Adds the specified card to the hand.
	 */
	void addCard(Card c) {
		
	}
	
	/**
	 * @return - Remove and returns a card of suit <i> from the player's hand; if there are no cards of suit <i>s</i>, then remove and return an arbitrary card from the hand.
	 */
	Card play(Suit s) {
		return null;
	}
	
	/**
	 * @return - An iterator for all cards currently in the hand
	 */
	Iterator<Card> iterator() {
		return null;
	}
	
	/**
	 * @return - An iterator for all cards of suit <i>s</i> that are currently in the hand.
	 */
	Iterator<Card> suitIterator(Suit s) {
		return null;
	}
	
	/**
	 * @return - Returns a string of the cards currently in the hand.
	 */
	@Override
	public String toString() {
		
		// Hint: The cards toString function is provided to you
		return "";
	}
}
