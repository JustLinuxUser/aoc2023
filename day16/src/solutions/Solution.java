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

    class cell {

        char t;

        int tracks[] = new int[] { 0, 0, 0, 0 };

        cell(char t) {
            this.t = t;
        }
    }

    class coord {
        int x;
        int y;
        // left right up down
        int dirs[] = new int[] { 0, 0, 0, 0 };
    }

    public void solution1() {
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            int total = 0;
            List<List<cell>> field = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                List<cell> row_c = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    row_c.add(new cell(c));
                }
                field.add(row_c);
            }
            int width = field.get(0).size();
            int height = field.size();
            List<coord> stack = new ArrayList<>();
            {
                coord c = new coord();
                c.x = -1;
                c.y = 0;
                c.dirs[1] = 1;
                stack.add(c);
            }
            big_loop: while (stack.size() != 0) {
                coord crd = stack.removeFirst();

                while (true) {
                    // left right up down
                    if (crd.dirs[0] == 1)
                        crd.x--;
                    if (crd.dirs[1] == 1)
                        crd.x++;
                    if (crd.dirs[2] == 1)
                        crd.y--;
                    if (crd.dirs[3] == 1)
                        crd.y++;
                    System.out.println("x: " + crd.x + " y: " + crd.y);
                    if (crd.x < 0 || crd.x >= width || crd.y < 0 || crd.y >= height) {
                        System.out.println("Out of bounds");
                        break;
                    }
                    cell el = field.get(crd.y).get(crd.x);

                    for (int i = 0; i < 4; i++) {
                        if (crd.dirs[i] == 1) {
                            if (el.tracks[i] == 0) {
                                el.tracks[i] = 1;
                            } else {
                                continue big_loop;
                            }
                        }
                    }

                    if (el.t == '/') {
                        if (crd.dirs[0] == 1) {
                            crd.dirs[0] = 0;
                            crd.dirs[3] = 1;
                        } else if (crd.dirs[1] == 1) {
                            crd.dirs[1] = 0;
                            crd.dirs[2] = 1;
                        } else if (crd.dirs[2] == 1) {
                            crd.dirs[2] = 0;
                            crd.dirs[1] = 1;
                        } else if (crd.dirs[3] == 1) {
                            crd.dirs[3] = 0;
                            crd.dirs[0] = 1;
                        }
                    }
                    if (el.t == '\\') {
                        if (crd.dirs[0] == 1) {
                            crd.dirs[0] = 0;
                            crd.dirs[2] = 1;
                        } else if (crd.dirs[1] == 1) {
                            crd.dirs[1] = 0;
                            crd.dirs[3] = 1;
                        } else if (crd.dirs[2] == 1) {
                            crd.dirs[2] = 0;
                            crd.dirs[0] = 1;
                        } else if (crd.dirs[3] == 1) {
                            crd.dirs[3] = 0;
                            crd.dirs[1] = 1;
                        }
                    }
                    if (el.t == '-') {
                        coord new_coord = new coord();
                        // left right up down
                        new_coord.dirs[0] = 1;
                        new_coord.x = crd.x;
                        new_coord.y = crd.y;
                        stack.add(new_coord);
                        for (int i = 0; i < 4; i++) {
                            crd.dirs[i] = 0;
                        }
                        crd.dirs[1] = 1;
                    }
                    if (el.t == '|') {
                        coord new_coord = new coord();
                        // left right up down
                        new_coord.dirs[2] = 1;
                        new_coord.x = crd.x;
                        new_coord.y = crd.y;
                        stack.add(new_coord);
                        for (int i = 0; i < 4; i++) {
                            crd.dirs[i] = 0;
                        }
                        crd.dirs[3] = 1;
                    }
                }
            }
            for (int iter_p = 0; iter_p < field.size(); iter_p++) {
                for (int jiter_p = 0; jiter_p < field.get(0).size(); jiter_p++) {
                    cell e = field.get(iter_p).get(jiter_p);
                    boolean occ = false;
                    for (int iter = 0; iter < 4; iter++)
                        if (e.tracks[iter] == 1)
                            occ = true;
                    if (occ) {
                        total++;
                        System.out.print("#");
                    } else
                        System.out.print(".");
                }
                System.out.println();
            }

            System.out.println(total);
            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    int calc(List<List<cell>> field, coord start) {
        int width = field.get(0).size();
        int height = field.size();
        int total = 0;
        List<coord> stack = new ArrayList<>();
        {
            stack.add(start);
        }
        big_loop: while (stack.size() != 0) {
            coord crd = stack.removeFirst();

            while (true) {
                // left right up down
                if (crd.dirs[0] == 1)
                    crd.x--;
                if (crd.dirs[1] == 1)
                    crd.x++;
                if (crd.dirs[2] == 1)
                    crd.y--;
                if (crd.dirs[3] == 1)
                    crd.y++;
                if (crd.x < 0 || crd.x >= width || crd.y < 0 || crd.y >= height) {
                    break;
                }
                cell el = field.get(crd.y).get(crd.x);

                for (int i = 0; i < 4; i++) {
                    if (crd.dirs[i] == 1) {
                        if (el.tracks[i] == 0) {
                            el.tracks[i] = 1;
                        } else {
                            continue big_loop;
                        }
                    }
                }

                if (el.t == '/') {
                    if (crd.dirs[0] == 1) {
                        crd.dirs[0] = 0;
                        crd.dirs[3] = 1;
                    } else if (crd.dirs[1] == 1) {
                        crd.dirs[1] = 0;
                        crd.dirs[2] = 1;
                    } else if (crd.dirs[2] == 1) {
                        crd.dirs[2] = 0;
                        crd.dirs[1] = 1;
                    } else if (crd.dirs[3] == 1) {
                        crd.dirs[3] = 0;
                        crd.dirs[0] = 1;
                    }
                }
                if (el.t == '\\') {
                    if (crd.dirs[0] == 1) {
                        crd.dirs[0] = 0;
                        crd.dirs[2] = 1;
                    } else if (crd.dirs[1] == 1) {
                        crd.dirs[1] = 0;
                        crd.dirs[3] = 1;
                    } else if (crd.dirs[2] == 1) {
                        crd.dirs[2] = 0;
                        crd.dirs[0] = 1;
                    } else if (crd.dirs[3] == 1) {
                        crd.dirs[3] = 0;
                        crd.dirs[1] = 1;
                    }
                }
                if (el.t == '-') {
                    coord new_coord = new coord();
                    // left right up down
                    new_coord.dirs[0] = 1;
                    new_coord.x = crd.x;
                    new_coord.y = crd.y;
                    stack.add(new_coord);
                    for (int i = 0; i < 4; i++) {
                        crd.dirs[i] = 0;
                    }
                    crd.dirs[1] = 1;
                }
                if (el.t == '|') {
                    coord new_coord = new coord();
                    // left right up down
                    new_coord.dirs[2] = 1;
                    new_coord.x = crd.x;
                    new_coord.y = crd.y;
                    stack.add(new_coord);
                    for (int i = 0; i < 4; i++) {
                        crd.dirs[i] = 0;
                    }
                    crd.dirs[3] = 1;
                }
            }
        }
        for (int iter_p = 0; iter_p < field.size(); iter_p++) {
            for (int jiter_p = 0; jiter_p < field.get(0).size(); jiter_p++) {
                cell e = field.get(iter_p).get(jiter_p);
                boolean occ = false;
                for (int iter = 0; iter < 4; iter++)
                    if (e.tracks[iter] == 1)
                        occ = true;
                if (occ) {
                    total++;
                }
            }
        }
        return total;
    }

    public void solution2() {
        try {
            File inputFile = new File("input_real");
            Scanner myScanner = new Scanner(inputFile);
            int max = 0;
            List<List<cell>> field = new ArrayList<>();
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                List<cell> row_c = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    row_c.add(new cell(c));
                }
                field.add(row_c);
            }
            int width = field.get(0).size();
            int height = field.size();
            for (int i = 0; i < height; i++) {
                coord c = new coord();
                c.dirs[1] = 1;
                c.x = -1;
                c.y = i;
                int curr = calc(field, c);
                System.out.println(curr);
                if (curr > max)
                    max = curr;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        cell ce = field.get(y).get(x);
                        ce.tracks = new int[] { 0, 0, 0, 0 };
                    }
                }
            }
            for (int i = 0; i < height; i++) {
                coord c = new coord();
                c.dirs[0] = 1;
                c.x = width;
                c.y = i;
                int curr = calc(field, c);
                if (curr > max)
                    max = curr;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        cell ce = field.get(y).get(x);
                        ce.tracks = new int[] { 0, 0, 0, 0 };
                    }
                }
            }
            for (int i = 0; i < width; i++) {
                coord c = new coord();
                c.dirs[3] = 1;
                c.x = i;
                c.y = -1;
                int curr = calc(field, c);
                if (curr > max)
                    max = curr;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        cell ce = field.get(y).get(x);
                        ce.tracks = new int[] { 0, 0, 0, 0 };
                    }
                }
            }
            for (int i = 0; i < width; i++) {
                coord c = new coord();
                c.dirs[2] = 1;
                c.x = i;
                c.y = height;
                int curr = calc(field, c);
                if (curr > max)
                    max = curr;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        cell ce = field.get(y).get(x);
                        ce.tracks = new int[] { 0, 0, 0, 0 };
                    }
                }
            }
            System.out.println(max);

            myScanner.close();
        } catch (

        FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Solution().solution2();
    }

}
