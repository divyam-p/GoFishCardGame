/** This is the superclass which represents any Player. 
*
*  @author Divyam Patel 
*/

import java.util.ArrayList; 
import java.util.Random; 

public class Player{ 
    //Variable view is for ms.rubini to be able to see the
    //cards, have to change number manually.
    //Keep value of view to 1 to keep the computer 
    //players cards not visible. 
    //Change the value of view to 0 to be able to see. 

    public static int view = 1; 
    /**
    *  The name, # of pairs, and hand of player. 
    */ 
    private String name;
    private int pairs = 0; 
    private ArrayList<Card> hand = new ArrayList<Card>();

    /**This is the Player's constructor which takes in
    *	 the name of the player. 
    *  @param n - the name of the player
    */
    public Player(String n){ 
        this.name = n;  
    }

    /**Adds a card to player's hand. 
    *   
    *  @param c - any 1 card
    */
    public void addCard(Card c){ 
        hand.add(c); 
    }

    /**Prints player's hand. 
    *
    */
    public void printHand(){ 
        for(Card card : this.hand){ 
            System.out.println(card); 
        }
    }

    /**Gets the number of pairs a player has. 
    *
    *  @return the number of pairs a player has
    */
    public int getPairs(){ 
        return this.pairs; 
    }

    /**Prints the player's hand. 
    *
    *  @return a String reprenting the cards in the players 
    *    hand 
    */
    public String toString(){ 
        //Checks if "view" is 0 or 1, and prints hand
        //accordingly, when variable is 0, the user can see 
        //the cards in everyones hands, when variable is 1, 
        //the user can only see the cards in their hand. 
        if(view == 0){ 
            String toReturn = "| "; 
            //Goes through every card in the hand
            //individually and adds it to the string to be //returned. 
            for(Card card : this.hand){ 
                toReturn = toReturn + card.toString() + " | "; 
            }
            toReturn = toReturn + "- " + this.getName() + "'s hand"; 
            return toReturn; 
        } 
        else{ 
            String toReturn = "| "; 
            int count = 0; 
            //Goes through every card in the hand and checks
            //which players turn it is, if its a computer 
            //player's turn, then it print "☒" instead of 
            //the actual card. 
            for(Card card : this.hand){ 
                if(this.name.equals("Player1") || this.name.equals("Player2") || this.name.equals("Player3")){ 
                    toReturn = toReturn + " ☒ " + " | "; 
                }
                else{ 
                    toReturn = toReturn + card.getSuit() + " " + this.convertRankAlp(card.getRank()) + " | "; 
                }
                count++; 
                //Assures that a extra "|" is not printed
                //when there are exactly 8 cards in the
                //players hand. 
                if(this.hand.size() == 8 && count == 8){ 
                    toReturn+= "\n "; 
                    break;
                }
                //Makes it so the print moves to the next
                //line after eight cards are printed on one
                //line. 
                if(count == 8){ 
                    toReturn+= "\n| "; 
                }
            }
            //Returns the combined string with all the cards.
            toReturn = toReturn + "- " + this.getName() + "'s hand"; 
            return toReturn; 
        } 
    
    }

    /**Converts a card's rank into its proper numerical
    *   value. 
    *     
    *  @param r - the String value of the rank of a card
    *  @return the numerical value of the card's rank
    */
    public int convertRankNum(String r){ 
        //Checks if the rank is A, J, Q, K. 
        if(r.equals("A") || r.equals("a")){ 
            return 1; 
        }
        else if(r.equals("J") || r.equals("j")){ 
            return 11; 
        }
        else if(r.equals("Q") || r.equals("q")){ 
            return 12; 
        }
        else if(r.equals("K") || r.equals("k")){ 
            return 13; 
        }
        else{ 
            //Returns numerical value or -1 which is used to 
            //check if the user inputed a card that exists 
            //in the HumanPlayer class. 
            try{ 
                int toReturn = Integer.valueOf(r);
                if(toReturn > 10 || toReturn < 2){ 
                    return -1; 
                }
                else{ 
                    return toReturn; 
                }
            } 
            catch(Exception e){
                return -1; 
            }
        }
    }

    /**Convert's a card's numerical rank into it's shape 
    *   or the String rank. 
    *  
    *  @param r - the numerical value of the rank of a card 
    *  @return the String value of the rank of the card 
    */
    public String convertRankAlp(int r){ 
        if(r == 1){ 
            return "A"; 
        }
        else if(r == 11){ 
            return "J"; 
        }
        else if(r == 12){ 
            return "Q"; 
        }
        else if(r == 13){ 
            return "K"; 
        }
        else{ 
            return String.valueOf(r); 
        }
    }

    /**Gets the player's hand. 
    *   
    *  @return player's hand
    */
    public ArrayList<Card> getHand(){ 
        return hand; 
    }

    /**Gets the player's name. 
    *   
    *  @return the player's name
    */
    public String getName(){ 
        return this.name; 
    }

    /**Sorts the player's hand. 
    *
    */
    public void sortHand(){
        //Creates a temporary array and adds the values in 
        //the sorted order. 
        ArrayList<Card> temp = new ArrayList<Card>();  
        for(int i = 1; i < 14; i++){ 
            int index = 0;
            for(Card card : this.hand){ 
                if(card.getRank() == i){ 
                    Card tempCard = this.hand.get(index); 
                    temp.add(tempCard); 
                }
                index++; 
            }
            
        }
        //Makes the sorted temporary array (hand) equal to 
        //to the player's hand. 
        this.hand = temp; 
        
    }

    /**Adds a card to player's hand. 
    *   
    *  @param c - any 1 card
    */
    public int handLength(){ 
        return this.hand.size(); 
    }

    /**The player's turn. 
    *   
    *  @param rightPlayer - the player that the current player
    *   asks for a card  
    *  @param d - the deck of cards  
    */
    public void turn(Player rightPlayer, Deck d){ 
        //Prints both players sorted hands. 
        System.out.println("---" + this.getName() + "'s turn---"); 
        System.out.println(" "); 
        this.sortHand();
        System.out.println(this.toString());  
        System.out.println(" "); 
        System.out.println(rightPlayer.toString());  
        System.out.println(" "); 
    }

    /**Checks if the current player has any pairs of 4
    *    cards. 
    *   
    */
    public void checkPairs(){
        //Goes through every rank and checks how many times
        //the rank appears in the player's hand. 
        for(int i = 1; i < 14; i++){ 
            int count = 0; 
            for(Card card : this.hand){ 
                if(card.getRank() == i){ 
                    count++; 
                }
                //If a rank appears 4 times, any card of
                //that rank is removed and the player gets a
                //point. 
                if(count == 4){ 
                    this.pairs++; 
                    System.out.println("\n" + this.getName() + " got 4 cards of " + this.convertRankAlp(card.getRank()) + "!"); 
                    System.out.println("Player gets a point!"); 
                    //Creates a temporary arraylist with the 
                    //the cards to be removed and removes 
                    //those cards from the player's hand. 
                    ArrayList<Card> cardsToRemove = new ArrayList<Card>(); 
                    for(int j = 0; j < this.hand.size(); j++){ 
                        if(this.hand.get(j).getRank() == i){ 
                            cardsToRemove.add(this.hand.get(j)); 
                        }
                    }
                    this.hand.remove(cardsToRemove.get(0));
                    this.hand.remove(cardsToRemove.get(1));
                    this.hand.remove(cardsToRemove.get(2));
                    this.hand.remove(cardsToRemove.get(3)); 
                    break; 
                }
            }
        }
    }

    /**The player fishes a card. 
    *   
    *  @param d - deck of cards 
    */
    public void fishCard(Deck d){ 
        Card temp = d.getCard();
        this.addCard(temp);  
    }

}