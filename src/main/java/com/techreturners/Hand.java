package com.techreturners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.techreturners.RankType.*;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private RankType rankType;
    private Rank highestRank;
    private List<Card> winningCards;

    public Hand(List<Card> cards) {
        this.cards = cards;
        Collections.sort(this.cards, Collections.reverseOrder());
        calculateRank();
    }

    public void calculateRank() {
        Collections.sort(cards);
        if (isRoyalFlush()) {
            rankType = RankType.ROYAL_FLUSH;
            highestRank = Rank.ACE;
        } else if (isStraightFlush()) {
            rankType = RankType.STRAIGHT_FLUSH;
            highestRank = getHighestRank();
        } else if (isFourOfAKind()) {
            rankType = RankType.FOUR_OF_A_KIND;
            highestRank = getHighestRankOfNKind(4);
        } else if (isFullHouse()) {
            rankType = RankType.FULL_HOUSE;
            highestRank = getHighestRankOfNKind(3);
        } else if (isFlush()) {
            rankType = RankType.FLUSH;
            highestRank = getHighestRank();
        } else if (isStraight()) {
            rankType = RankType.STRAIGHT;
            highestRank = getHighestRank();
        } else if (isFourOfAKind()) {
            rankType = RankType.FOUR_OF_A_KIND;
            highestRank = getHighestRankOfNKind(4);
        } else if (isThreeOfAKind()) {
            rankType = THREE_OF_A_KIND;
            highestRank = getHighestRankOfNKind(3);
        } else if (isTwoPair()) {
            rankType = TWO_PAIR;
            highestRank = getHighestRankOfNKind(2);
        } else if (isOnePair()) {
            rankType = ONE_PAIR;
            highestRank = getHighestRankOfNKind(2);
        } else {
            rankType = RankType.HIGH_CARD;
            highestRank = getHighestRank();
        }
    }

    private boolean isRoyalFlush() {
        if (!isStraightFlush()) {
            return false;
        }
        return cards.get(0).getRank() == Rank.ACE;
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isFourOfAKind() {
        return getNKindCount(4) == 1;
    }

    private boolean isFullHouse() {
        return getNKindCount(3) == 1 && getNKindCount(2) == 1;
    }

    private boolean isFlush() {
        Suit suit = cards.get(0).getSuit();
        return cards.stream().allMatch(card -> card.getSuit() == suit);
    }

    private boolean isStraight() {
        for (int i = 1; i < this.cards.size(); i++) {
            Rank currentRank = Rank.fromSymbol(this.cards.get(i).getRank().getSymbol());
            Rank prevRank = Rank.fromSymbol(this.cards.get(i - 1).getRank().getSymbol());
            if (currentRank.ordinal() != prevRank.ordinal() + 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind() {
        return getNKindCount(3) == 1;
    }

    private boolean isTwoPair() {
        return getNKindCount(2) == 2;
    }

    private boolean isOnePair() {
        return getNKindCount(2) == 1;
    }

    private Rank getHighestRankOfNKind(int n) {
        for (int i = 0; i <= cards.size() - n; i++) {
            Rank rank = cards.get(i).getRank();
            if (cards.subList(i + 1, i + n).stream().allMatch(card -> card.getRank() == rank)) {
                return rank;
            }
        }
        return null;
    }

    private int getNKindCount(int n) {
        int count = 0;
        for (int i = 0; i <= cards.size() - n; i++) {
            int finalI = i;
            if (cards.subList(i + 1, i + n).stream().allMatch(card -> card.getRank() == cards.get(finalI).getRank())) {
                count++;
                i += n - 1;
            }
        }
        return count;
    }

    private Rank getHighestRank() {
        return cards.get(0).getRank();
    }

    public RankType getRankType() {
        return rankType;
    }

    public Rank getHighestRankOfType() {
        return highestRank;
    }

    @Override
    public int compareTo(Hand other) {
        int result = getRankType().compareTo(other.getRankType());
        if (result != 0) {
            return result;
        }
        result = getHighestRankOfType().compareTo(other.getHighestRankOfType());
        if (result != 0) {
            return result;
        }
        for (int i = 0; i < cards.size(); i++) {
            result = cards.get(i).getRank().compareTo(other.cards.get(i).getRank());
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return cards.toString() + " (" + rankType.toString() + ")";
    }

    public String getWinningCombination() {
        switch (this.rankType) {
            case STRAIGHT_FLUSH:
                return "straight flush";
            case FOUR_OF_A_KIND:
                return "four of a kind";
            case FULL_HOUSE:
                return "full house";
            case FLUSH:
                return "flush";
            case STRAIGHT:
                return "straight";
            case THREE_OF_A_KIND:
                return "three of a kind";
            case TWO_PAIR:
                return "two pair";
            case ONE_PAIR:
                return "one pair";
            default:
                return "high card";
        }
    }

    public Rank getHighCard() {
        return Collections.max(cards).getRank();
    }

    public List<String> getWinningCardNames() {
        List<Card> winningCards = getWinningCards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : winningCards) {
            cardNames.add(card.getName());
        }
        return cardNames;
    }

    private List<Card> getWinningCards() {
        return cards;
    }


}
