package com.techreturners;

 public enum Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),//jack is 11
    QUEEN("Q"),//12
    KING("K"),//13
    ACE("A");//14
    private final String symbol;
    Rank(String symbol) {
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Rank fromSymbol(String symbol) {
        for (Rank rank : Rank.values()) {
            if (rank.symbol.equals(symbol)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Invalid rank symbol: " + symbol);
    }
}
