package LabSolutions.list_iterator;

/**
 * Java implementation of a standard playing card with a face value (<b>rank</b>) and <b>suit</b>
 * <br><br><b>Assignment Restrictions</b>: You may <i>not</i> edit the contents of this file.
 * @author Frank Madrid
 */
public final class Card {

	/**
	 * <b>Note</b>: An example of an enumeration in Java which allows the inclusion of <i>member</i>. Treat enumerations in Java as a class where each enum constant is a constructor.
	 */
	public enum Rank {
		
		ACE("Ace"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("Jack"), QUEEN("Queen"), KING("King");
		
		private final String name;
		
		Rank(String name) { this.name = name;}
		
		public char getAbbrevation() { return name.charAt(0); }
		public String toString() { return name; }
	}
	
	public enum Suit {
		HEART("Heart"), SPADE("Spade"), DIAMOND("Diamond"), CLUB("Club");
		private final String name;
		Suit(String name) { this.name = name; }
		public char getAbbreviation() { return name.charAt(0); }
		public String toString() { return name; }
	}
	
	private Rank r;
	private Suit s;
	
	public Card(Rank r, Suit s) throws IllegalArgumentException {
		
		this.r = r;
		this.s = s;
	}
	
	public Rank getRank() { return r; }
	public Suit getSuit() { return s; }
	
	/**
	 * @return A string containing the abbreviate of a playing card. For example, <i>"Ace of Hearts"</i> -> <i>"AH"</i>
	 */
	public String shortName() { return r.getAbbrevation() + "" + s.getAbbreviation(); }
	
	/**
	 * @return A string containing the full name of a playing card. For example, <i>"Ace of Hearts"</i>
	 */
	@Override
	public String toString() { return r.toString() + " of " + s.toString(); }
	
	
}
