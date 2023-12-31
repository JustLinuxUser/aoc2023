package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.Instant;

/**
 * Solution
 */
public class Solution {

    static class Deal {
        int[] cards = new int[13];
    }

    static class DealAndBid implements Comparable<DealAndBid> {
        char[] cards = new char[5];
        int bid;

        @Override
        public int compareTo(DealAndBid arg0) {
            return DeckCmp(this.cards, arg0.cards);
        }
    }

    static int CardValue(char c) {
        switch (c) {
            case 'A': {
                return 0;
            }
            case 'K': {
                return 1;
            }
            case 'Q': {
                return 2;
            }
            case 'J': {
                return 3;
            }
            case 'T': {
                return 4;
            }
            default: {
                int idx = '9' - c + 5;
                return idx;
            }
        }
    }

    static int DeckType(Deal d1) {
        if (d1.cards[0] == 5)
            return 7;
        else if (d1.cards[0] == 4)
            return 6;
        else if (d1.cards[0] == 3 && d1.cards[1] == 2)
            return 5;
        else if (d1.cards[0] == 3)
            return 4;
        else if (d1.cards[0] == 2 && d1.cards[1] == 2)
            return 3;
        else if (d1.cards[0] == 2)
            return 2;
        else
            return 1;
    }

    static Deal DeckToDeal(char[] d) {
        Deal deal = new Deal();
        for (char c : d) {
            deal.cards[CardValue(c)]++;
        }
        Arrays.sort(deal.cards);
        for (int i = 0; i < deal.cards.length / 2; i++) {
            int tmp = deal.cards[i];
            deal.cards[i] = deal.cards[deal.cards.length - i - 1];
            deal.cards[deal.cards.length - i - 1] = tmp;
        }
        return deal;
    }

    static int DeckCmp(char[] d1, char[] d2) {
        Deal deal1 = DeckToDeal(d1);
        Deal deal2 = DeckToDeal(d2);
        if (DeckType(deal1) != DeckType(deal2)) {
            return DeckType(deal1) - DeckType(deal2);
        }
        for (int i = 0; i < d1.length; i++) {
            if (CardValue(d1[i]) != CardValue(d2[i])) // because it works in reverse
                return CardValue(d2[i]) - CardValue(d1[i]);
        }
        return 0;
    }

    public static void solution1() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d7");
            Scanner myScanner = new Scanner(inputFile);
            List<DealAndBid> L = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                int bid = Integer.parseInt(line.split(" ")[1]);
                char[] deal_c = line.split(" ")[0].toCharArray();
                DealAndBid dab = new DealAndBid();
                dab.bid = bid;
                dab.cards = deal_c;
                L.add(dab);
            }
            Collections.sort(L);
            long sum = 0;
            for (int i = 0; i < L.size(); i++) {
                DealAndBid dab = L.get(i);
                sum += dab.bid * (i + 1);
                // System.out.println(dab.cards + " " + dab.bid + " * " + i + 1);
            }
            System.out.println(sum);
            myScanner.close();

        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution1();
    }
}
