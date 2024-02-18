package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class Cursor implements KeyboardHandler {
    private Rectangle rect;
    private int x;
    private int y;
    private int cellSize;
    private Grid grid;
    private boolean isSpacePressed = false;

    public Cursor(int x, int y, int cellSize, Grid grid) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.grid = grid;
        this.rect = new Rectangle(x, y, cellSize, cellSize);
        rect.setColor(Color.GREEN);
        rect.fill();

    }


    public void draw() {
        rect.delete();
        rect = new Rectangle(x, y, cellSize, cellSize);
        rect.setColor(Color.GREEN);
        rect.fill();
    }

    public void moveUp() {
        if (y - cellSize >= grid.getY()) {
            y -= cellSize;
            draw();
        }
    }

    public void moveDown() {
        if (y + cellSize < grid.getY() + grid.getHeight()) {
            y += cellSize;
            draw();
        }
    }

    public void moveLeft() {
        if (x - cellSize >= grid.getX()) {
            x -= cellSize;
            draw();
        }
    }

    public void moveRight() {
        if (x + cellSize < grid.getX() + grid.getWidth()) {
            x += cellSize;
            draw();
        }
    }

    public void saveToFile(String filename) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream)) {

            for (int row = 0; row < grid.getWidth(); row += cellSize) {
                for (int col = 0; col < grid.getHeight(); col += cellSize) {
                    String tileInfo = row + "," + col + System.lineSeparator();
                    writer.write(tileInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void paint() {
        while (isSpacePressed) {
            grid.paintTile(x, y);
        }
    }
    @Override
    public void keyPressed(KeyboardEvent event) {
        int key = event.getKey();
        if (key == KeyboardEvent.KEY_UP) {
            moveUp();
        } else if (key == KeyboardEvent.KEY_DOWN) {
            moveDown();
        } else if (key == KeyboardEvent.KEY_RIGHT) {
            moveRight();
        } else if (key == KeyboardEvent.KEY_LEFT) {
            moveLeft();
        } else if (key == KeyboardEvent.KEY_SPACE) {
            //isSpacePressed=true;
            //paint();
            grid.paintTile(x, y);
        } else if (key == KeyboardEvent.KEY_S) {
            saveToFile("test.txt");
        } else if (key == KeyboardEvent.KEY_C) {
            grid.resetTiles();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        int key = event.getKey();
        if (key == KeyboardEvent.KEY_SPACE) {
            isSpacePressed = false;
        }
    }

    public static void addArrowKeyListeners(Keyboard keyboard) {
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRight);

        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLeft);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveUp);

        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveDown);

        KeyboardEvent paint = new KeyboardEvent();
        paint.setKey(KeyboardEvent.KEY_SPACE);
        paint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(paint);

        KeyboardEvent stopPaint = new KeyboardEvent();
        stopPaint.setKey(KeyboardEvent.KEY_SPACE);
        stopPaint.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(stopPaint);

        KeyboardEvent saveImage = new KeyboardEvent();
        saveImage.setKey(KeyboardEvent.KEY_S);
        saveImage.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(saveImage);

        KeyboardEvent resetTiles = new KeyboardEvent();
        resetTiles.setKey(KeyboardEvent.KEY_C);
        resetTiles.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(resetTiles);
    }

}
