package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Tile {
    private Rectangle rectangle;
    private Color color;

    public Tile (int x, int y, int size){
        rectangle = new Rectangle(x,y,size,size);
        rectangle.setColor(Color.WHITE);
        rectangle.draw();
    }

    public void setColor(Color color){
        rectangle.setColor(color);
    }

    public Color getColor(){
        return color;
    }

    public void fill(){
        rectangle.fill();
    }
}
