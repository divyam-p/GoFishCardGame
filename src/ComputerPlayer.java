/** This is the subclass of Player which represents 
*      any computer player. 
*  @author Divyam Patel 
*/
public class ComputerPlayer extends Player{ 
    /**This is the computer player's constructor which takes
    *   in the name of the player. 
    *  @param n - the name of the player
    */
    public ComputerPlayer(String n){ 
        super(n); 
    }

    /**The computer player's turn.
    * 
    *  @param rightPlayer - the player that the current player
    *   asks for a card  
    *  @param d - the deck of cards  
    */
    public void turn(Player rightPlayer, Deck d){ 
        //Calls the super classes turn method first and then 
        //continues with the subclasses. 
        super.turn(rightPlayer, d);  
        
        //Checks which card rank the player has of the
        //greatest number and thats the rank that it asks
        //for.  
        int count = 0; 
        int index = 0; 
        for(int i = 1; i < 14; i++){ 
            int temp = 0; 
            for(int j = 0; j < this.getHand().size(); j++){ 
                if(this.getHand().get(j).getRank() == i){ 
                    temp++; 
                    if(temp > count){ 
                        count = temp; 
                        index = j; 
                    }
                }
            } 
        }
        if(index == 0){ 
            index = (int)(Math.random() * (this.getHand().size())); 
        }
        //This is the card to get. 
        Card cardToGet = this.getHand().get(index) 
        ; 
        System.out.println(" "); 
        System.out.println(this.getName() + " asked for " + this.convertRankAlp(cardToGet.getRank()) + "."); 
        System.out.println( " "); 
        
        //Checks if the player to the right has the card 
        //and takes it if the player does. 
        int check = 0; 
        for(int i = 0; i < rightPlayer.getHand().size(); i++){ 
            if(rightPlayer.getHand().get(i).getRank() == cardToGet.getRank()){ 
                Card tempCard = rightPlayer.getHand().get(i);
                rightPlayer.getHand().remove(i);
                this.getHand().add(tempCard); 
                check = 1; 
                System.out.println(this.getName() + " gets " + tempCard.getSuit() + " " + this.convertRankAlp(tempCard.getRank()) + " from " + rightPlayer.getName() + "."); 
                System.out.println(" "); 
                break; 
            }
        }
        //If the right player does not have the card, the  
        //current computer player fishes. 
        if(check == 0){ 
            System.out.println(rightPlayer.getName() + " does not have the card, player fishes!"); 
            System.out.println(" "); 
            this.fishCard(d); 
        }
        //Prints both player's updated hands. 
        this.sortHand();
        System.out.println(this.toString()); 
        System.out.println(" "); 
        System.out.println(rightPlayer.toString()); 
        System.out.println(" "); 
        //Prints the number of cards left in the deck. 
        System.out.println("Number of Cards Left in Deck: " + d.deckLength()); 
        
    }


}
