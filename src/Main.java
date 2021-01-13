/**This is where all the classes are called from to perform  
*  specific tasks. 
*  @author Divyam Patel 
*/

import java.util.ArrayList; 
import java.util.Scanner;

class Main { 
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);
 
        //Intro.
        System.out.println("Welcome! "); 
        System.out.println("What is your name? "); 
        String playerName = in.nextLine();  
        System.out.println(" "); 

        //Asks user what he/she wants to do. 
        System.out.println("What would you like to do?");
        System.out.println(" 1) Get Instructions \n 2) Play Game \n 3) Exit"); 
        String choice = ""; 

        //Gets the users choice.
        while(true){ 
            System.out.println("Answer : "); 
            choice = in.nextLine(); 
            if(choice.equals("1") || choice.equals("2") || choice.equals("3")){ 
                break; 
            }
            System.out.println("Invalid Input! "); 
        }
        
        //Checks what the users choice is and operates
        //accordingly. 
        if(choice.equals("1")){ 
            //Prints instructions.  
            System.out.println("\nObjective: Fish for cards from other players until you make a \nset of four cards of the same rank (ex. 4 kings), keep doing\nthat until no cards remain in either the “draw pile” (fishing\npile), or any players hands. The player with the most number\nof pairs of 4 at the end of the game wins!");
            System.out.println("\nRules:\n - Each player starts with 4 cards at the beginning of the\n game\n - If you ask for a card and the player does not have it, you\n fish from the deck, “draw pile”\n - You go around in a circle, asking the person to the right\n for a card you need, player 1 always starts\n - Once you make a pair of 4 cards of the same rank, that gets\n stored / placed to the side and you get a point (pair) added\n to your name\n - There are 3 computer players and 1 human player (you)");

            //Starts game. 
            System.out.println("\nTry playing the game now! "); 
            choice = "2"; 
        }
        if(choice.equals("2")){ 
            System.out.println(" "); 
            System.out.println("Organizing Playing Area...");

            //Creates deck. 
            Deck deck1 = new Deck(); 
            deck1.createDeck(); 
            System.out.println("Creating Deck..."); 

            //Creates players. 
            ArrayList<Player> allPlayers = new ArrayList<Player>(); 
            allPlayers.add(new ComputerPlayer("Player1")); 
            allPlayers.add(new ComputerPlayer("Player2"));
            allPlayers.add(new ComputerPlayer("Player3")); 
            allPlayers.add(new HumanPlayer(playerName));

            //Shuffles deck. 
            deck1.shuffleDeck();
            System.out.println("Shuffling Deck..."); 

            //Distributes starting hand. 
            for(Player player : allPlayers){ 
                for(int i = 0; i < 4; i++){ 
                    player.addCard(deck1.getCard());
                }
            }
            System.out.println("Distributing Cards..."); 
            System.out.println("Lets get the game started!");
            System.out.println("Player one starts!"); 

            //Calls the method "continueGame".  
            continueGame(); 

            int gameOn = 0;
            //Variable "nextPlayer" helps keep track of
            //which player is to the right of the player
            //who's turn it is.
            int nextPlayer = 1;
            while(gameOn == 0){ 
                //Goes through each players turn one at a
                //time. 
                for(Player player : allPlayers){ 
                    System.out.println(" "); 
                    //Runs the players turn.  
                    player.turn(allPlayers.get(nextPlayer), deck1);
                    //Checks if player has any pairs of 4. 
                    player.checkPairs(); 
                    //Stops game if deck has no cards. 
                    if(deck1.deckLength() == 0){ 
                    System.out.println("No More Cards in the Deck!");
                        break; 
                    } 
                    //Stops game if players hands have no
                    //cards. 
                    else if(player.handLength() == 0){ 
                        System.out.println("No more cards in " + player.getName() + "'s hand!");
                    }
                    else if(allPlayers.get(nextPlayer).handLength() == 0){ 
                        System.out.println("No more cards in " + allPlayers.get(nextPlayer).getName() + "'s hand!");
                    }
                    //Updates the player to the right. 
                    nextPlayer++;
                    if(nextPlayer == 4){ 
                        nextPlayer = 0; 
                    }
                    continueGame(); 

                } 
                //Checks if conditions of ending the game
                //meet again to break out of the overall
                //while loop. 
                if(deck1.deckLength() == 0){ 
                    break; 
                } 
                else if(allPlayers.get(0).handLength() == 0 || allPlayers.get(1).handLength() == 0 || allPlayers.get(2).handLength() == 0 || allPlayers.get(3).handLength() == 0){ 
                    break; 
                }
                //Updates player to the right. 
                nextPlayer = 1; 
            }

            //Checks what the max number of pairs of 4 is.   
            int maxPairs = 0; 
            String name = " "; 
            for(Player player : allPlayers){ 
                if(maxPairs <= player.getPairs()){ 
                    maxPairs = player.getPairs(); 
                    name = player.getName(); 
                }
            }   

            //Checks if no one made a pair, then no one wins.
            if(maxPairs == 0){ 
                System.out.println("No Winner!");
                System.out.println("No one made a pair!"); 
            }
            else{ 
                //Makes a arraylist with all the players who 
                //have pairs equal to the greatest number 
                //(checks for ties). 
                ArrayList<Player> winners = new ArrayList<Player>(); 
                for(Player player : allPlayers){ 
                    if(player.getPairs() == maxPairs){ 
                        winners.add(player); 
                    }
                }
                //If only one person has the max pairs, he/she wins.  
                if(winners.size() == 1){ 
                    System.out.println("The winner is : "); 
                    System.out.println(winners.get(0).getName() + " with " + winners.get(0).getPairs() + " pair!!!");
                }
                //If more than one person has the max pairs, 
                //all the players with the max pairs win. 
                else{ 
                    System.out.println("It's a tie!"); 
                    System.out.println("The winners are : "); 
                    for(Player player : winners){ 
                        System.out.print(player.getName() + " with " + player.getPairs() + " pair!!!"); 
                        System.out.print("\n"); 
                    }
                }
            }
        }
        else{
            System.out.println("Exited."); 
        }
    }

    public static void continueGame(){ 
        //Allows the game to be kept track of, by only 
        //allowing the computer players to take their turns 
        //when the user is ready. 
        Scanner in = new Scanner(System.in); 
        String answer = ""; 
        while(true){ 
            System.out.println(" "); 
            System.out.println("Are you updated with whats going on in the game? ");
            System.out.println("Continue? ... (1 = YES) "); 
            answer = in.nextLine(); 
            if(answer.equals("1")){ 
                break; 
            }
        } 
    }
}