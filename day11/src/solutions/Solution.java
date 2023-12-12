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
    static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void solution1() {
        Solution S = new Solution();
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            List<List<Character>> file = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                List<Character> line = myScanner.nextLine().chars().mapToObj(c -> (char) c)
                        .collect(Collectors.toList());
                file.addLast(line);
            }
            for (int i = 0; i < file.size(); i++) {
                boolean clean = true;
                for (int j = 0; j < file.get(0).size(); j++) {
                    if (file.get(i).get(j) != '.') {
                        clean = false;
                        break;
                    }
                }
                if (clean) {
                    file.add(i, new ArrayList<>(file.get(i)));
                    i++;
                }
            }
            for (int j = 0; j < file.get(0).size(); j++) {
                boolean clean = true;
                for (int i = 0; i < file.size(); i++) {
                    if (file.get(i).get(j) != '.') {
                        clean = false;
                        break;
                    }
                }
                if (clean) {
                    for (int i = 0; i < file.size(); i++) {
                        file.get(i).add(j, '.');
                    }
                    j++;
                }
            }
            for (var line : file) {
                for (var c : line) {
                    System.out.print(c);
                }
                System.out.println();
            }

            List<Coord> galaxies = new ArrayList<>();
            for (int i = 0; i < file.size(); i++) {
                for (int j = 0; j < file.get(0).size(); j++) {
                    if (file.get(i).get(j) == '#')
                        galaxies.addLast(new Coord(i, j));
                }
            }
            long sum = 0;
            for (int i = 0; i < galaxies.size() - 1; i++) {
                for (int j = i + 1; j < galaxies.size(); j++) {
                    int dist = 0;
                    var galax1 = galaxies.get(i);
                    var galax2 = galaxies.get(j);
                    dist += Math.abs(galaxies.get(i).x - galaxies.get(j).x);
                    dist += Math.abs(galaxies.get(i).y - galaxies.get(j).y);
                    // System.out.println("Dist between " + i + " " + j + " is " + dist);
                    System.out.println(galax1.x + " " + galax1.y);
                    System.out.println(galax2.x + " " + galax2.y);
                    System.out.println("Dist between " + i + " " + j + " is " + dist);
                    sum += dist;
                }
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
            List<List<Character>> file = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                List<Character> line = myScanner.nextLine().chars().mapToObj(c -> (char) c)
                        .collect(Collectors.toList());
                file.addLast(line);
            }
            List<Integer> vertical = new ArrayList<>();
            for (int i = 0; i < file.size(); i++) {
                int num_galax = 0;
                for (int j = 0; j < file.get(0).size(); j++) {
                    if (file.get(i).get(j) == '#') {
                        num_galax++;
                    }
                }
                if (num_galax == 0) {
                    vertical.addLast(-1);
                } else {
                    vertical.addLast(num_galax);
                }
            }
            List<Integer> horizontal = new ArrayList<>();
            for (int j = 0; j < file.get(0).size(); j++) {
                int num_galax = 0;
                for (int i = 0; i < file.size(); i++) {
                    if (file.get(i).get(j) == '#') {
                        num_galax++;
                    }
                }
                if (num_galax == 0) {
                    horizontal.addLast(-1);
                } else {
                    horizontal.addLast(num_galax);
                }
            }
            for (var line : file) {
                for (var c : line) {
                    System.out.print(c);
                }
                System.out.println();
            }

            List<Coord> galaxies = new ArrayList<>();
            for (int i = 0; i < file.size(); i++) {
                for (int j = 0; j < file.get(0).size(); j++) {
                    if (file.get(i).get(j) == '#')
                        galaxies.addLast(new Coord(j, i));
                }
            }
            long sum = 0;
            long expantion_rate = 1000000;
            for (int i = 0; i < galaxies.size() - 1; i++) {
                for (int j = i + 1; j < galaxies.size(); j++) {
                    int dist = 0;
                    var galax1 = galaxies.get(i);
                    var galax2 = galaxies.get(j);
                    for (int _i = Math.min(galax1.x, galax2.x); _i < Math.max(galax1.x, galax2.x); _i++) {
                        // System.out.println("Checking hor: " + _i);
                        if (horizontal.get(_i) != -1)
                            dist++;
                        else {
                            dist += expantion_rate;
                            // System.out.println("Adding hor expantion");
                        }

                    }
                    for (int _i = Math.min(galax1.y, galax2.y); _i < Math.max(galax1.y, galax2.y); _i++) {
                        if (vertical.get(_i) != -1)
                            dist++;
                        else {
                            dist += expantion_rate;
                            // System.out.println("Adding vertical expantion");
                        }

                    }
                    // System.out.println(galax1.x + " " + galax1.y);
                    // System.out.println(galax2.x + " " + galax2.y);
                    // System.out.println("Dist between " + i + " " + j + " is " + dist);
                    sum += dist;
                }
            }
            System.out.println(sum);
            // for (int i : horizontal)
            // System.out.println(i);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            // System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // solution1();
        solution2();
    }

}
