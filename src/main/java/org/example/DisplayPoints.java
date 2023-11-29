package org.example;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayPoints extends JFrame {

    private static final int CANVAS_WIDTH = 540;
    private static final int CANVAS_HEIGHT = 960;
    private static final int POINT_RADIUS = 1;

    private BufferedImage canvas;

    public DisplayPoints() {
        canvas = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        initializeCanvas();
        loadAndDisplayPoints("C:\\Users\\Downloads\\DS4.txt");
        displayResult();
    }

    private void initializeCanvas() {
        Graphics2D g = canvas.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    private void loadAndDisplayPoints(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Graphics2D g = canvas.createGraphics();
            g.setColor(Color.BLACK);

            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split("\\s+");
                if (coordinates.length == 2) {
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    g.fillOval(x - POINT_RADIUS, y - POINT_RADIUS, POINT_RADIUS * 2, POINT_RADIUS * 2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayResult() {
        ImageIcon imageIcon = new ImageIcon(canvas);
        JLabel jLabel = new JLabel(imageIcon);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(jLabel);
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new DisplayPoints());
    }
}