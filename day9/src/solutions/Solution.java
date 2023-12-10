package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Solution
 */
public class Solution {

    static class range {
        long dest_start;
        long source_start;
        long length;
    }

    public static void solution1() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            long sum = 0;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                List<List<Long>> triangle = new ArrayList<>();
                List<Long> lLine = Arrays.stream(line.split(" ")).filter(i -> !(i.trim().isEmpty()))
                        .map(i -> Long.valueOf(i)).toList();
                triangle.addLast(lLine);

                int depth = 1;
                boolean solved = false;
                while (!solved) {
                    solved = true;
                    List<Long> currLine = new ArrayList<>();
                    for (int i = 1; i < triangle.get(depth - 1).size(); i++) {
                        long a = triangle.get(depth - 1).get(i - 1);
                        long b = triangle.get(depth - 1).get(i);
                        if (b - a != 0) {
                            solved = false;
                        }
                        currLine.addLast(b - a);
                    }
                    triangle.add(currLine);
                    depth++;
                }
                int idx = 0;
                /*
                 * for (List<Long> level : triangle) {
                 * for (int i = 0; i < idx; i++)
                 * System.out.print(" ");
                 * for (long i : level) {
                 * System.out.print(i + " ");
                 * }
                 * idx++;
                 * System.out.println();
                 * }
                 */
                long diff_above = 0;
                for (int i = triangle.size() - 2; i >= 0; i--) {
                    List<Long> curr_line = triangle.get(i);
                    diff_above += curr_line.getLast();
                }
                System.out.println(diff_above);
                sum += diff_above;
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
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            long sum = 0;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                List<List<Long>> triangle = new ArrayList<>();
                List<Long> lLine = Arrays.stream(line.split(" ")).filter(i -> !(i.trim().isEmpty()))
                        .map(i -> Long.valueOf(i)).toList();
                triangle.addLast(lLine);
                int depth = 1;
                boolean solved = false;
                while (!solved) {
                    solved = true;
                    List<Long> currLine = new ArrayList<>();
                    for (int i = 1; i < triangle.get(depth - 1).size(); i++) {
                        long a = triangle.get(depth - 1).get(i - 1);
                        long b = triangle.get(depth - 1).get(i);
                        if (b - a != 0) {
                            solved = false;
                        }
                        currLine.addLast(b - a);
                    }
                    triangle.add(currLine);
                    depth++;
                }
                int idx = 0;

                /*
                 * for (List<Long> level : triangle) {
                 * for (int i = 0; i < idx; i++)
                 * System.out.print(" ");
                 * for (long i : level) {
                 * System.out.print(i + " ");
                 * }
                 * idx++;
                 * System.out.println();
                 * }
                 */
                long diff_above = 0;
                for (int i = triangle.size() - 2; i >= 0; i--) {
                    List<Long> curr_line = triangle.get(i);
                    diff_above -= curr_line.getFirst();
                    diff_above *= -1;
                }
                System.out.println(diff_above);
                sum += diff_above;
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
