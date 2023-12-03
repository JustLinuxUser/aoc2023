package solutions;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Solution
 */
public class Solution {

    public static void solution1() {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        try {
            File inputFile = new File("input_d2");
            Scanner myScanner = new Scanner(inputFile);
            int sum = 0;
            while (myScanner.hasNextLine()) {
                String game = myScanner.nextLine();
                int game_id = Integer.parseInt(game.split(":")[0].split(" ")[1]);
                String[] pulls = game.split(":")[1].split(";");
                boolean valid = true;
                for (String pull : pulls) {
                    String[] colors = pull.split(",");
                    for (String color : colors) {
                        color = color.trim();
                        int value = Integer.parseInt(color.split(" ")[0]);
                        color = color.split(" ")[1].trim();
                        if (color.equals("red") && value > 12)
                            valid = false;
                        if (color.equals("green") && value > 13)
                            valid = false;
                        if (color.equals("blue") && value > 14)
                            valid = false;
                    }
                }
                System.out.println(game_id + " " + valid);
                sum += valid == true ? game_id : 0;
            }
            System.out.println(sum);
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public static void solution2() {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        try {
            File inputFile = new File("input_d2");
            Scanner myScanner = new Scanner(inputFile);
            int sum = 0;
            while (myScanner.hasNextLine()) {
                String game = myScanner.nextLine();
                String[] pulls = game.split(":")[1].split(";");
                int reds = 0, greens = 0, blues = 0;
                for (String pull : pulls) {
                    String[] colors = pull.split(",");
                    for (String color : colors) {
                        color = color.trim();
                        int value = Integer.parseInt(color.split(" ")[0]);
                        color = color.split(" ")[1].trim();
                        if (color.equals("red") && value > reds)
                            reds = value;
                        if (color.equals("green") && value > greens)
                            greens = value;
                        if (color.equals("blue") && value > blues)
                            blues = value;
                    }
                }
                sum += reds * greens * blues;
            }
            System.out.println(sum);
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
