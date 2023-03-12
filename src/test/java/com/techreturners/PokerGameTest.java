package com.techreturners;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerGameTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    public void testPokerGame(String blackCardsString, String whiteCardsString, String expectedOutput) {
        Card[] blackCards = parseCardsString(blackCardsString);
        Card[] whiteCards = parseCardsString(whiteCardsString);
        Hand blackHand = new Hand(Arrays.asList(blackCards));
        Hand whiteHand = new Hand(Arrays.asList(whiteCards));
        blackHand.calculateRank(); // add this line to calculate rank
        whiteHand.calculateRank();
        int result = blackHand.compareTo(whiteHand);
        String winningCombination = "";
        if (result > 0) {
             winningCombination = blackHand.getWinningCombination();
            if (winningCombination.isEmpty()) {
                assertEquals("Black wins.", expectedOutput);
            } else {
                String winningCardNames = blackHand.getWinningCardNames().toString();
                String expectedWinningOutput = "Black wins. - with " + winningCombination + ":" + winningCardNames;
               // assertEquals(expectedWinningOutput, expectedOutput);
            }
        } else if (result < 0) {
             winningCombination = whiteHand.getWinningCombination();
            if (winningCombination.isEmpty()) {
                String highCardName=result > 0 ? blackHand.getHighCard().getSymbol() : whiteHand.getHighCard().getSymbol();
                assertEquals((result > 0 ? "Black" : "White") + " wins. - with high card: " + (result > 0 ? blackHand.getHighCard() : whiteHand.getHighCard()), expectedOutput);
            } else {
               // List<String> winningCardNames = whiteHand.getWinningCardNames();
                //assertEquals((result > 0 ? "Black" : "White") + " wins. - with " + winningCombination, expectedOutput);
            }
        } else {
            assertEquals("Tie", expectedOutput);
        }
        System.out.println(blackCardsString + "," + whiteCardsString + "," + expectedOutput);
    }

    private Card[] parseCardsString(String cardsString) {
        String[] cardsArray = cardsString.split(" ");
        Card[] cards = new Card[cardsArray.length];
        for (int i = 0; i < cardsArray.length; i++) {
            String cardString = cardsArray[i];
            Rank rank;
            Suit suit;
            if (cardString.length() == 2) {
                char rankChar = cardString.charAt(0);
                try {
                    rank = Rank.fromSymbol(String.valueOf(rankChar));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid rank symbol: " + rankChar);
                    return null;
                }
                try {
                    suit = Suit.fromSymbol(String.valueOf(cardString.charAt(1)));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid suit symbol: " + cardString.charAt(1));
                    return null;
                }
            } else {
                try {
                    rank = Rank.fromSymbol(cardString.substring(0, 1));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid rank symbol: " + cardString.substring(0, 1));
                    return null;
                }
                try {
                    suit = Suit.fromSymbol(String.valueOf(cardString.charAt(2)));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid suit symbol: " + cardString.charAt(2));
                    return null;
                }
            }
            cards[i] = new Card(rank, suit);
        }
        return cards;
    }
}