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
            int total = 0;
            List<char[]> segment = new ArrayList<>();
            String line = myScanner.nextLine();
            String[] instructions = line.trim().split(",");
            myScanner.close();

            for (String instruction : instructions) {
                int curr = 0;
                for (int i = 0; i < instruction.length(); i++) {
                    curr += instruction.charAt(i);
                    curr *= 17;
                    curr %= 256;
                }
                total += curr;
            }
            System.out.println(total);

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static class Lens {
        String l;
        int f;

        Lens(String l, int f) {
            this.l = l;
            this.f = f;
        }
    }

    public static void solution2() {
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            int total = 0;
            List<char[]> segment = new ArrayList<>();
            String line = myScanner.nextLine();
            myScanner.close();
            String[] instructions = line.trim().split(",");
            ArrayList<Lens>[] s = new ArrayList[256];
            for (int i = 0; i < 256; i++) {
                s[i] = new ArrayList<>();
            }

            for (String instruction : instructions) {
                String name = instruction.split("=|-")[0];
                int curr = 0;
                for (int i = 0; i < name.length(); i++) {
                    curr += name.charAt(i);
                    curr *= 17;
                    curr %= 256;
                }
                if (instruction.indexOf('=') != -1) {
                    int f = Integer.valueOf(instruction.split("=")[1]);
                    boolean found = false;
                    for (int i = 0; i < s[curr].size(); i++) {
                        System.out.println("lol");
                        if (s[curr].get(i).l.equals(name)) {
                            s[curr].get(i).f = f;
                            found = true;
                        }
                    }
                    if (!found) {
                        s[curr].addLast(new Lens(name, f));
                    }
                } else {
                    for (int i = 0; i < s[curr].size(); i++) {
                        if (s[curr].get(i).l.equals(name)) {
                            s[curr].remove(i);
                            break;
                        }
                    }
                }

                System.out.println(instruction);
                for (int i = 0; i < 256; i++) {
                    if (s[i].size() == 0)
                        continue;
                    for (int j = 0; j < s[i].size(); j++) {
                        var l = s[i].get(j);
                        System.out.print(l.l + " " + l.f + "\t");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println();
            }
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < s[i].size(); j++) {
                    total += (i + 1) * (j + 1) * s[i].get(j).f;
                }
            }
            System.out.println(total);

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solution2();
    }

}
