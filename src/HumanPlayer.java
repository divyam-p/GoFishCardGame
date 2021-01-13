/** This is the subclass of Player which represents 
*      any human player. 
*  @author Divyam Patel 
*/

import java.util.Scanner; 

public class HumanPlayer extends Player{ 
    /**This is the human player's constructor which takes
    *   in the name of the player. 
    *  @param n - the name of the player
    */
    public HumanPlayer(String n){ 
        super(n); 
    }
    
    /**The human player's turn.
    * 
    *  @param rightPlayer - the player that the current player
    *   asks for a card  
    *  @param d - the deck of cards  
    */
    public void turn(Player rightPlayer, Deck d){ 
        Scanner in = new Scanner(System.in); 
        //Calls the super classes turn method first and then 
        //continues with the subclasses. 
        super.turn(rightPlayer, d); 
        //Keeps asking the user for a card they want, until 
        //they input a existing card. 
        int realRank = 0; 
        while(true){ 
            System.out.println("What card do you want to ask for? (Options : A, 2-10, J, Q, K)");
            String rank = in.nextLine(); 
            realRank = this.convertRankNum(rank);
            if(realRank == -1){ 
                System.out.println("Invalid Input! "); 
                continue; 
            }
            else{ 
                break; 
            }
        }
        System.out.println(" "); 

        //Checks if the player to the right has the card with
        //that rank or not. 
        int check = 0; 
        for(int i = 0; i < rightPlayer.getHand().size(); i++){ 
            if(rightPlayer.getHand().get(i).getRank() == realRank){ 
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
        //player fishes. 
        if(check == 0){ 
            System.out.println(rightPlayer.getName() + " does not have the card, player fishes!"); 
            System.out.println(" "); 
            this.fishCard(d); 
        }
        //Prints the sorted hands of both players. 
        this.sortHand();
        System.out.println(this.toString()); 
        System.out.println(" "); 
        System.out.println(rightPlayer.toString()); 
        System.out.println(" "); 
        //Prints the number of cards left in the deck of
        //cards. 
        System.out.println("Number of Cards Left in Deck: " + d.deckLength()); 
    
    }

}