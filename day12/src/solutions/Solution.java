package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Solution
 */
public class Solution {
    static Hashtable<String, Long> ht = new Hashtable<>();

    public static long count(char[] cfg, int[] nums) {
        // System.out.print("Called count with: ");
        String hk = "";
        for (char c : cfg)
            hk += c;

        hk += " ";
        for (int i : nums)
            hk += i + ",";
        // System.out.println(hk);
        long result = 0;
        if (cfg.length == 0)
            if (nums.length == 0) {
                return 1;
            } else {
                return 0;
            }
        if (nums.length == 0) {
            for (int i = 0; i < cfg.length; i++) {
                if (cfg[i] == '#') {
                    return 0;
                }
            }
            return 1; // sus, we can still have ??
        }
        Long res = ht.get(hk);
        if (res != null)
            return res;

        int num_hashes_or_questions = 0;

        for (int i = 0; i < cfg.length; i++) {
            if (cfg[i] == '.')
                break;
            else
                num_hashes_or_questions++;
        }

        if (cfg[0] == '?' || cfg[0] == '.') {
            result += count(Arrays.copyOfRange(cfg, 1, cfg.length), nums);
        }

        if (cfg[0] == '?' || cfg[0] == '#') {
            if (num_hashes_or_questions >= nums[0]
                    && (cfg.length == nums[0] || cfg[nums[0]] != '#')) {

                char[] new_cfg = Arrays.copyOfRange(cfg, nums[0], cfg.length);
                if (cfg.length != nums[0]) {
                    new_cfg[0] = '.';
                }
                result += count(new_cfg, Arrays.copyOfRange(nums, 1, nums.length));
            }
        }
        ht.put(hk, result);
        return result;
    }

    public static void solution1() {
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            long total = 0;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                char[] springs = line.split(" ")[0].toCharArray();
                int[] broken_list = Arrays.stream(line.split(" ")[1].split(","))
                        .map(i -> Integer.valueOf(i).intValue())
                        .mapToInt(Integer::intValue)
                        .toArray();
                ht.clear();
                long poss = count(springs, broken_list);
                System.out.println(poss);
                total += poss;
            }
            System.out.println(total);

            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void solution2() {
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            long total = 0;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                String springs_s = line.split(" ")[0];
                springs_s = springs_s + "?" + springs_s + "?" + springs_s + "?" + springs_s + "?" + springs_s;
                char[] springs = springs_s.toCharArray();
                String broken_list_s = line.split(" ")[1];
                broken_list_s = broken_list_s + "," + broken_list_s + ","
                        + broken_list_s + "," + broken_list_s + ","
                        + broken_list_s;
                int[] broken_list = Arrays.stream(broken_list_s.split(","))
                        .map(i -> Integer.valueOf(i).intValue())
                        .mapToInt(Integer::intValue)
                        .toArray();
                ht.clear();
                long poss = count(springs, broken_list);
                System.out.println(poss);
                total += poss;
            }
            System.out.println(total);

            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution2();
    }

}
