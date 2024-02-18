package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Grid {
    public Object getWidth;
    public Object getX;
    //public static final int WIDTH = 800;
    //public static final int HEIGHT = 800;
    private int x;
    private int y;
    private int width;
    private int height;
    private int cellSize;
    //private Rectangle tile;
    //private Cursor cursor;
    private Tile[][] tiles;
    //private int rows;
    //private int cols;


    public Grid(int x, int y, int width, int height, int cellSize) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.cellSize = cellSize;

       // int paddedWidth = width - 20;
        //int paddedHeight = height - 20;

        /*this.rows = paddedWidth / cellSize;
        this.cols = paddedHeight / cellSize;

        tiles = new Tile[rows][cols];

        //tileColors = new Color[WIDTH / cellSize][HEIGHT / cellSize];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new Tile(x + 10 + i * cellSize, y + 10 + j * cellSize, cellSize);
            }
        }*/
        //tile = new Rectangle(x, y, WIDTH, HEIGHT);
        //tile.setColor(Color.BLACK);
        //tile.draw();
    }

    public void draw() {
        Rectangle tile = new Rectangle(x,y, width,height);
        tile.setColor(Color.BLACK);
        tile.draw();

       for (int row = y + cellSize; row < height; row += cellSize) {
            for (int col = x + cellSize; col < x+width; col += cellSize) {
                new Rectangle(col, row, cellSize, cellSize).draw();
                //tile.setColor(Color.BLACK);
                //tile.draw();
            }
        }
    }

    public void paintTile(int x, int y) {
        //Rectangle tile = new Rectangle(x,y,cellSize,cellSize);
        //int row = (x - this.x - 10) / cellSize;
        //int col = (y - this.y - 10) / cellSize;

        //tileColors[colIndex][rowIndex] = Color.BLACK;
        //if (row >= 0 && row < rows && col >= 0 && col < cols) {
          //  tiles[row][col].setColor(Color.BLACK);
            //tiles[row][col].fill();
        //}
        Rectangle tile = new Rectangle(x, y, cellSize, cellSize);
        tile.setColor(Color.BLACK);
        tile.fill();
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    /*public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    Color color = tiles[row][col].getColor();
                    writer.write(color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\n");
                    //writer.write(" ");
                }
                //writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /*public void setCursor(Cursor cursor){
        this.cursor=cursor;
    }*/
}
