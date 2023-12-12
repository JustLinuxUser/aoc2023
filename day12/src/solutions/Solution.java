package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Solution
 */
public class Solution {

    public static List<Integer> CalculateList(char[] springs) {

        List<Integer> ret = new ArrayList<>();
        int count_bad = 0;
        for (int i = 0; i < springs.length; i++) {
            char c = springs[i];
            if (c == '&' || c == '#') {
                count_bad++;
            } else {
                if (count_bad != 0) {
                    ret.addLast(count_bad);
                    count_bad = 0;
                }
            }
        }
        if (count_bad != 0)
            ret.addLast(count_bad);
        return ret;
    }

    public static void solution1() {
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            long total = 0;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                char[] springs = line.split(" ")[0].toCharArray();
                List<Integer> broken_list = Arrays.stream(line.split(" ")[1].split(","))
                        .map(s -> Integer.valueOf(s))
                        .collect(Collectors.toList());
                for (int i = 0; i < springs.length; i++) {
                    if (springs[i] == '?') {
                        springs[i] = ',';
                    }
                }
                boolean finished = false;
                int count = 0;
                List<Integer> lret = CalculateList(springs);
                if (lret.equals(broken_list))
                    count++;
                // for (int i = 0; i < broken_list.size(); i++) {
                // System.out.print(broken_list.get(i) + ", ");
                // }
                // System.out.println();
                while (!finished) {
                    {
                        int i = 0;
                        for (; i < springs.length; i++) {
                            if (springs[i] == ',') {
                                springs[i] = '&';
                                break;
                            }
                        }
                        if (i == springs.length)
                            finished = true;
                        if (!finished) {
                            for (i--; i >= 0; i--) {
                                if (springs[i] == '&') {
                                    springs[i] = ',';
                                }
                            }
                            lret = CalculateList(springs);
                            if (lret.equals(broken_list))
                                count++;
                        }
                    }
                    // System.out.println(springs);
                    // System.out.println("lol");
                    // for (int i = 0; i < lret.size(); i++) {
                    // System.out.print(lret.get(i) + ", ");
                    // }
                    // System.out.println();
                    // System.out.println("count: " + count);
                    // System.out.println("lol");

                }
                System.out.println(count);
                total += count;
            }
            System.out.println(total);

            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution1();
    }

}
