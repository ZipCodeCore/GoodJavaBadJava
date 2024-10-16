// Level 1

/******************************************************************************
 *  Compilation:  javac GoodDeal.java
 *  Execution:    java GoodDeal players
 *
 *  Deal 5-card hands at random to the given number of players.
 *
 *  % java Deal 3
 *  4 of Spades
 *  9 of Spades
 *  Ace of Hearts
 *  9 of Clubs
 *  9 of Diamonds
 *
 *  6 of Spades
 *  10 of Hearts
 *  Queen of Hearts
 *  8 of Hearts
 *  King of Spades
 *
 *  7 of Hearts
 *  8 of Diamonds
 *  Queen of Spades
 *  3 of Spades
 *  4 of Diamonds
 *
 ******************************************************************************/



 // This needs to be Re-Factor'd
 // This is a simple program that deals 5-card hands at random to the given number of players.
 // It needs to be generalized, so that it can be used for any number of players and any number of cards per player.
 // Also the blocks of code in `main` should be broken down into methods.
// the code is not well-organized, and it is not clear what the code does.
// the SUITS and RANKS arrays should be declared as constants. And outside of the main method.


public class GoodDeal {

    // instance variables
    private int cardsPerPlayer = 5;

    private final String[] SUITS = {
        "Clubs", "Diamonds", "Hearts", "Spades"
    };

    private final String[] RANKS = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "Jack", "Queen", "King", "Ace"
    };
    private String[] deck = {};
    private int deckSize = SUITS.length * RANKS.length;
    private int players;

    public static void main(String[] args) {

        // number of players
        int players = 0;
        
        if (args.length > 0) {
            players = Integer.parseInt(args[0]);
        } else {
            players = 5;
        }
        // what does this ^^^^ mean?
        GoodDeal deal = new GoodDeal(players);

        deal.shuffle();
        deal. printDeck();

    }

    public GoodDeal() {
        this(5);
    } // need a nullary constructor

    public GoodDeal(int players){
        this.players = players;
        deck = new String[deckSize];

        if (cardsPerPlayer * players > deckSize)
            throw new RuntimeException("Too many players, not enough cards");


        // initialize deck
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                deck[SUITS.length*i + j] = RANKS[i] + " of " + SUITS[j];
            }
        }
    }

    public void shuffle(){
        for (int i = 0; i < deckSize; i++) {
            int r = i + (int) (Math.random() * (deckSize - i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < players * cardsPerPlayer; i++) {
            sb.append(deck[i]);
            sb.append("\n");
            if (i % cardsPerPlayer == cardsPerPlayer - 1)
                sb.append("\n");
        }
        return sb.toString();
    }   

    public void printDeck(){
        System.out.println(this.toString());
    }
}
