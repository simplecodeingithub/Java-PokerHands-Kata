# Java-PokerHands-Kata

# Poker Hands Comparison Problem

## Problem Description:

The task is to compare several pairs of poker hands and determine which, if any, has the higher rank.

- A poker deck contains 52 cards, each with a suit (clubs, diamonds, hearts, or spades) and a value (2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, or ace).
- A poker hand consists of five cards dealt from the deck, and hands are ranked based on their value.

A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked by the following partial order from lowest to highest.

- High Card: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards have the same value, the hands are ranked by the next highest, and so on.
- Pair: 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
- Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
- Three of a Kind: Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards.
- Straight: Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
- Flush: Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
- Full House: 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
- Four of a kind: 4 cards with the same value. Ranked by the value of the 4 cards.
- Straight flush: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.

## Get Started

Implement a program that can read in input describing a pair of poker hands, determine which hand has the higher rank, and output the result.

Implement logic for evaluating the value of poker hands based on the rules described above.

## Input Format

- Each line of input will describe a pair of poker hands, with cards separated by spaces. 
- The first five cards will be for the first player, and the second five cards will be for the second player. 
- Each card is represented by two characters: the first character represents the card value, and the second character represents the card suit. For example, "2H" represents the two of hearts.

## Output Format

- For each line of input, output the result of the comparison as either "Black wins", "White wins", or "Tie". 
- If one hand has a higher rank than the other, include the reason for the higher rank in parentheses. For example, "Black wins (with a full house)".

## Sample TestCases

### Sample input:

Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH

Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S

Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH

Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH

### Sample output:

White wins. - with high card: Ace 

Black wins. - with full house: 4 over 2 

Black wins. - with high card: 9

Tie.
