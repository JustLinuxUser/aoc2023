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
    public static class pipeConnectors {
        public static class Symbol {
            char s;
            List<Coord> crds;

            Symbol(char s, int x1, int y1, int x2, int y2) {
                this.s = s;
                crds = new ArrayList<>();
                Coord c1 = new Coord();
                Coord c2 = new Coord();
                c1.x = x1;
                c1.y = y1;
                c2.x = x2;
                c2.y = y2;
                crds.add(c1);
                crds.add(c2);
            }

            Symbol() {
            }

            public static class Coord {
                int x;
                int y;

                Coord() {
                }

                Coord(int x, int y) {
                    this.x = x;
                    this.y = y;
                }
            }
        }

        List<Symbol> symbols;

        pipeConnectors() {
            symbols = new ArrayList<>();
            symbols.add(new Symbol('|', 0, -1, 0, 1));
            symbols.add(new Symbol('-', -1, 0, 1, 0));
            symbols.add(new Symbol('L', 0, -1, 1, 0));
            symbols.add(new Symbol('F', 0, 1, 1, 0));
            symbols.add(new Symbol('7', -1, 0, 0, 1));
            symbols.add(new Symbol('J', 0, -1, -1, 0));
            Symbol s = new Symbol();
            List<Symbol.Coord> s_cords = new ArrayList<>();
            s_cords.add(new Symbol.Coord(1, 0));
            s_cords.add(new Symbol.Coord(0, 1));
            s_cords.add(new Symbol.Coord(0, -1));
            s_cords.add(new Symbol.Coord(-1, 0));
            s.crds = s_cords;
            s.s = 'S';
            symbols.addLast(s);
        }

        public Symbol GetSymbol(char s) {
            for (var symbol : symbols) {
                if (symbol.s == s)
                    return symbol;
            }
            return null;
        }
    };

    public static void solution1() {
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
            int curr_x = 0, curr_y = 0;
            int prev_x = 0, prev_y = 0;
            pipeConnectors conns = new pipeConnectors();
            pipeConnectors.Symbol curr_S = null;
            int width = file.get(0).size();
            int height = file.size();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (file.get(i).get(j) == 'S') {
                        curr_x = j;
                        curr_y = i;
                        break;
                    }
                }
            }
            curr_S = conns.GetSymbol('S');
            System.out.println(curr_x);
            System.out.println(curr_y);
            int count = 0;
            do {
                System.out.println("Size: " + curr_S.crds.size());
                find_next: for (var curr_cords : curr_S.crds) {
                    if (curr_cords.x + curr_x < 0 || curr_cords.x + curr_x >= width) {
                        System.out.println("X out of bounds");
                        continue;
                    }
                    if (curr_cords.y + curr_y < 0 || curr_cords.y + curr_y >= width) {
                        System.out.println("Y out of bounds");
                        continue;
                    }
                    if (curr_cords.x + curr_x == prev_x && curr_cords.y + curr_y == prev_y) {
                        System.out.println("Going back");
                        continue;
                    }
                    System.out.println(curr_cords.x + " " + curr_cords.y);
                    System.out.println("full_cords: " + curr_x + " " + curr_y);
                    char s = file.get(curr_y + curr_cords.y).get(curr_x + curr_cords.x);
                    pipeConnectors.Symbol sym = conns.GetSymbol(s);
                    if (sym == null) {
                        System.exit(-1);

                    }
                    System.out.println("trying: " + s);
                    for (var coord : sym.crds) {
                        if (coord.x == -curr_cords.x && coord.y == -curr_cords.y) {
                            curr_S = sym;
                            System.out.println("next is :" + sym.s);
                            prev_x = curr_x;
                            prev_y = curr_y;

                            curr_x += -coord.x;
                            curr_y += -coord.y;
                            break find_next;
                        }
                    }
                }
                System.out.println("aaa");
                count++;
            } while (curr_S.s != 'S');
            System.out.println(count / 2);

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
            int curr_x = 0, curr_y = 0;
            int prev_x = 0, prev_y = 0;
            pipeConnectors conns = new pipeConnectors();
            pipeConnectors.Symbol curr_S = null;
            int width = file.get(0).size();
            int height = file.size();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (file.get(i).get(j) == 'S') {
                        curr_x = j;
                        curr_y = i;
                        break;
                    }
                }
            }
            curr_S = conns.GetSymbol('S');
            // System.out.println(curr_x);
            // System.out.println(curr_y);
            int count = 0;
            do {
                // System.out.println("Size: " + curr_S.crds.size());
                find_next: for (var curr_cords : curr_S.crds) {
                    if (curr_cords.x + curr_x < 0 || curr_cords.x + curr_x >= width) {
                        // System.out.println("X out of bounds");
                        continue;
                    }
                    if (curr_cords.y + curr_y < 0 || curr_cords.y + curr_y >= width) {
                        // System.out.println("Y out of bounds");
                        continue;
                    }
                    if (curr_cords.x + curr_x == prev_x && curr_cords.y + curr_y == prev_y) {
                        // System.out.println("Going back");
                        continue;
                    }
                    // System.out.println(curr_cords.x + " " + curr_cords.y);
                    // System.out.println("full_cords: " + curr_x + " " + curr_y);
                    char s = file.get(curr_y + curr_cords.y).get(curr_x + curr_cords.x);
                    pipeConnectors.Symbol sym = conns.GetSymbol(s);
                    if (sym == null) {
                        // System.out.println("Exiting, bec of: " + s);
                        for (var line : file) {

                            for (var c : line) {
                                // System.out.print(c);
                            }
                            System.out.println();
                        }
                        System.exit(-1);

                    }
                    // System.out.println("trying: " + s);
                    for (var coord : sym.crds) {
                        if (coord.x == -curr_cords.x && coord.y == -curr_cords.y) {
                            // System.out.println("####current is: " + curr_S.s);
                            // System.out.println("###next is :" + sym.s);
                            if (curr_S.s != 'S')
                                file.get(curr_y).set(curr_x, '#');
                            if (curr_S.s == '7' || curr_S.s == 'L')
                                file.get(curr_y).set(curr_x, '&');
                            curr_S = sym;
                            prev_x = curr_x;
                            prev_y = curr_y;

                            curr_x += -coord.x;
                            curr_y += -coord.y;
                            break find_next;
                        }
                    }
                }
                // System.out.println("aaa");
                count++;
            } while (curr_S.s != 'S');
            file.get(curr_y).set(curr_x, '#');
            // System.out.println(count / 2);
            // for (var line : file) {
            // for (var c : line) {
            // System.out.print(c);
            // }
            // System.out.println();
            // }
            long inside = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int x_start = j - 1000;
                    int y_start = i - 1000;
                    int intersections = 0;
                    if (file.get(i).get(j) == '#')
                        continue;
                    if (file.get(i).get(j) == '&')
                        continue;
                    for (; x_start < j; x_start++, y_start++) {
                        if (x_start < 0 || y_start < 0)
                            continue;
                        if (file.get(y_start).get(x_start) == '#') {
                            intersections++;
                        }
                    }
                    if (intersections % 2 != 0) {
                        inside++;
                        // System.out.println(j + " " + i);
                    }
                }
            }
            System.out.println(inside);

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
