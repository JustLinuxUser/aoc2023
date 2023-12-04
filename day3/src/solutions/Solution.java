package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import solutions.Solution.coord;

import solutions.Solution.coord;

/**
 * Solution
 */
public class Solution {

    public static void solution1() {
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
                                    System.out.println("top: " + (j + k) + " " + (i - 1));
                                    is_part = true;
                                }
                            }

                        for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                            char curr = matrix.get(i).charAt(j + k);
                            if ((curr < '0' || curr > '9') && curr != '.') {
                                System.out.println("middle: " + (k + j) + " " + i);
                                is_part = true;
                            }
                        }
                        if (touching_bottom == 0)
                            for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                                char curr = matrix.get(i + 1).charAt(j + k);
                                if ((curr < '0' || curr > '9') && curr != '.') {
                                    System.out.println("bottom: " + (k + j) + " " + (i + 1));
                                    is_part = true;
                                }
                            }
                        if (is_part) {
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

            }

            System.out.println(sum);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    class coord {
        int x;
        int y;

        public coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof coord))
                return false;
            coord o_c = (coord) o;
            if (o_c.x == this.x && o_c.y == this.y)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            String s = String.valueOf(this.x) + " " + String.valueOf(this.y);
            return s.hashCode();
        }
    }

    class star_nums {
        int n = 0;
        int mul = 1;
    }

    public static void solution2() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d3");
            Scanner myScanner = new Scanner(inputFile);
            ArrayList<String> matrix = new ArrayList<>();
            long sum = 0;
            coord test_hash = S.new coord(1, 2);
            System.out.println(test_hash.hashCode());

            while (myScanner.hasNextLine()) {
                matrix.add(myScanner.nextLine());
            }
            HashMap<coord, star_nums> s_nums = new HashMap<>();
            for (int i = 0; i < matrix.size(); i++) {
                boolean is_int = false;
                int int_idx = 0;
                HashSet<coord> star_cords = new HashSet<>();
                for (int j = 0; j < matrix.get(0).length(); j++) {
                    int touching_left = 0;
                    int touching_right = 0;
                    int touching_top = 0;
                    int touching_bottom = 0;
                    char c = matrix.get(i).charAt(j);
                    int next_c;
                    if (matrix.get(0).length() - 1 != j) {
                        next_c = matrix.get(i).charAt(j + 1);
                    } else {
                        next_c = -1;
                    }
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
                                if (curr == '*') {
                                    System.out.println("top: " + k + " " + i);
                                    star_cords.add(S.new coord(j + k, i - 1));
                                }
                            }

                        for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                            char curr = matrix.get(i).charAt(j + k);
                            if (curr == '*') {
                                System.out.println("middle: " + k + " " + i);
                                star_cords.add(S.new coord(j + k, i));
                            }
                        }
                        if (touching_bottom == 0)
                            for (int k = -1 + touching_left; k <= 1 - touching_right; k++) {
                                char curr = matrix.get(i + 1).charAt(j + k);
                                if (curr == '*') {
                                    System.out.println("bottom: " + k + " " + i);
                                    star_cords.add(S.new coord(j + k, i + 1));
                                }
                            }
                        if ((next_c == -1 || next_c < '0' || next_c > '9')) {
                            int num = Integer.parseInt(matrix.get(i).substring(int_idx, j + 1));
                            System.out.println("Final int= " + num);
                            for (coord star_cords2 : star_cords) {
                                star_nums sn = s_nums.get(star_cords2);
                                if (sn == null) {
                                    sn = S.new star_nums();
                                } else {
                                    System.out.println("Got something!!");
                                }
                                sn.n++;
                                sn.mul *= num;
                                s_nums.put(star_cords2, sn);
                            }
                            is_int = false;
                            star_cords.clear();
                        }
                    }
                }
            }
            for (star_nums sNums : s_nums.values()) {
                System.out.println(sNums.mul + " " + sNums.n);
                if (sNums.n == 2)
                    sum += sNums.mul;
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
        solution2();
    }
}
