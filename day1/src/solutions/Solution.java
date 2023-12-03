package solutions;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Solution
 */
public class Solution {
    public static void solution1() {
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            int sum = 0;
            while (myScanner.hasNextLine()) {
                String s = myScanner.nextLine();
                int fnum = -1, snum = 0;
                for (char c : s.toCharArray()) {
                    if (c >= '0' && c <= '9') {
                        snum = c - '0';
                        if (fnum == -1) {
                            fnum = c - '0';
                        }
                    }
                }
                sum += fnum * 10;
                sum += snum;
            }
            System.out.println(sum);
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void solution2() {
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            int sum = 0;
            String digits[] = { "one", "1", "two", "2", "three", "3", "four", "4", "five", "5", "six", "6",
                    "seven", "7", "eight", "8", "nine", "9" };
            while (myScanner.hasNextLine()) {
                String input = myScanner.nextLine();
                int fnum = 0, snum = 0;
                int fnum_idx = 9999, snum_idx = -1;
                int idx = 1;
                for (String s : digits) {
                    if (input.indexOf(s) != -1 && input.indexOf(s) < fnum_idx) {
                        fnum_idx = input.indexOf(s);
                        fnum = (idx + 1) / 2;
                    }
                    if (input.lastIndexOf(s) > snum_idx) {
                        snum_idx = input.lastIndexOf(s);
                        snum = (idx + 1) / 2;
                    }
                    idx++;
                }
                System.out.println(fnum + " " + snum);
                sum += fnum * 10;
                sum += snum;
            }
            System.out.println(sum);
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void solution21() {
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            int sum = 0;
            String digits_spelled[] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
            String digits[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            while (myScanner.hasNextLine()) {
                String input = myScanner.nextLine();
                int fnum = 0, snum = 0;
                int fnum_idx = 9999, snum_idx = -1;
                int idx = 1;
                for (String s : digits) {
                    if (input.indexOf(s) != -1 && input.indexOf(s) < fnum_idx) {
                        fnum_idx = input.indexOf(s);
                        fnum = idx;
                    }
                    if (input.lastIndexOf(s) > snum_idx) {
                        snum_idx = input.lastIndexOf(s);
                        snum = idx;
                    }
                    idx++;
                }
                idx = 1;
                for (String s : digits_spelled) {
                    if (input.indexOf(s) != -1 && input.indexOf(s) < fnum_idx) {
                        fnum_idx = input.indexOf(s);
                        fnum = idx;
                    }
                    if (input.lastIndexOf(s) > snum_idx) {
                        snum_idx = input.lastIndexOf(s);
                        snum = idx;
                    }
                    idx++;
                }
                System.out.println(fnum + " " + snum);
                sum += fnum * 10;
                sum += snum;
            }
            System.out.println(sum);
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution2();
        solution21();
    }
}
