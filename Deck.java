
import java.util.Random;

/**
 * An implementation of a deck of cards.
 * 
 * @author Amberlyn Schjoll
 *@since 04/05/2016
 */
public class Deck {

	/**
	 * The array of cards in the deck, where the top card is 
	 * in the first index.
	 * 
	 * Could also use an ArrayList or a LinkedList.
	 */
	private Card[] myCards;
	/**
	 * the number of cards currently in the deck.
	 */
	private int numCards;
	
	/**
	 * Constructor with a default of one deck (i.e. 52 cards)
	 * and no shuffling.
	 */
	public Deck() {
		
		// call the other constructor, defining on deck without shuffling
		this( 1, false );
		
	}
	/**
	 * Constructor that defiles the number of decks ( i.e.
	 * how many sets of 52 cards are in the deck) and whether
	 * it should be shuffled.
	 * 
	 * @param numDeck 	the number of individual decks in this deck
	 * @param shuffle	whether to shuffle the cards
	 */
	public Deck( int numDecks, boolean shuffle ) {
		
		this.numCards = numDecks * 52;
		this.myCards = new Card[ this.numCards ];
		
		// initial card index
		int c = 0;
		
		// for each deck
		for ( int d = 0; d < numDecks; d++ ) {
			
			//for each suit
			for ( int s = 0; s < 4; s++ ) {
				
				//for each number
				for ( int n = 1; n <= 13; n++ ) {
					
					//add a new Card to the deck
					this.myCards[c] = new Card( Suit.values()[s], n );
					c++;
				}
			}
		}
		
		// shuffle, if necessary
		if (shuffle) {
			this.shuffle();
		}
		
	}
	
	/**
	 * Shuffle deck by randomly swapping pairs of cards.
	 */
	public void shuffle() {

		
		// init random number generator
		Random rng = new Random();
		
		// temporary card
		Card temp;
		
		int j;
		for (int i = 0; i < this.numCards; i++ ) {
			
			// get a random card j to swap i's value with
			j = rng.nextInt( this.numCards );
			
			// do swap
			temp = this.myCards[i];
			this.myCards[i] = this.myCards[j];
			this.myCards[j] = temp;
			
		}
	}

	/**
	 * Deal the next card from the top of the deck.
	 * 
	 * @return	the dealt card
	 */
	public Card dealNextCard() {
		
		// get the top card
		Card top = this.myCards[0];
		
		// shift all the subsequent cards to the left by one index
		for (int c = 1; c < this.numCards; c++) {
			this.myCards[ c-1 ] = this.myCards[c];
		}
		this.myCards[this.numCards-1] = null;
		
		// decrement the number of cards in our deck
		this.numCards--;
		
		return top;
	}
	
	/**
	 * Print the top cards in the deck.
	 * 
	 * @param numToPrint	the number of cards from the top
	 * 						of the deck to print
	 * @return 
	 */
	public void printDeck( int numToPrint ) {
		
		for ( int c = 0; c < numToPrint; c++ ) {
			System.out.printf( "% 3d/%d %s\n", c+1, 
					this.numCards,
					this.myCards[c].toString() );
		}
		System.out.printf( "\t[%d others]\n", 
				this.numCards-numToPrint );
	}
}
