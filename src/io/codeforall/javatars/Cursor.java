package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Cursor implements KeyboardHandler {
    private Rectangle rect;
    private int x;
    private int y;
    private int cellSize;
    //private Keyboard keyboard;
    private Grid grid;
    private boolean isSpacePressed = false;
    private boolean isPaintingThreadRunning = false;

    public Cursor(int x, int y, int cellSize, Grid grid) {
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        this.grid=grid;
        this.rect = new Rectangle(x+10, y+10, cellSize, cellSize);
        rect.setColor(Color.GREEN);
        rect.fill();

    }

    /*public void draw(){
        rect.draw();
    }*/

   public void draw() {
        rect.delete();
        rect = new Rectangle(x+10, y+10, cellSize, cellSize);
        rect.setColor(Color.GREEN);
        rect.fill();
    }

    public void moveUp() {
        if (y - cellSize >= grid.getY() + 10) {
            y -= cellSize;
            draw();
            //rect.translate(0,-cellSize);
        }
    }

    public void moveDown() {
        if (y + cellSize < grid.getY() + grid.getHeight() - 10) {
            y += cellSize;
            //rect.translate(0,cellSize);
            draw();
        }
    }

    public void moveLeft() {
        if (x - cellSize >= grid.getX()+10) {
            x -= cellSize;
            //rect.translate(-cellSize,0);
            draw();
        }
    }

    public void moveRight() {
        if (x + cellSize < grid.getX() + grid.getWidth()-10) {
            x += cellSize;
            //rect.translate(cellSize,0);
            draw();
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
            isSpacePressed=true;
            //paint();
            startPaintingThread();
            grid.paintTile(x,y);
        } else if (key == KeyboardEvent.KEY_S) {
            saveToFile();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        int key = keyboardEvent.getKey();
        if(key == KeyboardEvent.KEY_SPACE){
            isSpacePressed=false;
        }
    }
    private void startPaintingThread (){
        if (!isPaintingThreadRunning){
            isPaintingThreadRunning = true;
            new Thread(this::paint).start();
        }
    }
    private void paint(){
        while (isSpacePressed) {
            grid.paintTile(x,y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
        isPaintingThreadRunning=false;
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
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    private void saveToFile(){
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write(grid.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
