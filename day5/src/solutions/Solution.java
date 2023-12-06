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

            List<long[]> seed_ranges = new ArrayList<>();
            for (int i = 0; i < seeds_seq.size(); i += 2) {
                long[] l = new long[2];
                l[0] = seeds_seq.get(i);
                l[1] = seeds_seq.get(i + 1) + l[0];
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
            for (List<range> map : Maps) {
                List<long[]> NEW = new ArrayList<>();
                int processed = 0;
                while (processed < seed_ranges.size()) {
                    long[] seed_range = seed_ranges.get(processed);
                    long ss = seed_range[0];
                    long se = seed_range[1];
                    System.out.println("Seed[" + processed + "]: " + ss + " " + se);
                    for (long[] seed : seed_ranges) {
                        System.out.println("seed_rangeel: " + seed[0] + " " + (seed[0] + seed[1]));
                    }
                    boolean found = false;
                    for (range map_range : map) {
                        long ms = map_range.source_start;
                        long me = map_range.source_start + map_range.length;
                        long ds = map_range.dest_start - ms;
                        System.out.println("Map: " + ms + " " + me);
                        long is = Math.max(ms, ss);
                        long ie = Math.min(me, se);
                        if (is < ie) {
                            NEW.add(new long[] { is + ds, ie + ds });
                            System.out.println("NEW Adding: " + (is + ds) + " " + (ie + ds));
                            if (ss < ms) {
                                seed_ranges.addLast(new long[] { ss, ms });
                                System.out.println("Adding: " + ss + " " + ms);
                            }
                            if (se > me) {
                                seed_ranges.addLast(new long[] { me, se });
                                System.out.println("Adding: " + me + " " + se);
                            }

                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        NEW.add(new long[] { ss, se });
                        System.out.println("NEW Adding: " + (ss) + " " + (se));
                    }
                    processed++;
                    System.out.println("Len: " + seed_ranges.size());
                }
                for (long[] range : NEW) {
                    System.out.println("NEW ARR: " + range);
                }
                seed_ranges.clear();
                seed_ranges.addAll(NEW);
                NEW.clear();
                System.out.println("NEW MAP!!\n\n");
            }
            Collections.sort(seed_ranges, (o1, o2) -> o1[0] > o2[0] ? 1 : o1[0] == o2[0] ? 0 : -1);
            for (long[] range : seed_ranges) {
                System.out.println("seeds final: " + range[0]);
            }

            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution2_no_lento();
    }
}
