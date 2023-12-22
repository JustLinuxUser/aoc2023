package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import solutions.Solution.cell.type;

/**
 * Solution
 */
public class Solution {
    static Hashtable<String, Long> ht = new Hashtable<>();

    class cell {
        public enum type {
            MIRROR_RIGHT,
            MIRROR_LEFT,
            SPLITTER_VERT,
            SPLITTER_HOR,
            NORMAL
        }

        type t;

        boolean lleft = false;
        boolean lright = false;
        boolean ldown = false;
        boolean lup = false;

        int dirx = 0;
        int diry = 0;
        int x;
        int y;

        cell(type t, int x, int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }

        cell(cell c) {
            this.t = c.t;
            this.lleft = c.lleft;
            this.lright = c.lright;
            this.lup = c.lup;
            this.ldown = c.ldown;
            this.dirx = c.dirx;
            this.diry = c.diry;
            this.x = c.x;
            this.y = c.y;
        }
    }

    public void solution1() {
        try {
            File inputFile = new File("input");
            Scanner myScanner = new Scanner(inputFile);
            int total = 0;
            List<List<cell>> field = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                List<cell> row_c = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == '.')
                        row_c.add(new cell(type.NORMAL, i, field.size()));
                    else if (c == '/')
                        row_c.add(new cell(type.MIRROR_RIGHT, i, field.size()));
                    else if (c == '\\')
                        row_c.add(new cell(type.MIRROR_LEFT, i, field.size()));
                    else if (c == '|')
                        row_c.add(new cell(type.SPLITTER_VERT, i, field.size()));
                    else if (c == '-')
                        row_c.add(new cell(type.SPLITTER_HOR, i, field.size()));
                    else {
                        System.out.println("FUCK!!! WE DONE GOOFED!!!: " + c);
                        System.exit(-1);
                    }
                }
                field.add(row_c);
            }
            int dirx;
            int diry;
            int x = 0;
            int y = 0;
            int width = field.get(0).size();
            int height = field.size();
            List<cell> stack = new ArrayList<>();
            field.get(0).get(0).dirx = 1;
            field.get(0).get(0).diry = 0;
            stack.add(field.get(0).get(0));
            while (stack.size() != 0) {
                cell el = stack.removeFirst();
                dirx = el.dirx;
                diry = el.diry;
                x = el.x + dirx;
                y = el.y + diry;
                while (true) {
                    if (x < 0 || x >= width || y < 0 || y >= height) {
                        System.out.println("Out of bounds");
                        break;
                    }
                    el = field.get(y).get(x);
                    System.out.println("dx: " + dirx + " dy: " + diry);
                    System.out.println("x: " + x + " y: " + y);
                    System.out.println("EL: x: " + el.x + " y: " + el.y);

                    if (dirx == 1) {
                        if (!el.lright)
                            el.lright = true;
                        else {
                            System.out.println("right");
                            break;
                        }
                    }
                    if (dirx == -1)
                        if (!el.lleft)
                            el.lleft = true;
                        else {
                            System.out.println("left");
                            break;
                        }
                    if (diry == 1)
                        if (!el.ldown)
                            el.ldown = true;
                        else {
                            System.out.println("down");
                            break;
                        }
                    if (diry == -1)
                        if (!el.lup)
                            el.lup = true;
                        else {
                            System.out.println("Up");
                            break;
                        }

                    if (el.t == type.MIRROR_LEFT) {
                        cell c = new cell(el);
                        c.dirx = -el.diry;
                        c.diry = -el.dirx;
                        stack.add(c);
                        System.out.println("Mirror _left");
                        break;
                    }
                    if (el.t == type.MIRROR_RIGHT) {
                        cell c = new cell(el);
                        c.dirx = el.diry;
                        c.diry = el.dirx;
                        stack.add(c);
                        System.out.println("Mirror _right");
                        break;
                    }
                    if (el.t == type.SPLITTER_HOR) {
                        cell c = new cell(el);
                        c.dirx = 1;
                        c.diry = 0;
                        stack.add(c);
                        c = new cell(el);
                        c.dirx = -1;
                        c.diry = 0;
                        stack.add(c);
                        System.out.println("hor split");
                        break;
                    }
                    if (el.t == type.SPLITTER_VERT) {
                        cell c = new cell(el);
                        c.dirx = 0;
                        c.diry = 1;
                        stack.add(c);
                        c = new cell(el);
                        c.dirx = 0;
                        c.diry = -1;
                        stack.add(c);
                        System.out.println("vert split");
                        break;
                    }
                    x += dirx;
                    y += diry;
                    total++;
                    System.out.println("Finished loop");
                }
            }

            System.out.println(total);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Solution().solution1();
    }

}
