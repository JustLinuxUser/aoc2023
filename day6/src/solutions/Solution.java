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

    static class range {
        long dest_start;
        long source_start;
        long length;
    }

    public static void solution1() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d6");
            Scanner myScanner = new Scanner(inputFile);
            String[] times_s = myScanner.nextLine().split(":")[1].split("\\s+");
            List<Integer> times = new ArrayList<>();
            for (String time : times_s) {
                if (time.trim().isEmpty())
                    continue;
                times.add(Integer.valueOf(time));
            }
            String[] distances_s = myScanner.nextLine().split(":")[1].split("\\s+");
            List<Integer> distances = new ArrayList<>();
            for (String distance : distances_s) {
                if (distance.trim().isEmpty())
                    continue;
                distances.add(Integer.valueOf(distance));
            }
            System.out.println(times);
            System.out.println(distances);

            long total = 1;
            for (int i = 0; i < times.size(); i++) {
                int time = times.get(i);
                int record = distances.get(i);
                int curr = 0;
                for (; curr * (time - curr) <= record; curr++)
                    ;
                int winning_opts = 0;
                int curr1 = curr;
                for (; curr * (time - curr) > record; curr++)
                    winning_opts++;
                System.out.println("1) " + curr1 + " 2) " + (curr1 + winning_opts));
                System.out.println(winning_opts);
                total *= winning_opts;

            }
            System.out.println(total);

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
            File inputFile = new File("input_d6");
            Scanner myScanner = new Scanner(inputFile);
            String times_s = myScanner.nextLine().split(":")[1];
            long time = times_s.chars().filter(c -> c >= '0' && c <= '9').reduce(0,
                    (subtotal, c) -> subtotal = subtotal * 10 + c - '0');
            System.out.println(time);

            String distances_s = myScanner.nextLine().split(":")[1];
            char[] distances_c = distances_s.toCharArray();
            long record = 0;
            for (char c : distances_c) {
                if (c >= '0' && c <= '9')
                    record = record * 10 + c - '0';
            }
            System.out.println(record);

            long total = 1;
            int curr = 0;
            int curr2 = 0;
            // record = (time - curr) * curr
            // record = time*curr - curr^2
            // curr^2 - time*curr + record = 0;
            // D = time^2 - 4*record;
            // curr1 = (1 + sqrt(D))/2
            long D = time * time - 4 * record;
            curr = (int) Math.ceil((time - Math.sqrt(D)) / 2);
            System.out.println("Record: " + record + " time at curr1 " + ((time - curr) * curr));
            curr2 = (int) Math.floor((time + Math.sqrt(D)) / 2);
            System.out.println("Record: " + record + " time at curr2 " + ((time - curr2) * curr2));
            System.out.println("1: " + curr + " 2: " + curr2);

            int winning_opts = curr2 - curr + 1;
            System.out.println(winning_opts); // because the range is inclusive
            total *= winning_opts;

            System.out.println(total);

            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution1();
        solution2();
    }

}
