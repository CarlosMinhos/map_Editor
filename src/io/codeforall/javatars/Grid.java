package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {
    private int x;
    private int y;
    private int width;
    private int height;
    private int cellSize;
    private Rectangle[][] tiles;

    public Grid(int x, int y, int width, int height, int cellSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;

        tiles = new Rectangle[height / cellSize][width / cellSize];
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                tiles[row][col] = new Rectangle(x + col * cellSize, y + row * cellSize, cellSize, cellSize);
            }
        }
    }

    public void draw() {
        for (Rectangle[] row : tiles) {
            for (Rectangle rectangle : row) {
                rectangle.setColor(Color.BLACK);
                rectangle.draw();
            }
        }
    }

    public void paintTile(int x, int y) {
        int row = (y - this.y) / cellSize;
        int col = (x - this.x) / cellSize;

        if (row >= 0 && row < tiles.length && col >= 0 && col < tiles[0].length) {
            tiles[row][col].setColor(Color.BLACK);
            tiles[row][col].fill();
        }
    }
    public void resetTiles() {
        for (Rectangle[] row : tiles) {
            for (Rectangle tile : row) {
                tile.setColor(Color.WHITE);
                tile.fill();
            }
        }
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


}
