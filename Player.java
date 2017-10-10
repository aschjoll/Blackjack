/**
 * An implementation of a blackjack player.
 * 
 * @author Amberlyn Schjoll
 * @since 04/05/2016
 */
public class Player {
	
	/**
	 * The player's name.
	 */
	private String name;
	
	/**
	 * The cards in the player's current hand.
	 */
	private Card[] hand = new Card[10];
	
	/**
	 * The number of cards in the player's current hand.
	 */
	private int numCards;
	
	/**
	 * Player constructor.
	 * 
	 * @param aName	the name of the player
	 */
	public Player( String aName ) {
		
		this.name = aName;
		
		// set a player's hand to empty
		this.emptyHand();
	}
	
	/**
	 * Reset the player's hand to have no cards.
	 */
	public void emptyHand() {
		
		for ( int c = 0; c < 10; c++ ) {
			this.hand[c] = null;
		}
		this.numCards = 0;
	}
	
	/**
	 * Add a card to the player's hand.
	 * 
	 * @param aCard the card to add
	 * @return		whether the sum of the new hand is below or 
	 * 				equal to 21
	 */
	public boolean addCard( Card aCard ) {
		
		// print error if we already have the max number of cards
		if ( this.numCards == 10 ) {
			System.err.printf( "%s's hand already has 10 cards; " + "cannot add another\n", this.name );
			System.exit( 1 );
		}
		
		// add new card in next slot and increment number-of-cards counter
		this.hand[ this.numCards ] = aCard;
		this.numCards++;
		
		return ( this.getHandSum() <= 21 );
	}
	
	/**
	 * Get the sum of the cards in the player's hand.
	 * 
	 * @return the sum
	 */
	public int getHandSum() {
		
		int handSum = 0;
		int cardNum;
		int numAces = 0;
		
		// calculate each card's contribution to the hand sum
		for ( int c = 0; c < this.numCards; c++ ) {
			
			// get the number for the current card
			cardNum = this.hand[ c ].getNumber();
			
			if ( cardNum == 1 ) { // Ace
				numAces++;
				handSum += 11;
			} else if ( cardNum > 10 ) { // Face card
				handSum += 10;
			} else {
				handSum += cardNum;
			}
		}
		
		// If we have aces and our sum id > 21, set some/all
		// of them to value 1 instead.
		while ( handSum > 21 && numAces > 0 ) {
			handSum -= 10;
			numAces--;
		}
		
		return handSum;
	}
	
	/**
	 * Print the cards in the player's hand.
	 * 
	 * @param showFirstCard	whether the first card is
	 * 						hidden or not
	 */
	public void printHand( boolean showFirstCard ) {
		
		System.out.printf( "%s's cards:\n", this.name );
		for ( int c = 0; c < this.numCards; c++ ) {
			if ( c == 0 && !showFirstCard ) {
				System.out.println( "  [hidden]");
			} else {
				System.out.printf( "  %s\n", this.hand[ c ].toString() );
			}
		}
	}
}












