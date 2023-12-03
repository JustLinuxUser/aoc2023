package solutions;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Solution
 */
public class Solution {

    public static void solution1() {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            ArrayList<String> matrix = new ArrayList<>();
            int sum = 0;

            while (myScanner.hasNextLine()) {
                matrix.add(myScanner.nextLine());
            }
            for (int i = 0; i < matrix.size(); i++) {
                boolean is_int = false;
                int int_idx = 0;
                boolean is_part = false;
                for (int j = 0; j < matrix.get(0).length(); j++) {
                    int touching_left = 0;
                    int touching_right = 0;
                    int touching_top = 0;
                    int touching_bottom = 0;
                    char c = matrix.get(i).charAt(j);
                    if (j == 0)
                        touching_left = 1;
                    if (j == matrix.get(0).length() - 1)
                        touching_right = 1;
                    if (i == 0)
                        touching_top = 1;
                    if (i == matrix.size() - 1)
                        touching_bottom = 1;
                    if (c >= '0' && c <= '9') {
                        if (!is_int) {
                            is_int = true;
                            int_idx = j;
                        }
                        // top line
                        if (touching_top == 0)
                            for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                                char curr = matrix.get(i - 1).charAt(j + k);
                                if ((curr < '0' || curr > '9') && curr != '.') {
                                    System.out.println("top: " + k + " " + i);
                                    is_part = true;
                                }
                            }

                        for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                            char curr = matrix.get(i).charAt(j + k);
                            if ((curr < '0' || curr > '9') && curr != '.') {
                                System.out.println("middle: " + k + " " + i);
                                is_part = true;
                            }
                        }
                        if (touching_bottom == 0)
                            for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                                char curr = matrix.get(i + 1).charAt(j + k);
                                if ((curr < '0' || curr > '9') && curr != '.') {
                                    System.out.println("bottom: " + k + " " + i);
                                    is_part = true;
                                }
                            }
                        if (is_part) {
                            System.out.println(c + " is a part component");
                        }
                    } else {
                        if (is_int && is_part) {
                            int num = Integer.parseInt(matrix.get(i).substring(int_idx, j));
                            System.out.println("Final int= " + num);
                            sum += num;
                        }
                        is_int = false;
                        is_part = false;
                    }
                }

                if (is_int && is_part) {
                    int num = Integer.parseInt(matrix.get(i).substring(int_idx, matrix.get(0).length()));
                    System.out.println("Final int= " + num);
                    sum += num;
                }
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
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            ArrayList<String> matrix = new ArrayList<>();
            int sum = 0;

            while (myScanner.hasNextLine()) {
                matrix.add(myScanner.nextLine());
            }
            for (int i = 0; i < matrix.size(); i++) {
                boolean is_int = false;
                int int_idx = 0;
                boolean is_part = false;
                for (int j = 0; j < matrix.get(0).length(); j++) {
                    int touching_left = 0;
                    int touching_right = 0;
                    int touching_top = 0;
                    int touching_bottom = 0;
                    char c = matrix.get(i).charAt(j);
                    if (j == 0)
                        touching_left = 1;
                    if (j == matrix.get(0).length() - 1)
                        touching_right = 1;
                    if (i == 0)
                        touching_top = 1;
                    if (i == matrix.size() - 1)
                        touching_bottom = 1;
                    if (c >= '0' && c <= '9') {
                        if (!is_int) {
                            is_int = true;
                            int_idx = j;
                        }
                        // top line
                        if (touching_top == 0)
                            for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                                char curr = matrix.get(i - 1).charAt(j + k);
                                if ((curr < '0' || curr > '9') && curr != '.') {
                                    System.out.println("top: " + k + " " + i);
                                    is_part = true;
                                }
                            }

                        for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                            char curr = matrix.get(i).charAt(j + k);
                            if ((curr < '0' || curr > '9') && curr != '.') {
                                System.out.println("middle: " + k + " " + i);
                                is_part = true;
                            }
                        }
                        if (touching_bottom == 0)
                            for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                                char curr = matrix.get(i + 1).charAt(j + k);
                                if ((curr < '0' || curr > '9') && curr != '.') {
                                    System.out.println("bottom: " + k + " " + i);
                                    is_part = true;
                                }
                            }
                        if (is_part) {
                            System.out.println(c + " is a part component");
                        }
                    } else {
                        if (is_int && is_part) {
                            int num = Integer.parseInt(matrix.get(i).substring(int_idx, j));
                            System.out.println("Final int= " + num);
                            sum += num;
                        }
                        is_int = false;
                        is_part = false;
                    }
                }

                if (is_int && is_part) {
                    int num = Integer.parseInt(matrix.get(i).substring(int_idx, matrix.get(0).length()));
                    System.out.println("Final int= " + num);
                    sum += num;
                }
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
