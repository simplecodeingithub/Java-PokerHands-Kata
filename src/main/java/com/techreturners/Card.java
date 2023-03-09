package com.techreturners;

public class Card implements  Comparable<Card>{
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    @Override
    public int compareTo(Card other) {
        return this.rank.compareTo(other.rank);
    }
    public String getName(){
        String rankName=this.rank.toString();
        String suitName=this.suit.toString();
        return rankName + " of " + suitName;
    }
}
