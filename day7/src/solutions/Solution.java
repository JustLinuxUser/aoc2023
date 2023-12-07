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

    public static void solution1() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                int bid = Integer.parseInt(line.split(" ")[1]);
                char[] deal_c = line.split(" ")[0].toCharArray();
                Deal deal = new Deal();
                String clases = "A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2";
                for (char c : deal_c) {
                    switch (c) {
                        case 'A': {
                            deal.cards[0]++;
                            break;
                        }
                        case 'K': {
                            deal.cards[1]++;
                            break;
                        }
                        case 'Q': {
                            deal.cards[2]++;
                            break;
                        }
                        case 'J': {
                            deal.cards[3]++;
                            break;
                        }
                        case 'T': {
                            deal.cards[4]++;
                            break;
                        }
                    }
                    if (c >= '2' && c <= '9') {
                        int idx = '9' - c + 5;
                        deal.cards[idx]++;
                    }
                }
                System.out.println(line);
                System.out.println(clases);
                for (int i : deal.cards) {
                    System.out.print(i + ", ");
                }
                System.out.println();
                int[] cards_copy = deal.cards.clone();
                Arrays.sort(cards_copy);
                for (int i = 0; i < cards_copy.length / 2; i++) {
                    int tmp = cards_copy[i];
                    cards_copy[i] = cards_copy[cards_copy.length - i - 1];
                    cards_copy[cards_copy.length - i - 1] = tmp;
                }
                for (int i : cards_copy) {
                    System.out.print(i + ", ");
                }
                System.out.println();
                if (cards_copy[0] == 5)
                    System.out.println("Five");
                else if (cards_copy[0] == 4)
                    System.out.println("Four");
                else if (cards_copy[0] == 3 && cards_copy[1] == 2)
                    System.out.println("Full house");
                else if (cards_copy[0] == 3)
                    System.out.println("Three");
                else if (cards_copy[0] == 2 && cards_copy[1] == 2)
                    System.out.println("Two pair");
                else if (cards_copy[0] == 2)
                    System.out.println("One pair");
                else
                    System.out.println("High card");
            }
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
