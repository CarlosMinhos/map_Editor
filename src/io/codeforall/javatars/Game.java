package io.codeforall.javatars;


import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;

import java.awt.image.BufferedImage;

import static io.codeforall.javatars.Grid.*;


public class Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private int CELL_SIZE = 20;
    private Grid grid;
    private Cursor cursor;

    public Game() {
        Canvas.limitCanvasHeight(HEIGHT);
        Canvas.limitCanvasWidth(WIDTH);
        grid = new Grid(10, 10, WIDTH, HEIGHT, CELL_SIZE);
        cursor = new Cursor(10, 10, CELL_SIZE, grid);
        Keyboard keyboard = new Keyboard(cursor);
        Cursor.addArrowKeyListeners(keyboard);
    }

    public void init() {
        grid.draw();
        cursor.draw();
    }
}


