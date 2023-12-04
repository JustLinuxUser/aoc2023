package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution
 */
public class Solution {

    public static void solution1() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d4");
            Scanner myScanner = new Scanner(inputFile);
            ArrayList<String> matrix = new ArrayList<>();

            while (myScanner.hasNextLine()) {
                matrix.add(myScanner.nextLine());
            }
            int sum = 0;

            for (String line : matrix) {
                // Card 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53
                int total_value = 0;
                String winning[] = line.split(":")[1].split("\\|")[0].split("\\s+");
                String my_nums = line.split(":")[1].split("\\|")[1];
                for (String num : winning) {
                    if (!num.isEmpty() && my_nums.indexOf(" " + num + " ") != -1) {
                        System.out.println("Match");
                        total_value = total_value == 0 ? 1 : total_value * 2;
                    }
                }
                sum += total_value;
                System.out.println(total_value);
            }
            System.out.println(sum);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void solution2() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d4");
            Scanner myScanner = new Scanner(inputFile);
            ArrayList<String> matrix = new ArrayList<>();

            while (myScanner.hasNextLine()) {
                matrix.add(myScanner.nextLine());
            }
            int sum = 0;

            int idx = 0;
            int cards_won[] = new int[201];
            for (String line : matrix) {
                // Card 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53
                int total_value = 0;
                String winning[] = line.split(":")[1].split("\\|")[0].split("\\s+");
                String my_nums = line.split(":")[1].split("\\|")[1];
                for (String num : winning) {
                    if (!num.isEmpty() && my_nums.indexOf(" " + num + " ") != -1) {
                        System.out.println("Match " + idx);
                        total_value++;
                    }
                }
                System.out.println("Idx: " + idx + " total:" + total_value);
                int j = total_value;
                while (j > 0) {
                    cards_won[j + idx] += 1 + cards_won[idx];
                    j--;
                }
                idx++;
                int z = 0;
                for (int i : cards_won) {
                    if (z++ > 10)
                        break;
                    System.out.println(i);
                    // sum += i;
                }
            }
            for (int i : cards_won) {
                // System.out.println(i);
                sum += i;
            }
            sum += matrix.size();
            System.out.println("sum = " + sum);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution2();
    }
}
