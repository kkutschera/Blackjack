import java.util.ArrayList;


public class CardGame{
  public static void main(String[] args){
    //Game actions
    CardPile gameDeck = CardPile.makeFullDeck();
    //Number of Players Input
    int numPlayers = Integer.parseInt(args[0]);
    //Creating Hands
    CardPile[] playersHands = new CardPile[numPlayers];
    for(int i=0; i<numPlayers; i++){
     playersHands[i] = new CardPile();
    }
    
    //Filling Hands
    int indexOfGameDeck = 0;
    //Empties gameDeck into players hands
    while(!gameDeck.isEmpty()){
      for(int j=0; j<playersHands.length; j++){
        Card dealingCard = gameDeck.remove(indexOfGameDeck);;
        playersHands[j].addToBottom(dealingCard);
        if(gameDeck.isEmpty()){
          break;
        }
      }
    }
    
    //Find Ace of Spades
    for(int i=0; i<numPlayers; i++){
      int indexOfAce = playersHands[i].find(Suit.SPADES, Value.ACE);
      if(indexOfAce != -1){
        //Outputs player number starting at 1 (not zero) since the average person will start counting at 1)
        System.out.println("Player " + (i+1) + " is the winner! You have the Ace of Spades!");
      }
    }
  }
}