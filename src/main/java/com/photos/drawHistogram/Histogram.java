package com.photos.drawHistogram;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Histogram {
    //o sa revin la asta mai tarziu
    public static final int MAX_SCORE = 100;  // max possible score

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:\\Users\\Roxana\\Downloads\\midterm.txt"));

        // read file into count array
        int[] count = new int[MAX_SCORE + 1];
        while (input.hasNextInt()) {
            int score = input.nextInt();
            count[score]++;
        }

        // use array to produce a histogram
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                System.out.print(i + ": ");
                for (int j = 1; j <= count[i]; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

        draw(count);
    }

    // Uses a DrawingPanel to draw the histogram
    public static void draw(int[] count) {
        DrawingPanel p = new DrawingPanel(count.length * 6 + 12, 500);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLUE);
        for (int i = 0; i < count.length; i++) {
            g.drawLine(i * 6 + 6, 475, i * 6 + 6, 475 - 10 * count[i]);
        }
    }
}