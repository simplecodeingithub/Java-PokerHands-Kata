package com.techreturners;

public enum Suit {
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C"),
    SPADES("S");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Suit fromSymbol(String symbol) {
        for (Suit suit : Suit.values()) {
            if (suit.symbol.equals(symbol)) {
                return suit;
            }
        }
        throw new IllegalArgumentException("Invalid suit symbol: " + symbol);
    }
}
