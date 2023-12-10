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
            File inputFile = new File("input_d8");
            Scanner myScanner = new Scanner(inputFile);
            String cmds = myScanner.nextLine().strip();
            HashMap<String, List<String>> hm = new HashMap<>();
            String curr = "AAA";
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                if (line.strip().isEmpty())
                    continue;
                String name = line.split(" = ")[0];
                String left = line.split(" = ")[1].split(", ")[0].split("\\(")[1];
                String right = line.split(" = ")[1].split(", ")[1].split("\\)")[0];

                System.out.println(name + ": " + left + " " + right);
                List<String> val = new ArrayList<>();
                val.addLast(left);
                val.addLast(right);
                hm.put(name, val);
            }
            int count = 0;
            while (!curr.equals("ZZZ")) {
                char curr_cmd = cmds.charAt(count % cmds.length());
                List<String> opts = hm.get(curr);
                // System.out.println("Curr: " + curr + ", " + opts.get(0) + " | " +
                // opts.get(1));
                if (curr_cmd == 'L')
                    curr = opts.get(0);
                else if (curr_cmd == 'R')
                    curr = opts.get(1);
                else
                    System.exit(-1);
                // System.out.println(count);
                count++;
            }
            System.out.println(count);

            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static long gcd(long n1, long n2) {
        if (n1 == n2)
            return n1;

        while (true) {
            long min = Math.min(n1, n2);
            long max = Math.max(n1, n2);
            if (max % min == 0)
                return min;
            n1 = max - min;
            n2 = min;
        }
    }

    public static long lcm(long[] n) {

        long lcm = 1;

        for (long num : n) {
            long g = gcd(num, lcm);
            lcm = lcm * num / g;
        }

        return lcm;
    }

    public static void solution2() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d8");
            Scanner myScanner = new Scanner(inputFile);
            String cmds = myScanner.nextLine().strip();
            HashMap<String, List<String>> hm = new HashMap<>();
            List<String> currents = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                if (line.strip().isEmpty())
                    continue;
                String name = line.split(" = ")[0];
                String left = line.split(" = ")[1].split(", ")[0].split("\\(")[1];
                String right = line.split(" = ")[1].split(", ")[1].split("\\)")[0];

                System.out.println(name + ": " + left + " " + right);
                List<String> val = new ArrayList<>();
                val.addLast(left);
                val.addLast(right);
                hm.put(name, val);
                if (name.charAt(2) == 'A')
                    currents.add(name);

            }
            List<Long> loop_lengths = new ArrayList<>();
            for (int i = 0; i < currents.size(); i++) {
                int count = 0;
                while (currents.get(i).charAt(2) != 'Z') {
                    char curr_cmd = cmds.charAt(count % cmds.length());
                    List<String> opts = hm.get(currents.get(i));
                    if (curr_cmd == 'L')
                        currents.set(i, opts.get(0));
                    else if (curr_cmd == 'R')
                        currents.set(i, opts.get(1));
                    else
                        System.exit(-1);
                    count++;
                }
                loop_lengths.add((long) count);
                System.out.println(count);
            }
            long[] loop_length_arr = loop_lengths.stream().mapToLong(i -> i).toArray();
            System.out.println(loop_length_arr[0]);
            System.out.println(lcm(loop_length_arr));

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
