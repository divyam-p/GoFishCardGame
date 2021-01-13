/** This is the class which represents any Card. 
*
*  @author Divyam Patel 
*/
public class Card{ 
    /**
    *  The suit and rank of the card. 
    */
    private String suit; 
    private int rank; 

    /**This is the Card's constructor which takes in
    *	 the suit, and rank of the card. 
    *  @param s - the suit of the card (heart (H), Diamond 
    *    (D), Clubs (C), Spade (S))
    *  @param r - the rank of the card (from 1-13, A = 1, J 
    *    = 11, Q = 12, K = 13)
    */
    public Card(String s, int r){ 
        this.suit = s; 
        this.rank = r; 
    }

    /**Gets the rank and suit of the card. 
    *
    *  @return a String reprenting this card 
    */
    public String toString(){ 
        return this.suit + " " + this.rank; 
    }

    /**Gets the rank of the card. 
    *
    *  @return a int reprenting this card's rank
    */ 
    public int getRank(){ 
        return this.rank; 
    }

    /**Gets the suit of the card. 
    *
    *  @return a String reprenting this card's suit
    */ 
    public String getSuit(){ 
        //Converts the letter representations to shapes.  
        if(this.suit.equals("H")){ 
            return "♥"; 
        }
        else if(this.suit.equals("D")){ 
            return "◆"; 
        }
        else if(this.suit.equals("C")){ 
            return "♣"; 
        }
        else{ 
            return "♠"; 
        }
    }
}