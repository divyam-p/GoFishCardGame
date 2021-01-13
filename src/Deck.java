/** This is the Deck class which represents a deck of 52 
*      52 cards. 
*  @author Divyam Patel 
*/

import java.util.Collections;
import java.util.ArrayList; 

public class Deck{ 
    /**
    *  The deck of cards. 
    */
    private ArrayList<Card> deck = new ArrayList<Card>(); 

    /**Adds a card to the deck. 
    *
    *  @param c - a card
    */
    public void addCard(Card c){ 
        this.deck.add(c); 
    }

    /**Gets the entire deck. 
    *
    *  @return the deck of cards 
    */
    public ArrayList<Card> printdeck(){ 
        return deck; 
    }

    /**Gets the deck's length. 
    *
    *  @return the length of the deck
    */
    public int deckLength(){ 
        return this.deck.size(); 
    }

    /**Creates the deck of Cards. 
    * 
    */
    public void createDeck(){ 
        for(int i = 0; i < 4; i++){ 
            for(int j = 1; j < 14; j++){ 
                if(i == 0){ 
                    this.deck.add(new Card("H", j)); 
                }
                else if(i == 1){ 
                    this.deck.add(new Card("D", j)); 
                }
                else if(i == 2){ 
                    this.deck.add(new Card("C", j)); 
                }
                else{ 
                    this.deck.add(new Card("S", j)); 
                }
            }
        }
    }

    /**Gets a specific card in the deck. 
    *
    *  @return a card
    */
    public Card getCard(){ 
        //Gets first value in the array (top of the deck). 
        Card temp = this.deck.get(0); 
        this.deck.remove(0); 
        return temp; 
    }

    /**Gets the size of the deck. 
    *
    *  @return size of the deck 
    */
    public int deckSize(){ 
        return this.deck.size(); 
    }

    /**Shuffles the deck. 
    *
    */
    public void shuffleDeck(){
        Collections.shuffle(this.deck); 
    }
    
}