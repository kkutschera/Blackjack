import java.util.Scanner;

public class Blackjack{
  public static void main(String[] args){
    CardPile deck = CardPile.makeFullDeck(4);
    while(deck.getNumCards()>10){
      playRound(deck);
    }
  }
  
  public static void playRound(CardPile deck){
    //Make Player's Deck
    CardPile playersHand = new CardPile();
    //Make Dealer's Deck
    CardPile dealersHand = new CardPile();
    playersHand.addToBottom(deck.remove(0));
    dealersHand.addToBottom(deck.remove(0));
    playersHand.addToBottom(deck.remove(0));
    dealersHand.addToBottom(deck.remove(0));
    
    int playersScore = countValues(playersHand);
    int dealersScore = getScore(dealersHand.get(0));
    
    printInfoBefore(playersHand, dealersHand.get(0), playersScore, dealersScore);
    
    //blackjack
    if(playersScore == 21){
      System.out.println("BLACKJACK."+'\n');
      dealersScore = countValues(dealersHand);
      printInfoAfter(playersHand, dealersHand, playersScore, dealersScore);
      if(dealersScore != 21){
        System.out.println("You got a blackjack. You win!"+'\n'+"Round over."+'\n');
      }
      else{
        System.out.println("Tie." +'\n' + "Round over." +'\n');
      }
    }
    
    else{  
      //hit or stay    
      while(playersScore < 22 && willYouHit()){
        System.out.println("Hit.");
        playersHand.addToBottom(deck.remove(0));
        playersScore = countValues(playersHand);   
        printInfoBefore(playersHand, dealersHand.get(0), playersScore, dealersScore);
        System.out.println();
      }
      
      //outcomes
      if (playersScore > 21){
        dealersScore = countValues(dealersHand);
        printInfoAfter(playersHand, dealersHand, playersScore, dealersScore);
        System.out.println("Sorry, you busted.");
        System.out.println("Round over." +'\n');
      }
      
      else{
        System.out.println("Stay.");
        dealersScore = countValues(dealersHand);
        printInfoAfter(playersHand, dealersHand, playersScore, dealersScore);
        
        //if the dealer should hit.
        while(dealersScore<18 && dealersScore < 21){
          System.out.println("The dealer will hit.");
          dealersHand.addToBottom(deck.remove(0));
          dealersScore = countValues(dealersHand);  
          System.out.println();
          printInfoAfter(playersHand, dealersHand, playersScore, dealersScore);
        }
        
        //outcomes
        if (dealersScore > 21){
          System.out.println("The dealer busted. You win!"+'\n'+"Round over."+'\n');
        }
        else if (dealersScore < playersScore){
          System.out.println("You win!");
          System.out.println("Round over."+'\n');
        }
        else if(dealersScore > playersScore){
          System.out.println("The dealer won.");
          System.out.println("Round over."+'\n');
        }
        else if(dealersScore==playersScore){
          System.out.println("Tie.");
          System.out.println("Round over."+'\n');
        }
      }
    }
  }
 
  //hit or stay
  public static boolean willYouHit(){
    boolean hit = false;
    boolean goodUserInput = false;
    Scanner input = new Scanner(System.in);
    while(!goodUserInput){
      System.out.println("Would you like to hit or stay?");
      String answer = input.next();
      if(answer.equalsIgnoreCase("hit")){
        hit = true;
        return hit;
      }
      else if(answer.equalsIgnoreCase("stay")){
        return hit;
      }
      else{
        System.out.println("Illegal input: please try again.");
      }
    }
    return false;
  }
  
  
  
  //prints all relevant information
  public static void printInfoBefore(CardPile playersHand, Card dealersFirstCard, int playersScore, int dealersScore){
    System.out.println("Your hand: " + playersHand + '\n'+"Your score: " + playersScore);
    System.out.println("Dealer's card: " + dealersFirstCard +'\n'+ "Dealer's score: " + dealersScore);
  }
  
  public static void printInfoAfter(CardPile playersHand, CardPile dealersHand, int playersScore, int dealersScore){
    System.out.println("Your hand: " + playersHand + '\n'+"Your score: " + playersScore);
    System.out.println("Dealer's hand: " + dealersHand +'\n'+ "Dealer's score: " + dealersScore);
  }
  
  
  
  
  public static int countValues(CardPile input){
    int aceCounter = 0;
    int worth = 0;
    for(int i=0; i<input.getNumCards(); i++){
      if(input.get(i).getValue() == Value.ACE){
        aceCounter++;
      }
      worth = worth + getScore(input.get(i));
    }
    //makes use of aces dual value if over 21
    while(worth>21 && aceCounter>0){
      worth = worth - 10;
      aceCounter--;
    }
    return worth;
  }
  
  
  public static int getScore(Card input){
    if(input.getValue() == Value.ACE){
      return 11;
    }
    else if(input.getValue() == Value.TWO){
      return 2;
    }
    else if(input.getValue() == Value.THREE){
      return 3;
    }
    else if(input.getValue() == Value.FOUR){
      return 4;
    }
    else if(input.getValue() == Value.FIVE){
      return 5;
    }
    else if(input.getValue() == Value.SIX){
      return 6;
    }
    else if(input.getValue() == Value.SEVEN){
      return 7;
    }
    else if(input.getValue() == Value.EIGHT){
      return 8;
    }
    else if(input.getValue() == Value.NINE){
      return 9;
    }
    else if(input.getValue() == Value.TEN){
      return 10;
    }
    else if(input.getValue() == Value.JACK){
      return 10;
    }
    else if(input.getValue() == Value.QUEEN){
      return 10;
    }
    else if(input.getValue() == Value.KING){
      return 10;
    }
    return -1;
  }
}