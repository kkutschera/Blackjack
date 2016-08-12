import java.util.ArrayList;
import java.util.Collections;

public class CardPile{
  public static void main(String[] args){
    //System.out.println(makeFullDeck(5));
  }
 
  //Attributes
  private ArrayList<Card> cards;
  private int numCards;
  
  //Constructor
  public CardPile(){
    this.cards = new ArrayList<Card>();
    this.numCards = 0;
  }
  
  //Getter for numCards
  public int getNumCards(){
    return this.numCards;
  }
  
  //Replaces first null spot with inputted card c
  public void addToBottom(Card c){
        this.cards.add(c);
        numCards++;
  }
  
  public boolean isEmpty(){
    return this.cards.isEmpty();
  }
  
  public Card get(int i){
   if(i<numCards){
      return this.cards.get(i);
    }
    return null;
  }

  public Card remove(int i){
    //Checks if a card exists
    if(i>this.cards.size()){
      return null;
    }
    
    Card value = this.cards.get(i);
    this.cards.remove(i);
   
    numCards--;
    return value; 
  }

  public int find(Suit s, Value v){
    for(int i=0; i<this.cards.size(); i++){
      if(this.cards.get(i) == null){
        continue;
      }
      if(this.cards.get(i).getSuit() == s && this.cards.get(i).getValue() == v){
        return i;
      }
    }
    return -1;
  }
  
  public String toString(){
    String output = "";
    for(int i=0; i<this.cards.size(); i++){
        output = output + i + "." + this.cards.get(i).toString() + " ";
    }
    return output;
  }
  
  public static CardPile makeFullDeck(){
    CardPile deck = new CardPile();
    //Setting ever card of the deck to an card with specific suit and value
      for(Suit j: Suit.values()){
        for(Value k: Value.values()){
          deck.cards.add(new Card(j,k));
        }
      }
    Collections.shuffle(deck.cards);
    deck.numCards = deck.cards.size();
    return deck;
  }
  
  public static CardPile makeFullDeck(int n){
    CardPile deck = new CardPile();
    for(int i=0; i<n; i++){
      for(Suit j: Suit.values()){
        for(Value k: Value.values()){
          deck.cards.add(new Card(j,k));
        }
      }
    }
    Collections.shuffle(deck.cards);
    deck.numCards = deck.cards.size();
    return deck;
  }
  
  
}
  











