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
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            long total = 0;
            List<char[]> segment = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                int height = segment.size();
                int width = 0;
                if (height != 0)
                    width = segment.get(0).length;
                if (line.strip().isEmpty()) {
                    for (int i = 1; i < width; i++) {
                        boolean found = true;
                        row: for (int offset = 1; i + offset - 1 < width && i - offset >= 0; offset++) {
                            for (int j = 0; j < height; j++) {
                                if (segment.get(j)[i + offset - 1] != segment.get(j)[i - offset]) {
                                    found = false;
                                    break row;
                                }
                            }
                        }
                        if (found) {
                            System.out.println(i);
                            total += i;
                            break;
                        }
                    }
                    for (int i = 1; i < height; i++) {
                        boolean found = true;
                        row: for (int offset = 1; i + offset - 1 < height && i - offset >= 0; offset++) {
                            for (int j = 0; j < width; j++) {
                                if (segment.get(i + offset - 1)[j] != segment.get(i - offset)[j]) {
                                    found = false;
                                    break row;
                                }

                            }
                        }
                        if (found) {
                            System.out.println(i);
                            total += i * 100;
                            break;
                        }
                    }
                    segment.clear();
                } else {
                    segment.add(line.toCharArray());
                }
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
            List<char[]> segment = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                int height = segment.size();
                int width = 0;
                if (height != 0)
                    width = segment.get(0).length;
                if (line.strip().isEmpty()) {
                    for (int i = 1; i < width; i++) {
                        int smudges = 0;
                        row: for (int offset = 1; i + offset - 1 < width && i - offset >= 0; offset++) {
                            for (int j = 0; j < height; j++) {
                                if (segment.get(j)[i + offset - 1] != segment.get(j)[i - offset]) {
                                    if (++smudges > 1)
                                        break row;
                                }
                            }
                        }
                        if (smudges == 1) {
                            System.out.println(i);
                            total += i;
                            break;
                        }
                    }
                    for (int i = 1; i < height; i++) {
                        int smudges = 0;
                        row: for (int offset = 1; i + offset - 1 < height && i - offset >= 0; offset++) {
                            for (int j = 0; j < width; j++) {
                                if (segment.get(i + offset - 1)[j] != segment.get(i - offset)[j]) {
                                    if (++smudges > 1) {
                                        break row;
                                    }
                                }

                            }
                        }
                        if (smudges == 1) {
                            System.out.println(i);
                            total += i * 100;
                            break;
                        }
                    }
                    segment.clear();
                } else {
                    segment.add(line.toCharArray());
                }
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
