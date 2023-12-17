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

    public static void solution1() {
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            long total = 0;
            List<char[]> segment = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                segment.addLast(line.toCharArray());
            }
            for (int i = 0; i < segment.size(); i++) {
                for (int j = 0; j < segment.get(0).length; j++) {
                    if (segment.get(i)[j] == 'O') {
                        int k = i;
                        System.out.println(k);
                        while (k >= 1) {
                            k--;
                            if (segment.get(k)[j] != '.') {
                                k++;
                                break;
                            }
                        }
                        segment.get(i)[j] = '.';
                        segment.get(k)[j] = 'O';
                        total += segment.size() - k;
                    }
                }
            }
            for (var line : segment) {
                System.out.println(line);
            }
            System.out.println();
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
            List<char[]> segment = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                segment.addLast(line.toCharArray());
            }
            boolean done = false;
            int iter = 0;
            // north
            List<List<char[]>> segment_history = new ArrayList<>();
            while (!done) {
                iter++;
                List<char[]> segment_prev = new ArrayList<>(segment);
                for (int i = 0; i < segment_prev.size(); i++) {
                    segment_prev.set(i, segment_prev.get(i).clone());
                }
                segment_history.add(segment_prev);
                total = 0;
                for (int i = 0; i < segment.size(); i++) {
                    for (int j = 0; j < segment.get(0).length; j++) {
                        if (segment.get(i)[j] == 'O') {
                            int k = i;
                            while (k >= 1) {
                                k--;
                                if (segment.get(k)[j] != '.') {
                                    k++;
                                    break;
                                }
                            }
                            segment.get(i)[j] = '.';
                            segment.get(k)[j] = 'O';
                        }
                    }
                }
                // west

                for (int i = 0; i < segment.get(0).length; i++) {
                    for (int j = 0; j < segment.size(); j++) {
                        if (segment.get(j)[i] == 'O') {
                            int k = i;
                            while (k >= 1) {
                                k--;
                                if (segment.get(j)[k] != '.') {
                                    k++;
                                    break;
                                }
                            }
                            segment.get(j)[i] = '.';
                            segment.get(j)[k] = 'O';
                        }
                    }
                }
                // south
                for (int i = segment.size() - 1; i >= 0; i--) {
                    for (int j = 0; j < segment.get(0).length; j++) {
                        if (segment.get(i)[j] == 'O') {
                            int k = i;
                            while (k < segment.size() - 1) {
                                k++;
                                if (segment.get(k)[j] != '.') {
                                    k--;
                                    break;
                                }
                            }
                            segment.get(i)[j] = '.';
                            segment.get(k)[j] = 'O';
                        }
                    }
                }
                // east
                for (int i = segment.get(0).length - 1; i >= 0; i--) {
                    for (int j = 0; j < segment.size(); j++) {
                        if (segment.get(j)[i] == 'O') {
                            int k = i;
                            while (k < segment.get(0).length - 1) {
                                k++;
                                if (segment.get(j)[k] != '.') {
                                    k--;
                                    break;
                                }
                            }
                            segment.get(j)[i] = '.';
                            segment.get(j)[k] = 'O';
                        }
                    }
                }
                // for (var line : segment) {
                // System.out.println(line);
                // }
                // System.out.println();
                // System.out.println(segment_history.size());
                for (int l = 0; l < segment_history.size(); l++) {
                    List<char[]> segment_curr = segment_history.get(l);
                    done = true;
                    check_eq: for (int i = 0; i < segment.size(); i++) {
                        for (int j = 0; j < segment.get(0).length; j++) {
                            if (segment.get(i)[j] != segment_curr.get(i)[j]) {
                                done = false;
                                break check_eq;
                            }
                        }
                    }
                    if (done) {
                        System.out.println(iter);
                        System.out.println(l);
                        int b = 1_000_000_000;
                        b = b - iter;
                        int idx = b % (iter - l);
                        segment_curr = segment_history.get(idx + l);
                        for (int i = 0; i < segment.size(); i++) {
                            for (int j = 0; j < segment.get(0).length; j++) {
                                if (segment_curr.get(i)[j] == 'O') {
                                    total += segment.size() - i;
                                }
                            }
                        }
                        System.out.println(total);
                        break;
                    }
                }
                // System.out.println(iter);
            }

            System.out.println();
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
