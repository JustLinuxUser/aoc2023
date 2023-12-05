package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
            File inputFile = new File("input_d5");
            Scanner myScanner = new Scanner(inputFile);
            List<Long> seeds = Arrays.asList(myScanner.nextLine()
                    .split(":")[1]
                    .split("\\s+")).stream()
                    .filter(s -> !s.isEmpty())
                    .map(s -> Long.valueOf(s))
                    .collect(Collectors.toList());

            List<List<range>> Maps = new ArrayList<>();

            int idx_map = -1;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                if (line.trim().isEmpty()) {
                    // skip empty line and the map description
                    if (myScanner.hasNextLine()) {
                        Maps.add(new ArrayList<>());
                        idx_map++;
                        myScanner.nextLine();
                    } else
                        break; // EOF
                    continue;
                }
                String[] nums = line.split("\\s+");
                range r = new range();
                r.dest_start = Long.valueOf(nums[0]);
                r.source_start = Long.valueOf(nums[1]);
                r.length = Long.valueOf(nums[2]);
                Maps.get(idx_map).add(r);
            }
            /*
             * for (List<range> map : Maps) {
             * System.out.println("\nMap: ");
             * for (range r : map) {
             * System.out.println(r.dest_start + " " + r.source_start + " " + r.length);
             * }
             * }
             */
            long min = -1;
            for (long seed : seeds) {
                System.out.println("Seed: " + seed);
                long curr = seed;
                for (List<range> map : Maps) {
                    for (range r : map) {
                        if (curr >= r.source_start && curr <= r.source_start + r.length) {
                            curr = curr - r.source_start + r.dest_start;
                            break;
                        }
                    }
                    System.out.println("New curr: " + curr);
                }
                if (curr < min || min == -1) {
                    min = curr;
                }
            }
            System.out.println(min);
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
            File inputFile = new File("input_d5");
            Scanner myScanner = new Scanner(inputFile);
            List<Long> seeds_seq = Arrays.asList(myScanner.nextLine()
                    .split(":")[1]
                    .split("\\s+")).stream()
                    .filter(s -> !s.isEmpty())
                    .map(s -> Long.valueOf(s))
                    .collect(Collectors.toList());

            List<List<Long>> seed_ranges = new ArrayList<>();
            for (int i = 0; i < seeds_seq.size(); i += 2) {
                List<Long> l = new ArrayList<>();
                l.add(seeds_seq.get(i));
                l.add(seeds_seq.get(i + 1));
                seed_ranges.add(l);
            }

            List<List<range>> Maps = new ArrayList<>();

            int idx_map = -1;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                if (line.trim().isEmpty()) {
                    // skip empty line and the map description
                    if (myScanner.hasNextLine()) {
                        Maps.add(new ArrayList<>());
                        idx_map++;
                        myScanner.nextLine();
                    } else
                        break; // EOF
                    continue;
                }
                String[] nums = line.split("\\s+");
                range r = new range();
                r.dest_start = Long.valueOf(nums[0]);
                r.source_start = Long.valueOf(nums[1]);
                r.length = Long.valueOf(nums[2]);
                Maps.get(idx_map).add(r);
            }
            /*
             * for (List<range> map : Maps) {
             * System.out.println("\nMap: ");
             * for (range r : map) {
             * System.out.println(r.dest_start + " " + r.source_start + " " + r.length);
             * }
             * }
             */
            long min = -1;
            for (List<Long> seed_range : seed_ranges) {
                long range_start = seed_range.get(0);
                long range_len = seed_range.get(1);
                System.out.println("Starting new range, len: " + range_len);
                Instant t = Instant.now();
                for (long i = range_start; i < range_start + range_len; i++) {
                    // System.out.println("Seed: " + seed);
                    long curr = i;
                    for (List<range> map : Maps) {
                        for (range r : map) {
                            if (curr >= r.source_start && curr <= r.source_start + r.length) {
                                curr = curr - r.source_start + r.dest_start;
                                break;
                            }
                        }
                        // System.out.println("New curr: " + curr);
                    }
                    if (curr < min || min == -1) {
                        min = curr;
                    }
                }
                Instant t_2 = Instant.now();
                System.out.println("Took " + Duration.between(t, t_2).toMillis() + " millis");
            }
            System.out.println(min);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void solution2_no_lento() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input_d5");
            Scanner myScanner = new Scanner(inputFile);
            List<Long> seeds_seq = Arrays.asList(myScanner.nextLine()
                    .split(":")[1]
                    .split("\\s+")).stream()
                    .filter(s -> !s.isEmpty())
                    .map(s -> Long.valueOf(s))
                    .collect(Collectors.toList());

            List<List<Long>> seed_ranges = new ArrayList<>();
            for (int i = 0; i < seeds_seq.size(); i += 2) {
                List<Long> l = new ArrayList<>();
                l.add(seeds_seq.get(i));
                l.add(seeds_seq.get(i + 1));
                seed_ranges.add(l);
            }

            List<List<range>> Maps = new ArrayList<>();

            int idx_map = -1;
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                if (line.trim().isEmpty()) {
                    // skip empty line and the map description
                    if (myScanner.hasNextLine()) {
                        Maps.add(new ArrayList<>());
                        idx_map++;
                        myScanner.nextLine();
                    } else
                        break; // EOF
                    continue;
                }
                String[] nums = line.split("\\s+");
                range r = new range();
                r.dest_start = Long.valueOf(nums[0]);
                r.source_start = Long.valueOf(nums[1]);
                r.length = Long.valueOf(nums[2]);
                Maps.get(idx_map).add(r);
            }
            /*
             * for (List<range> map : Maps) {
             * System.out.println("\nMap: ");
             * for (range r : map) {
             * System.out.println(r.dest_start + " " + r.source_start + " " + r.length);
             * }
             * }
             */
            long min = -1;
            List<Long> seeds = new ArrayList<>();
            for (List<Long> seed_range : seed_ranges) {
                long range_start = seed_range.get(0);
                long range_len = seed_range.get(1);
                System.out.println("starting new range, len: " + range_len);
                Instant t = Instant.now();
                long frame = 50000;
                for (int j = 0; j <= range_len / frame; j++) {
                    long i = 0;
                    for (; i < frame && j * frame + i < range_len; i++) {
                        seeds.add(i + j * frame + range_start);
                    }
                    for (List<range> map : Maps) {
                        for (range r : map) {
                            for (int seed_idx = 0; seed_idx < i; seed_idx++) {
                                long curr = seeds.get(seed_idx);
                                if (curr >= r.source_start && curr <= r.source_start + r.length) {
                                    seeds.set(seed_idx, curr - r.source_start + r.dest_start);
                                    break;
                                }
                            }
                        }
                        for (int seed_idx = 0; seed_idx < i; seed_idx++) {
                            long curr = seeds.get(seed_idx);
                            if (curr < min || curr == -1) {
                                min = curr;
                            }
                        }
                    }
                }
                Instant t_2 = Instant.now();
                System.out.println("took " + Duration.between(t, t_2).toMillis() +
                        " millis");
                System.out.println(min);
            }
            System.out.println(min);

            /*
             * System.out.println("Starting new range, len: " + range_len);
             * Instant t = Instant.now();
             * for (long i = range_start; i < range_start + range_len; i++) {
             * // System.out.println("Seed: " + seed);
             * long curr = i;
             * for (List<range> map : Maps) {
             * for (range r : map) {
             * if (curr >= r.source_start && curr <= r.source_start + r.length) {
             * curr = curr - r.source_start + r.dest_start;
             * break;
             * }
             * }
             * // System.out.println("New curr: " + curr);
             * }
             * if (curr < min || min == -1) {
             * min = curr;
             * }
             * }
             * Instant t_2 = Instant.now();
             * System.out.println("Took " + Duration.between(t, t_2).toMillis() +
             * " millis");
             * System.out.println(min);
             */
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
