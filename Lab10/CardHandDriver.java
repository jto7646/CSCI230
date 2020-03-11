package LabSolutions.list_iterator;

import java.util.ArrayList;
import java.util.Collections;

import LabSolutions.list_iterator.Card.Rank;
import LabSolutions.list_iterator.Card.Suit;

/**
 * A class that can be used to test your <i>CardHand</i> class. Two useful functions
 * @author YourName

 */
public final class CardHandDriver {

	private static final int HAND_SIZE = 5;
	private static ArrayList<Card> deck;
	private static CardHand hand;
	
	
	/**
	 * Initializes ArrayList deck with a shuffled set of 52 standard playing cards
	 */
	private static void initializeDeck() {
		deck = new ArrayList<Card>();
		for(Rank r : Card.Rank.values())
			for(Suit s : Card.Suit.values())
				deck.add(new Card(r,s));
		
		Collections.shuffle(deck);	
	}
	
	private static Card drawCard() throws IllegalStateException{
		if(deck.isEmpty()) throw new IllegalStateException("Deck has no more cards!");
		return deck.remove(0);
	}
	
	private static Card[] drawHand() {
		
		Card[] cards = new Card[5];
		for(int i = 0; i < HAND_SIZE; ++i)
			cards[i] = drawCard();
		return cards;
		
	}
	
	public static void main(String[] args) {
		
		initializeDeck();
		Card[] cards = drawHand();
		// TODO Test the remainder of the functions in class CardHand
	}

}
